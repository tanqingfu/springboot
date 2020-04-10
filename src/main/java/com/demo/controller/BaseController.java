package com.demo.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.common.InquiryCondition;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.sun.org.apache.xml.internal.security.utils.Base64;



public class BaseController {

	/**
	 * @Description: 设置Page参数
	 */
	protected InquiryCondition setPagePrmt(HttpServletRequest request, InquiryCondition inqcond, int total, String page) {
		if (page != null && !page.trim().equals("")) {
			inqcond.getPage().setCurrentPage(Integer.parseInt(page));
		}
		//inqcond.getPage().setShowLinePage(10);
		inqcond.getPage().setTotalSize(total);
		inqcond.getPage().calculateRecordNum();
		int pageNum = total <= inqcond.getPage().getShowLinePage() ? 1 : total / inqcond.getPage().getShowLinePage();
		if (total > inqcond.getPage().getShowLinePage() && total % inqcond.getPage().getShowLinePage() != 0) {
			pageNum += 1;
		}
		inqcond.getPage().setPageNum(pageNum);
		return inqcond;
	}

	/**
	 *
	 * @param:@param request  请求
	 * @param:@param inqcond  存放变量
	 * @param:@param total  总记录数
	 * @param:@param page   当前页
	 * @param:@param line   每页显示条数
	 * @param:@return
	 * @author:zwd
	 * @date:2018-11-29
	 */
	protected InquiryCondition setPagePrmt_line(HttpServletRequest request, InquiryCondition inqcond,int total, String page,int line) {
		if(page != null && !page.trim().equals("")){
			inqcond.getPage().setCurrentPage(Integer.parseInt(page));  //设置当前页
		}
		inqcond.getPage().setTotalSize(total); //设置总记录数
		inqcond.getPage().calculateRecordNum();	    //根据当前页，总记录数计算记录起始、截止数
		int pageNum = total <= inqcond.getPage().getShowLinePage() ? 1 : total / inqcond.getPage().getShowLinePage();  //总页数
		//如果总记录数大于当前显示的记录数  则页自加
		if (total > inqcond.getPage().getShowLinePage() && total % inqcond.getPage().getShowLinePage() != 0) {
			pageNum += 1;
		}
		inqcond.getPage().setPageNum(pageNum);//设置总页数
		return inqcond;
	}

	/**
	 * 添加cookie
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 */
	public void addCookie(HttpServletResponse response, String cookieName, String cookieValue) {
		try {
			Cookie cookie = new Cookie(cookieName, Base64.encode(URLEncoder.encode(cookieValue, "UTF-8").getBytes()));
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			//Log.error("保存用户名Cookie", e);
		}
	}

