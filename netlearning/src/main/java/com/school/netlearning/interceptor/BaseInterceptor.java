package com.school.netlearning.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

//        logger.info("RX REQ IP: " + request.getRemoteAddr());
//        logger.info("RX REQ PATH: " + request.getMethod() + " " + request.getRemoteHost() + ":" + request.getServerPort() + request.getRequestURI());
//        logger.info("RX REQ Param: " + ((request.getParameterMap() == null || request.getParameterMap().size() == 0) ? null : objectMapper.writeValueAsString(request.getParameterMap())));

        return true;
    }
}
