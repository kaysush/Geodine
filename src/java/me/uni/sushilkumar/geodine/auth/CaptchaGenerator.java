/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.uni.sushilkumar.geodine.auth;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 *
 * @author sushil
 */
public class CaptchaGenerator {
    private int height,width;
private char[] chars={'a','b','5','c','d','e','f','g','1','h','i','7','j','k','l','0','m','n','o','p','q','r','s','t','u','v','8','w','x','y','6','z',
'A','B','4','C','D','E','F','3','G','H','I','J','K','L','M','N','2','O','P','Q','R','S','T','U','V','W','X','Y','9','Z',
};
private String code;

	public CaptchaGenerator()
	{
	}

	public CaptchaGenerator(int width,int height)
	{
		this.width=width;
		this.height=height;
		
	}
	
	public String generateRandomCode(int length)
	{
		StringBuilder code=new StringBuilder(length);
		Random generator=new Random();
		for(int i=0;i<length;i++)
		{
			code.append(chars[generator.nextInt(62)]);
		}
		
		return code.toString();
	}
	
	public BufferedImage createImage() throws IOException, FontFormatException
	{
		BufferedImage captcha=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR );
		Graphics2D g=captcha.createGraphics();
		code=generateRandomCode(6);
		System.out.println(code);
		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Float(0,0,width,height));
		g.setColor(Color.RED);
		InputStream in = CaptchaGenerator.class.getResourceAsStream("captcha.ttf");
                Font font = Font.createFont(Font.TRUETYPE_FONT, in);
                font=font.deriveFont(30.0F);
                //Font font=new Font("Arial",Font.PLAIN,40);
		FontMetrics metrics = g.getFontMetrics(font);
		g.setFont(font);
		int actualWidth=metrics.stringWidth(code);
		int leftPadding=(width-actualWidth)/2;
		g.drawString(code,leftPadding,37);
		addNoise(captcha);
                g.dispose();
                return captcha;
		
	}
	
	public void addNoise(BufferedImage captcha)
	{
		long totalPixels=width*height;
		int i;
		Random generator=new Random();
		for(i=0;i<totalPixels/3;i++)
		{
			captcha.setRGB(generator.nextInt(width),generator.nextInt(height),5);
		}

		Graphics2D g=captcha.createGraphics();
		for(i=0;i<15;i++)
		{
			int x1=generator.nextInt(width);
			int y1=generator.nextInt(height);
			int x2=generator.nextInt(width);			
			int y2=generator.nextInt(height);
			g.setColor(Color.BLACK);
			g.drawLine(x1,y1,x2,y2);
		}
                g.dispose();
		
	}
        
        public String getCode()
        {
            return code;
        }
	
    
}
