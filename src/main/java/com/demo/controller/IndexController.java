package com.demo.controller;

import com.demo.common.Globals;
import com.demo.common.InquiryCondition;
import com.demo.meta.user;
import com.demo.service.userService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
}
