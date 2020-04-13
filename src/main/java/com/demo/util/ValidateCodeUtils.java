package com.demo.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @Description 生成验证码
 * @author waa
 * @version V1.0, 日期2011-7-4
 */
@WebServlet(name = "ValidateCodeUtils",urlPatterns = "/first")
public class ValidateCodeUtils extends HttpServlet {

	private static final long serialVersionUID = -1409007752285164213L;
	private static int width = 75;
	private static int height = 35;
	// private static char[] ch =
	// "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789".toCharArray();
	private static char[] ch = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

	public void doGet(HttpServletRequest request, HttpServletResponse response)

	throws ServletException, IOException {

		this.doPost(request, response);

	}

	// 生成数字和字母的验证码
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 在内存中创建图象
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 生成随机类
		Random random = new Random();

		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(4位数字)
		StringBuffer sb = new StringBuffer();
		int index, len = ch.length;
		for (int i = 0; i < 4; i++) {
			index = random.nextInt(len);
			g.setColor(new Color(random.nextInt(88), random.nextInt(188),
					random.nextInt(255)));
			g.setFont(new Font("Arial", Font.ITALIC, 28));// 输出的字体和大小
			g.drawString("" + ch[index], (i * 15) + 6, 26);// 写什么数字，在图片的什么位置画
			sb.append(ch[index]);
		}
		// 将认证码存入SESSION
		request.getSession().setAttribute("piccode", sb.toString());
		request.getSession().setAttribute(request.getSession().getId(), sb.toString());
		
		// 图象生效
		g.dispose();
		ServletOutputStream responseOutputStream = response.getOutputStream();
		// 输出图象到页面
		ImageIO.write(image, "JPEG", responseOutputStream);

		// 以下关闭输入流！
		responseOutputStream.flush();
		responseOutputStream.close();
		 
	}

	protected void ValidateCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 在内存中创建图象
		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 得到该图片的绘图对象
		Graphics g = img.getGraphics();

		Random r = new Random();
		Color c = new Color(200, 150, 255);
		g.setColor(c);

		// 填充整个图片的颜色
		g.fillRect(0, 0, width, height);

		// 向图片中输出数字和字母
		StringBuffer sb = new StringBuffer();
		int index, len = ch.length;
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			g
					.setColor(new Color(r.nextInt(88), r.nextInt(188), r
							.nextInt(255)));
			g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));// 输出的字体和大小
			g.drawString("" + ch[index], (i * 15) + 6, 18);// 写什么数字，在图片的什么位置画
			sb.append(ch[index]);
		}

		request.getSession().setAttribute("piccode", sb.toString());
		ServletOutputStream responseOutputStream = response.getOutputStream();
		ImageIO.write(img, "JPG", responseOutputStream);
		responseOutputStream.flush();
		responseOutputStream.close();
	}

	Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 得到某一个类的路径
	 * 
	 * @param name
	 * @return
	 */
	public static String getPath(Class name) {
		String strResult = null;
		if (System.getProperty("os.name").toLowerCase().indexOf("window") > -1) {
			strResult = name.getResource("/").toString().replace("file:/", "")
					.replace("%20", " ");
		} else {
			strResult = name.getResource("/").toString().replace("file:", "")
					.replace("%20", " ");
		}
		return strResult;
	}
}
