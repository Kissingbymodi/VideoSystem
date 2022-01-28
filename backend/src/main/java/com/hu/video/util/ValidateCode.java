package com.hu.video.util;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ValidateCode {
    public static final String RANDOMCODEKEY = "ValidateCode";
    //将图片存放到Session中的key

    private Random random = new Random();//生成随机数

    private String randomString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //图片中随机产生的字母或数字

    private int width = 80;//图片宽
    private int height = 26;//图片高
    private int lineSize = 40;//干扰线数量
    private int stringNum = 4;//随机产生字符数量

    //获得字体
    private Font getFont(){
        return new Font("Fixedsys",Font.CENTER_BASELINE,18);
    }

    //获得随机颜色值
    private Color getRandColor(int fc,int bc){
        if(fc > 255) {
            fc = 255;
        }
        if(bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc-fc-16);
        int g = fc + random.nextInt(bc-fc-14);
        int b = fc + random.nextInt(bc-fc-18);
        return new Color(r,g,b);
    }

    /*
     * 返回随机的字符
     */
    public String getRandomString(int num){
        return String.valueOf(randomString.charAt(num));
    }

    /*
     * 绘制字符串
     */
    private String drawString(Graphics g, String randomString, int i){
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(this.randomString.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /*
     * 绘制干扰线
     */
    private void drawLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    //核心方法：生成随机验证码，并返回
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
        g.setColor(getRandColor(110, 133));

        //绘制干扰线
        for(int i = 0;i <= lineSize; i++){
            drawLine(g);
        }

        //绘制随机字符
        String randomString = "";
        for(int i = 1; i <= stringNum; i++){
            randomString = drawString(g, randomString, i);
        }

        //将生成的随机字符串装进Session作用域
        session.removeAttribute(RANDOMCODEKEY);
        session.setAttribute(RANDOMCODEKEY, randomString);
        System.out.println(randomString);//输出生成的随机字符串

        g.dispose();//关闭窗体，释放资源
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
