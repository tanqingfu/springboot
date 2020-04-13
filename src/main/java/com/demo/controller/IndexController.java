package com.demo.controller;

import com.demo.common.Globals;
import com.demo.common.InquiryCondition;
import com.demo.common.JsonData;
import com.demo.meta.user;
import com.demo.service.userService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class IndexController extends BaseController{

    @Resource
    userService userService;
    /**
     * 项目运行首页
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public  String index(HttpServletRequest request, HttpServletResponse response) throws IOException{
        InquiryCondition InquiryCondition = new InquiryCondition();
        int total = userService.countALL(InquiryCondition);
        setPagePrmt_line(request, InquiryCondition, total, "1", 10);
        List<user> list = userService.selectAll(InquiryCondition);
        request.setAttribute("list",list);
        System.out.println(Globals.WWW_HOST);
        return "index";

    }

    /**
     * 获取数据 带分页
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getData",method = RequestMethod.POST)
    public void getData(HttpServletRequest request,HttpServletResponse response) throws  IOException{
        response.setContentType("text/html;charset=UTF-8"); //解决返回数据中文出现乱码
        InquiryCondition InquiryCondition = new InquiryCondition();
        String size = "10";//request.getParameter("size");
        String page = "1";//request.getParameter("page");
        PrintWriter out = null;
        JsonData JsonData = new JsonData();
        try {
            int total = userService.countALL(InquiryCondition);
            setPagePrmt_line(request, InquiryCondition, total, page, Integer.parseInt(size));
            List<user> list = userService.selectAll(InquiryCondition);
            out = response.getWriter();
            if(list.size()>0){
                JsonData = new JsonData(Globals.SUCCESS,"",list,total);
            }else{
                JsonData = new JsonData(Globals.SYSTEM_BUSY,"","");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        out.write(JsonData.toJsonString());

    }
}
