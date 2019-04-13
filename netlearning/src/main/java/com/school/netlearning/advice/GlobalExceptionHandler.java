package com.school.netlearning.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理类；所有Controller中的异常都会到这里
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result globalErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        Result result = ResultUtil.fail(e.getMessage());

        LOGGER.error("请求异常：" + objectMapper.writeValueAsString(result));

        return result;
    }
}
