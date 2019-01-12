package com.project;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class HelperClazz {
    private static final String DEFAULT_TEXT_CONTENT_TYPE = "text/plain; charset=utf8";
    private static final String DEFAULT_JSON_CONTENT_TYPE = "application/json; charset=utf8";
    //获取当前日期
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    //获取当前时间
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    //url中文编码
    public static String encode(String s) {
        try {
            return URLEncoder.encode(s, "utf8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    //url中文解码
    public static String decode(String s) {
        try {
            return URLDecoder.decode(s, "utf8");
        } catch (Exception e) {
            return "";
        }
    }

    //获得登陆信息id
    //flag==0 is ID;flag==1 is Name;flag==2 is roleId;flag==3 is roleName;flag==4 is roleFlag;;flag==5 is roleLevel;
    public static String getLoginInfo(HttpServletRequest request, int idx) {
        HttpSession session = request.getSession();
        String sess = (String) session.getAttribute("information_build");

        if (sess != null) {
            String[] loginInfos = sess.split("@");
            return loginInfos[idx];
        } else {
            return "";
        }

    }

    //生成随机名称，用于上传文件命名
    public static String getRandomName() {
        Random r = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
        StringBuffer sb = new StringBuffer();
        sb.append(r.nextInt(100));
        sb.append(r.nextInt(100));
        sb.append("_");
        sb.append(sdf.format(new Date()));
        sb.append("_");
        sb.append(r.nextInt(100));
        sb.append(r.nextInt(100));
        return sb.toString();
    }

    //获得文件名后缀
    public static String getFileExt(String fileName) {
        int idx = fileName.lastIndexOf(".");
        String ext = null;
        if (idx != -1) {
            ext = fileName.substring(idx + 1);
            if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpg")) {
                return ext;
            }
        }
        return ext;
    }

    //response返回内容
    public static void renderText(HttpServletResponse response, String text) {
        PrintWriter writer = null;
        try {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType(DEFAULT_TEXT_CONTENT_TYPE);
            writer = response.getWriter();
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    //response返回内容
    public static void renderJson(HttpServletResponse response, String text) {
        PrintWriter writer = null;
        try {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType(DEFAULT_JSON_CONTENT_TYPE);
            writer = response.getWriter();
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public static boolean isEmpty(Object o) {
        return StringUtils.isEmpty(o);
    }

    /**
     * 将网络图片进行Base64位编码
     *
     * @return     * @param imgUrl 图片的url路径，如http://.....xx.jpg

     */
    public static String encodeImgageToBase64(URL imageUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将本地图片进行Base64位编码
     *
     *  imgUrl 图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String encodeImgageToBase64(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

//    /**
//     * 将Base64位编码的图片进行解码，并保存到指定目录
//     *
//     * @param base64 base64编码的图片信息
//     * @return
//     */
//    public static void decodeBase64ToImage(String base64, String path, String imgName) {
//        BASE64Decoder decoder = new BASE64Decoder();
//        try {
//            FileOutputStream write = new FileOutputStream(new File(path + imgName));
//            byte[] decoderBytes = decoder.decodeBuffer(base64);
//            write.write(decoderBytes);
//            write.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    //短信发送
//    public static void sendSMS(String n,String m){
//        HttpRequest.post("http://202.108.100.197/sms").queryEncoding("gb2312").query("n", n).query("m",m).send().close();
//    }

    //获得指定位数的数字随机数
    public static String getRandomNum(){
        return RandomStringUtils.randomNumeric(6);
    }

    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }
    public static byte[] ipv4Address2BinaryArray(String ipAdd){
        byte[] binIP = new byte[4];
        String[] strs = ipAdd.split("\\.");
        for(int i=0;i<strs.length;i++){
            binIP[i] = (byte) Integer.parseInt(strs[i]);
        }
        return binIP;
    }
    public static String binaryArray2Ipv4Address(byte[] addr){
        StringBuffer ip=new StringBuffer();
        for(int i=0;i<addr.length;i++){
            ip.append((addr[i]&0xFF)+".");
        }
        return ip.substring(0, ip.length()-1);
    }

    public static String getRemoteIP(HttpServletRequest request){
        if (isEmpty(request.getHeader("x-forwarded-for"))) {
            return request.getRemoteAddr();
        }else{
            return request.getHeader("x-forwarded-for");
        }
    }
}
