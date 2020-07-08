package com.zxw.admin.common;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import java.text.DateFormat;
import java.util.*;
import java.util.Base64.Encoder;

@Component
public class Utils {

    private static Encoder encoder = Base64.getEncoder();

    /**
     * 将图片转换成base64进制
     *
     * @return
     */
    public String getImageBinary(String filePath) {
        File f = new File(filePath);
        BufferedImage bi;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            bi = ImageIO.read(f);
            ImageIO.write(bi, "png", baos);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        String result = encoder.encodeToString(bytes).trim();
        if(result == null) {
            System.out.println("获取图片为空，图片转为base64进制失败");
        }
        return result;
    }

    /**
     * 把request转为map
     *
     * @param request
     * @return
     */
    public Map<String, Object> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map<?, ?> properties = request.getParameterMap();
        // 返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Iterator<?> entries = properties.entrySet().iterator();

        Map.Entry<String, Object> entry;
        String name = "";
        String value = "";
        Object valueObj =null;
        while (entries.hasNext()) {
            entry = (Map.Entry<String, Object>) entries.next();
            name = (String) entry.getKey();
            valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    public  Map<String, String> requestToMap(HttpServletRequest request) {
        @SuppressWarnings("rawtypes")
        Enumeration e = request.getParameterNames();
        Object object = null;
        String paramKey = "";
        Map<String, String> maps = new HashMap<String, String>();
        while (e.hasMoreElements()) {
            object = (Object) e.nextElement();
            paramKey = object.toString();

            maps.put(object.toString(), request.getParameter(paramKey));
        }
        return maps;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 图片上传
     *
     * **/
    public static String upload(MultipartFile file, String path, String fileName) throws Exception {
        // 生成新的文件名
        String add = DateFormat.getDateInstance().format(new Date())+"/"+UUID.randomUUID().toString().replace("-", "")+"/";
        String realPath = path + "/" + add;
        File dest = new File(realPath,fileName);
        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            System.out.println("创建新文件夹");
            boolean b=dest.getParentFile().mkdirs();
            if(b) {
                System.out.println("创建成功");
            }else {
                System.out.println("创建失败");
            }
        }
        System.out.println("----"+dest+"===="+dest.getName());
        // 保存文件
        file.transferTo(dest);
        System.out.println("路径:"+add);
        return add+dest.getName();
    }

    /**
     * 直接指定压缩后的宽高：
     * (先保存原文件，再压缩、上传)
     * 壹拍项目中用于二维码压缩
     * @param oldFile 要进行压缩的文件全路径
     * @param width 压缩后的宽度
     * @param height 压缩后的高度
     * @param quality 压缩质量
     * @param smallIcon 文件名的小小后缀(注意，非文件后缀名称),入压缩文件名是yasuo.jpg,则压缩后文件名是yasuo(+smallIcon).jpg
     * @return 返回压缩后的文件的全路径
     */
    public static String zipImageFile(String oldFile, int width, int height,
                                      float quality, String smallIcon) {
        if (oldFile == null) {
            return null;
        }
        String newImage = null;
        try {
            /**对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(new File(oldFile));
            /** 宽,高设定 */
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
            String filePrex = oldFile.substring(0, oldFile.indexOf('.'));
            /** 压缩后的文件名 */
            newImage = filePrex + smallIcon + oldFile.substring(filePrex.length());
            /** 压缩之后临时存放位置 */
            FileOutputStream out = new FileOutputStream(newImage);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /** 压缩质量 */
            jep.setQuality(quality, true);
            encoder.encode(tag, jep);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newImage;
    }

    /**
     * 保存文件到服务器临时路径(用于文件上传)
     * @param fileName
     * @param is
     * @return 文件全路径
     */
    public static String writeFile(String fileName, InputStream is) {
        if (fileName == null || fileName.trim().length() == 0) {
            return null;
        }
        try {
            /** 首先保存到临时文件 */
            FileOutputStream fos = new FileOutputStream(fileName);
            byte[] readBytes = new byte[512];// 缓冲大小
            int readed = 0;
            while ((readed = is.read(readBytes)) > 0) {
                fos.write(readBytes, 0, readed);
            }
            fos.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 将图片转换成base64进制,压缩后80
     *
     * @return
     */
    public static String resizeImageTo80K(String filepath) throws IOException {
        String base64Img = imageToBase64(filepath);
        try {
            BufferedImage src = base64String2BufferedImage(base64Img);
            BufferedImage output = Thumbnails.of(src).size(src.getWidth()/3, src.getHeight()/3).asBufferedImage();
            String base64 = outputToBase64(output);
            if (base64.length() - base64.length() / 8 * 2 > 80000) {
                output = Thumbnails.of(output).scale(1/(base64.length()/80000)).asBufferedImage();
                base64 = outputToBase64(output);
            }
            return base64;
        } catch (Exception e) {
            return base64Img;
        }
    }

    /**
     * 将图片转换成base64进制,压缩后50
     *
     * @return
     */
    public static String resizeImageTo50K(String filepath) throws IOException {
        String base64Img = imageToBase64(filepath);
        try {
            BufferedImage src = base64String2BufferedImage(base64Img);
            BufferedImage output = Thumbnails.of(src).size(src.getWidth()/12, src.getHeight()/12).asBufferedImage();
            String base64 = outputToBase64(output);
            if (base64.length() - base64.length() / 8 * 2 > 80000) {
                output = Thumbnails.of(output).scale(1/(base64.length()/80000)).asBufferedImage();
                base64 = outputToBase64(output);
            }
            return base64;
        } catch (Exception e) {
            return base64Img;
        }
    }
    public static BufferedImage base64String2BufferedImage(String base64string) throws IOException {
        BufferedImage image = null;

        InputStream stream = BaseToInputStream(base64string);
        image = ImageIO.read(stream);

        return image;
    }
    private static InputStream BaseToInputStream(String base64string){
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64string);
            stream = new ByteArrayInputStream(bytes1);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return stream;
    }
    public static String imageToBase64(String filePath) throws IOException {
        File f = new File(filePath);
        BufferedImage bi;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            bi = ImageIO.read(f);
            ImageIO.write(bi, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        String result = encoder.encodeToString(bytes).trim();
        if(result == null) {
            System.out.println("获取图片为空，图片转为base64进制失败");
        }
        return result;
    }
    public static String outputToBase64(BufferedImage bufferedImage) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        return new String(encoder.encodeToString(baos.toByteArray()).trim());
    }

    /**
     * 等比例压缩算法：
     * 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
     * @param srcURL 原图地址
     * @param deskURL 缩略图地址
     * @param comBase 压缩基数
     * @param scale 压缩限制(宽/高)比例  一般用1：
     * 当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale<1,缩略图width=comBase,height按原图宽高比例
     * @throws Exception
     * @author shenbin
     * @createTime 2014-12-16
     * @lastModifyTime 2014-12-16
     */
    public static void saveMinPhoto(String srcURL, String deskURL, double comBase,
                                    double scale) throws Exception {
        File srcFile = new java.io.File(srcURL);
        Image src = ImageIO.read(srcFile);
        int srcHeight = src.getHeight(null);
        int srcWidth = src.getWidth(null);
        int deskHeight = 0;// 缩略图高
        int deskWidth = 0;// 缩略图宽
        double srcScale = (double) srcHeight / srcWidth;
        /**缩略图宽高算法*/
        if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
            if (srcScale >= scale || 1 / srcScale > scale) {
                if (srcScale >= scale) {
                    deskHeight = (int) comBase;
                    deskWidth = srcWidth * deskHeight / srcHeight;
                } else {
                    deskWidth = (int) comBase;
                    deskHeight = srcHeight * deskWidth / srcWidth;
                }
            } else {
                if ((double) srcHeight > comBase) {
                    deskHeight = (int) comBase;
                    deskWidth = srcWidth * deskHeight / srcHeight;
                } else {
                    deskWidth = (int) comBase;
                    deskHeight = srcHeight * deskWidth / srcWidth;
                }
            }
        } else {
            deskHeight = srcHeight;
            deskWidth = srcWidth;
        }
        BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
        tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); //绘制缩小后的图
        FileOutputStream deskImage = new FileOutputStream(deskURL); //输出到文件流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
        encoder.encode(tag); //近JPEG编码
        deskImage.close();
    }

}
