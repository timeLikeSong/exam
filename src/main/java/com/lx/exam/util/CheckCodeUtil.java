package com.lx.exam.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


@Component
public class CheckCodeUtil {


	/**
	 * 已有验证码，生成验证码图片
	 * @param textCode 文本验证码
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param interLine 图片中干扰线的条数
	 * @param randomLocation 每个字符的高低位置是否随机
	 * @param backColor 图片颜色，若为null，则采用随机颜色
	 * @param foreColor 字体颜色，若为null，则采用随机颜色
	 * @param lineColor 干扰线颜色，若为null，则采用随机颜色
	 * @return 图片缓存对象
	 */
	public static BufferedImage generateImageCode(String textCode, int width, int height, int interLine, boolean randomLocation,
			Color backColor, Color foreColor, Color lineColor) {

		BufferedImage bim = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bim.getGraphics();

		// 画背景图
		g.setColor(backColor == null ? getRandomColor() : backColor);
		g.fillRect(0, 0, width, height);

		// 画干扰线
		Random r = new Random();
		if (interLine > 0) {

			int x = 0, y = 0, x1 = width, y1 = 0;
			for (int i = 0; i < interLine; i++) {
				g.setColor(lineColor == null ? getRandomColor() : lineColor);
				y = r.nextInt(height);
				y1 = r.nextInt(height);

				g.drawLine(x, y, x1, y1);
			}
		}

		// 写验证码

		// 字体大小为图片高度的80%
		int fsize = (int) (height * 0.8);
		int fx = 0;
		int fy = fsize;

		g.setFont(new Font("", Font.PLAIN, fsize));

		// 写验证码字符
		for (int i = 0; i < textCode.length(); i++) {
			fy = randomLocation ? (int) ((Math.random() * 0.3 + 0.6) * height) : fy;// 每个字符高低是否随机
			g.setColor(foreColor == null ? getRandomColor() : foreColor);
			g.drawString(textCode.charAt(i) + "", fx, fy);
			fx += (width / textCode.length()) * (Math.random() * 0.3 + 0.8);
		}

		g.dispose();

		return bim;
	}

	/**
	 * 产生随机颜色
	 * @return
	 */
	private static Color getRandomColor() {
		Random r = new Random();
		Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		return c;
	}

	/**
	 * 绘制验证码
	 * @param type 1 数字英文 2 中文 3 数字计算 4 中文计算
	 * @param request
	 * @param response
	 */
	public static void drawCode(int type, HttpServletRequest request, HttpServletResponse response) {

		try {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "No-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			OutputStream os = response.getOutputStream();
			VerifyCodeOper oper = new VerifyCodeOper();
			Object code = null;
			BufferedImage image = null;
			if (type == 2) { // 中文计算
				code = VerifyCodeChinese.generateTextCode(4);
			}
			if (type == 4) { // 中文计算
				image = oper.drawVerificationCodeImage();
				code = oper.getXyresult();
			}
			request.getSession().setAttribute("checkcode", code);
			ImageIO.write(image, "JPEG", os);
			os.flush();
			os.close();
			os = null;
			// response.flushBuffer();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
