package com.mmall.common;

import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 当定义了一个Resolver且实现了HandlerExceptionResolver的时候，全局异常在http进行返回的时候就会被捕捉到，
// 所以在这里进行异常处理逻辑
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handle, Exception e) {
        // 取出当前访问到url
        String url = request.getRequestURI().toString();
        ModelAndView mv;
        String defaultMsg = "System error";

        // 如何区分当前请求是数据请求还是页面请求：
        // 1 从request里取出当前提交请求的header
        // 2 通过接口，比如定义系统内所有数据请求用json结尾，页面请求用.page结尾
        if (url.endsWith(".json")) {
            if (e instanceof PermissionException || e instanceof ParamException) {
                JsonData result = JsonData.fail(e.getMessage());
                mv = new ModelAndView("jsonView", result.toMap());
            } else {
                log.error("unknown json exception, url:" + url, e);
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonview", result.toMap());
            }
        } else if (url.endsWith(".page")){ // 这里要求项目中所有请求page页面，都用.page结尾
            log.error("unknown page exception, url:" + url, e);
            JsonData result = JsonData.fail(defaultMsg);
            // 会去jsp路径下找exception的jsp页面
            mv = new ModelAndView("exception", result.toMap());
        } else {
            log.error("unknown exception, url:" + url, e);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonview", result.toMap());
        }


        return mv;
    }
}
