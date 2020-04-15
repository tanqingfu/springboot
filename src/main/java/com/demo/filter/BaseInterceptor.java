package com.demo.filter;

import com.demo.meta.user;
import com.demo.util.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class BaseInterceptor implements HandlerInterceptor {
    /*1:先创建一个类如BaseInterceptor
    2:实现HandlerInterceptor接口
    3:重写其方法*/

    /**
     * 预处理回调方法，实现处理器的预处理
     * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("开始拦截.........");
        request.setAttribute("time", DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        //业务代码
        /*try {
            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
            user user=(user)request.getSession().getAttribute("user");
            user = new user();
            user.setNickName("和自己对话");
            if(user!=null){
                return true;
            }
            response.sendRedirect(request.getContextPath()+"test");
            return  false;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return true;
    }
    /**
     * 后处理回调方法，实现处理器（controller）的后处理，但在渲染视图之前
     * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }
    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，
     * 如性能监控中我们可以在此记录结束时间并输出消耗时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，
     * 但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
