//package com.demo.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.demo.common.Globals;
//
//import com.qq.connect.QQConnectException;
//import com.qq.connect.api.OpenID;
//import com.qq.connect.api.qzone.UserInfo;
//import com.qq.connect.javabeans.AccessToken;
//import com.qq.connect.javabeans.qzone.UserInfoBean;
//import com.qq.connect.oauth.Oauth;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//
//
//
//@Controller
//public class QQloginController extends BaseController{
//
//	@RequestMapping(value = "/qqlogin", method = RequestMethod.GET)
//	public String qqlogin(ModelMap model, HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		//Object demo_access_token = request.getSession().getAttribute("demo_access_token");
//		//if(demo_access_token == null){
//			try {
//				response.sendRedirect(new Oauth().getAuthorizeURL(request));
//			} catch (QQConnectException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		//}else{
//			//response.sendRedirect("test");
//		//}
//
//		return "qqlogin";
//
//	}
//
//	/**
//	 * 请求跳转到QQ登录授权
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/get_qqlogin", method = RequestMethod.POST)
//	public void get_qqlogin(ModelMap model, HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		response.setContentType("text/html;charset=utf-8");
//		 String msg = "";
//        try {
//            //response.sendRedirect(new Oauth().getAuthorizeURL(request));
//            msg = new Oauth().getAuthorizeURL(request);
//        } catch (QQConnectException e) {
//            e.printStackTrace();
//        }
//        response.getWriter().write(msg);
//	}
//
//	/**
//	 * QQ授权登录回调地址
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/qqlogin_data", method = RequestMethod.GET)
//	public String qqlogin_data(ModelMap model, HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		response.setContentType("text/html; charset=utf-8");
//
//        PrintWriter out = response.getWriter();
//
//        try {
//            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
//
//            String accessToken   = null,
//                   openID        = null;
//            long tokenExpireIn = 0L;
//
//            if (accessTokenObj.getAccessToken().equals("")) {
////                我们的网站被CSRF攻击了或者用户取消了授权
////                做一些数据统计工作
//                System.out.print("没有获取到响应参数");
//            } else {
//                accessToken = accessTokenObj.getAccessToken();
//                tokenExpireIn = accessTokenObj.getExpireIn();
//
//                request.getSession().setAttribute("demo_access_token", accessToken);
//                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));
//
//                // 利用获取到的accessToken 去获取当前用的openid -------- start
//                OpenID openIDObj =  new OpenID(accessToken);
//                openID = openIDObj.getUserOpenID();
//                request.getSession().setAttribute("demo_openid", openID);
//                // 利用获取到的accessToken 去获取当前用户的openid --------- end
//                 out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start </p>");
//
//                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
//                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
//                 out.println("<br/>");
//                if (userInfoBean.getRet() == 0) {
//                     out.println("用户昵称："+userInfoBean.getNickname() + "<br/>");
//                     out.println("性别："+userInfoBean.getGender() + "<br/>");
//                     out.println("黄钻等级： " + userInfoBean.getLevel() + "<br/>");
//                     out.println("会员 : " + userInfoBean.isVip() + "<br/>");
//                     out.println("黄钻会员： " + userInfoBean.isYellowYearVip() + "<br/>");
//                     out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL30() + "><br/>");
//                     out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL50() + "><br/>");
//                     //没有替换之前是QQ空间的头像图片地址
//                     out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL100().replace("qzapp.qlogo.cn", "thirdqq.qlogo.cn").replace("qzapp", "qqapp") + "><br/>");
//                     //"http://thirdqq.qlogo.cn/qqapp/101483567/8AD0B25EA6EFF86F0D77B707DBEB1961/100"  QQ头像地址
//                     // http://qzapp.qlogo.cn/qzapp/101483567/8AD0B25EA6EFF86F0D77B707DBEB1961/30   QQ空间头像地址
//                } else {
//                     out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
//                }
//
//
//                 //response.sendRedirect("test"); 登录成功之后 跳转
//            }
//        } catch (QQConnectException e) {
//        	e.printStackTrace();
//        }
//        return "qqlogin_data";
//
//	}
//	  //判断openid是否存在。
//
//	  // 如果openid存在，则说明此用户之前登录过或者已与本地user表中的用户绑定。写入cookie，使用户为登录状态，到此结束。
//
//	  //如果用户openid不存在，则判断用户名是否存在。
//
//	  //如果用户名不存在，则直接生成新的本地用户，并绑定uid与openid。写入cookie，使用户为登录状态，到此结束。
//
//	  //如果用户名存在，提醒用户是否验证并与之绑定。如果用户选择验证，并验证通过，则与之绑定。写入cookie，使用户为登录状态，到此结束。
//
//	  //如果用户放弃验证，或者验证失败，则生成新的本地用户，并生成新的用户名，绑定uid与openid。写入cookie，使用户为登录状态，到此结束。
//
//	/**
//	 * 通过前端JS保存QQ登录成功用户信息
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/save_qqlogindata", method = RequestMethod.POST)
//	public void save_qqlogindata(ModelMap model, HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		response.setContentType("text/html; charset=utf-8");
//		String nickname = request.getParameter("nickname"); //用户名
//		String openid = request.getParameter("openid"); // 用户身份的唯一标识。建议保存在本地，以便用户下次登录时可对应到其之前的身份信息，不需要重新授权。
//		String figureurl_qq_1 = request.getParameter("figureurl_qq_1"); //用户的头像 这个地址是一定存在的
//		String token = request.getParameter("token"); //表示当前用户在此网站/应用的登录状态与授权信息，建议保存在本地。
//		response.getWriter().write(nickname+","+ Globals.SUCCESS);
//
//	}
//}
