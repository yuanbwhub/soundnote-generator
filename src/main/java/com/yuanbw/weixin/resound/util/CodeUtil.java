package com.yuanbw.weixin.resound.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CodeUtil {
    //获取Access_Token，方法中调用sendHttpRequestAccess_Token();
    public static String getAccess_Token(String appid, String appsecret) {
        String access_token = "";
        String jsonData = sendHttpRequestAccess_Token("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + appsecret);
        String jsonArray[] = jsonData.split(",");
        String jsonObject = jsonArray[0];
        access_token = jsonObject.split(":")[1];
        access_token = access_token.substring(1, access_token.length() - 1);
        return access_token;
    }

    //获取Access_Token，发送http请求，请求方式为get
    public static String sendHttpRequestAccess_Token(String requestUrl) {
        URL url = null;
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder response = null;
        try {
            url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            InputStream in = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    //获取code，方法中调用sendHttpRequestCode();value为传入参数
    public static byte[] getCode(String access_token, String value) {
        byte[] jsonData = sendHttpRequestCode("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token, value);
        return jsonData;
    }

    //获取code的二进制字节流，发送HTTP请求，请求方式为post
    public static byte[] sendHttpRequestCode(String requestUrl, String value) {
        URL url = null;
        StringBuilder response = null;
        byte[] codebuf = new byte[1024000];
        String params = "{\"scene\":\"" + value + "\",\"page\":\"pages/main/main\"}";
        try {
            url = new URL(requestUrl);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();

            InputStream in = connection.getInputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            int sum = 0;
            while ((len = in.read(buf)) > 0) {
                for (int i = 0; i < len; i++) {
                    codebuf[sum + i] = buf[i];
                }
                sum += len;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codebuf;
    }

    //将微信小程序code的二进制字节流转换为image并保存在本地指定路径及图片名字
    public  static void WxCodeToImage(byte[] code, String path, String imgname) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(code);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File(path + imgname);// 可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
