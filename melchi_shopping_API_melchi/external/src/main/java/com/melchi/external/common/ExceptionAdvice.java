package com.melchi.external.common;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.melchi.external.common.model.BleApiException;
import com.melchi.external.common.model.response.Response;
 
   
@ControllerAdvice    
@Order(Ordered.HIGHEST_PRECEDENCE)   
public class ExceptionAdvice  {
 
   static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
   
   @Autowired
   SqlSessionTemplate basicSqlSessionTemplate;
   
   //비즈니스 익셉션
    @ExceptionHandler(BleApiException.class)    
    protected Response HandleBleApiException(HttpServletRequest request, BleApiException e) {
       e.printStackTrace(); 
       Response response = new Response(e);
        
       return response;           
    }    

   //위에서 처리가 안된 모든 Exception
    @ExceptionHandler(Exception.class)    
    protected Response HandleException(HttpServletRequest request, Exception e) {
       e.printStackTrace(); 
       Response response = new Response(new BleApiException(500, "알 수 없는 오류 발생\n관리자에게 문의하세요."));
       
       return response;    
    }    

}
