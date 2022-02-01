package com.melchi.external.common.handler;

import static com.melchi.external.common.BaseConst.Code.AUTH_TOKEN_EXPIRED;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.melchi.external.common.model.BleApiException;
import com.melchi.external.common.model.response.Response;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    //~ Static fields/initializers =====================================================================================

    protected static final Log logger = LogFactory.getLog(AccessDeniedHandlerImpl.class);

    //~ Instance fields ================================================================================================

    //~ Methods ========================================================================================================

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        if (!response.isCommitted()) {
        	Response resp = new Response(new BleApiException(AUTH_TOKEN_EXPIRED, "세션이 만료되었습니다. 다시 로그인해주세요."));
        	
        	String responseBody = null;
    		ObjectMapper mapper = new ObjectMapper();
    		response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    		responseBody = mapper.writeValueAsString(resp);
			response.getWriter().print(responseBody);
        }
    }
}