	/**
	 * 读取cookie
	 * @param request
	 * @param cookieName cookie名称
	 * @return
	 */
	public String readCookie(HttpServletRequest request, String cookieName){
		try {
			Cookie[] cookies = request.getCookies();
			if (null != cookies) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie c = cookies[i];
					if (cookieName.equals(c.getName())) {
						String value = URLDecoder.decode(new String(Base64.decode(c.getValue()),"UTF-8"), "UTF-8");
						return value;
					}
				}
			}
			return null;
		} catch (Exception e) {
			//Log.error("读取Cookie", e);
			return null;
		}
	}

	/**
	 * 移除cookie
	 * @param response
	 * @param cookieName
	 */
	public void removeCookie(HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	public void plag(HttpServletRequest request){
		//String str = request.getRequestURL().replaceAll("/","").replaceAll(".ftl","");
	}

	/**
	 * 动态生成分页
	 * @param request
	 * @param url
	 * @param page
	 * @param
	 * @return
	 */
	protected String setPage(HttpServletRequest request,String url,int page,int totalCount ){
		//总页数
		int pageNum;
		//当前页
		int currentPage = page;
		//每页多少条
		int size = 10;

		pageNum = totalCount; //数据条数
		pageNum = totalCount <= size ? 1 : totalCount/size ;
		if(totalCount >= size && totalCount%size != 0){
			pageNum += 1;
		}

		String topPage = "";
		if(pageNum >= 1 && pageNum <=4){
			for(int j =1 ;j<=pageNum ;j++){
				if(j == currentPage){
					topPage += "<a class='active' style='cursor:pointer;' href='"+url+"?page="+currentPage+"'>"+j+"</a>";
				}else{
					topPage += "<a class='' style='cursor:pointer;' href='"+url+"?page="+currentPage+"'>"+j+"</a>";
				}
			}
		}else if(pageNum >4){

			int pre = currentPage-1;//上一页
			int next = currentPage+1;//下一页
			if(currentPage == 1){
				topPage += "<a class='active' style='cursor:pointer;' href='"+url+"?page="+currentPage+"'>1</a><a class='' style='cursor:pointer;' href='"+url+"?page="+(currentPage+1)+"'>2</a><a class='' style='cursor:pointer;' href='"+url+"?page="+(currentPage+2)+"'>3</a><em>...</em><a style='cursor:pointer;' href='"+url+"?page="+pageNum+"'>"+pageNum+"</a>";
			}else if(currentPage == pageNum){
				topPage += "<a class='' style='cursor:pointer;' href='"+url+"?page=1'>1</a><em>...</em><a class='' style='cursor:pointer;' href='"+url+"?page="+(pageNum-2)+"')'>"+(pageNum-2)+"</a><a class='' style='cursor:pointer;' href='"+url+"?page="+(pageNum-1)+"'>"+(pageNum-1)+"</a><a class='active' style='cursor:pointer;' href='"+url+"?page="+pageNum+"'>"+pageNum+"</a>";
			}else if((currentPage+1) == pageNum){
				topPage += "<a class='' style='cursor:pointer;' href='"+url+"?page=1'>1</a><em>...</em><a class='active' style='cursor:pointer;' href='"+url+"?page="+currentPage+"'>"+currentPage+"</a><a style='cursor:pointer;' href='"+url+"?page="+pageNum+"'>"+pageNum+"</a>";
			}else if((currentPage+2) == pageNum){
				topPage += "<a class='' style='cursor:pointer;' href='"+url+"?page=1'>1</a><em>...</em><a class='active' style='cursor:pointer;' href='"+url+"?page="+currentPage+"'>"+currentPage+"</a><a class='' style='cursor:pointer;' href='special_details?page="+(currentPage+1)+"'>"+(currentPage+1)+"</a><a style='cursor:pointer;' href='"+url+"?page="+pageNum+"'>"+pageNum+"</a>";
			}else{
				topPage += "<a class='active' style='cursor:pointer;' href='"+url+"?page="+currentPage+"'>"+currentPage+"</a><a class='' style='cursor:pointer;' href='"+url+"?page="+(currentPage+1)+"'>"+(currentPage+1)+"</a><a class='' style='cursor:pointer;' href='"+url+"?page="+(currentPage+2)+"'>"+(currentPage+2)+"</a><em>...</em><a style='cursor:pointer;' href='"+url+"?page="+pageNum+"'>"+pageNum+"</a>";
			}
		}
		String table = "";
		if(totalCount >=1){
			if(currentPage == 1){
				if(pageNum == 1){
					table = table+  "<li><a href='"+url+"?page=1' style='cursor:pointer;'>首页</a><span><a href='"+url+"?page="+currentPage+"' class='pre' style='cursor:pointer;'><i></i></a>"+topPage+"<a  href='"+url+"?page="+currentPage+"' class='next' style='cursor:pointer;'><i></i></a></span><a style='cursor:pointer;'  href='"+url+"?page="+pageNum+"'>尾页</a></li>";
				}else{
					table = table+  "<li><a href='"+url+"?page=1' style='cursor:pointer;'>首页</a><span><a href='"+url+"?page="+currentPage+"' class='pre' style='cursor:pointer;'><i></i></a>"+topPage+"<a  href='"+url+"?page="+(currentPage+1)+"' class='next' style='cursor:pointer;'><i></i></a></span><a style='cursor:pointer;'  href='"+url+"?page="+pageNum+"'>尾页</a></li>";
				}

			}else if(currentPage >1){
				if(currentPage != pageNum){
					table = table+  "<li><a href='"+url+"?page=1' style='cursor:pointer;'>首页</a><span><a href='"+url+"?page="+(currentPage-1)+"' class='pre' style='cursor:pointer;'><i></i></a>"+topPage+"<a href='"+url+"?page="+(currentPage+1)+"' class='next' style='cursor:pointer;'><i></i></a></span><a style='cursor:pointer;'  href='"+url+"?page="+pageNum+"'>尾页</a></li>";
				}else{
					table = table+  "<li><a href='"+url+"?page=1' style='cursor:pointer;'>首页</a><span><a href='"+url+"?page="+(currentPage-1)+"' class='pre' style='cursor:pointer;'><i></i></a>"+topPage+"<a href='"+url+"?page="+pageNum+"' class='next' style='cursor:pointer;'><i></i></a></span><a style='cursor:pointer;'  href='"+url+"?page="+pageNum+"'>尾页</a></li>";
				}
			}
		}
		return table;
	}

















}
