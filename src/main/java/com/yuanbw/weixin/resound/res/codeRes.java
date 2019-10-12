package com.yuanbw.weixin.resound.res;

import com.yuanbw.weixin.resound.util.CodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;

@RestController
@RequestMapping("/wx/code")
public class codeRes {
    public BASE64Encoder encoder = new sun.misc.BASE64Encoder();//编码
    public BASE64Decoder decoder = new sun.misc.BASE64Decoder();//解码
    public String appid = "wxed0257ad02fcb9cf";//微信appid
    public String appsecret = "5f311e2bca0ef69cb60e02fb7801c472";//微信appsecret
    public String Access_Token;
    public byte[] code;

    @RequestMapping(method = RequestMethod.GET)
    public void getWxCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String numberString = request.getParameter("codenumber");//生成数量
        int number = Integer.valueOf(numberString);
        if (number == 0) {
            response.getWriter().print("{\"result\":\"0\"}");
        } else {
//            codeDaoimpl codeDaoimpl = new codeDaoimpl();
//            //搜索到数据库中最大的id值作为这一次生成二维码的起始id
//            int startID = codeDaoimpl.findMaxCodeID();
//            System.out.println(startID);
            int startID=0;
            Access_Token =  CodeUtil.getAccess_Token("wxa6ca53fdf9591454", "401e905a0666b2dc9501ca034ac2a0ac");
            for (int i = startID+1; i <= startID+number; i++) {
                code = CodeUtil.getCode(Access_Token, String.valueOf(i));
                CodeUtil.WxCodeToImage(code, "e://微信二维码//", "code" + String.valueOf(i) + ".jpg");//默认存储路径为e盘根目录
//                int result = codeDaoimpl.insertCodeInfo(i);//插入数据库
            }
            response.getWriter().print("{\"result\":\"1\"}");
        }
    }
}
