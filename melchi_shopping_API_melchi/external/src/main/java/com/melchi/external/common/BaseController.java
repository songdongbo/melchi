package com.melchi.external.common;

import static com.melchi.external.common.BaseConst.Code.*;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melchi.external.common.model.BleApiException;
import com.melchi.external.common.model.response.Header;
import com.melchi.external.common.model.response.Payload;
import com.melchi.external.common.model.response.Response;
import com.melchi.external.common.model.response.ResponseHeader;

@Controller
public class BaseController {

	private final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	
	public final static String RESPONSE_HTML = "RESPONSE_HTML";
	public final static String RESPONSE_JSON = "RESPONSE_JSON";
	
	/**
	 * @param e Biz Logic 외의 예상하지 못한 Runtime Exception에 대한 예외를 처리한다.
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseEntity<Response>  runtimeException(Exception e, HttpServletResponse httpServletResponse) {
		e.printStackTrace();
		LOGGER.error(">>> RuntimeException : {}", e);
		Response response;
		if (e instanceof HttpMessageNotReadableException) {
			//response = new Response(new BleApiException(INVALID_ERROR, "Wrong Parameter : " + e.getLocalizedMessage()));
			response = new Response(new BleApiException(SYSTEM_ERROR, "잘못 된 값이 전달되어 에러가 발생했습니다. 관리자에게 문의해주세요."));
		} else {
			//response = new Response(new BleApiException(SYSTEM_ERROR, "RuntimeException : " + e.getLocalizedMessage()));
			response = new Response(new BleApiException(SYSTEM_ERROR, "처리 중 에러가 발생했습니다. 관리자에게 문의해주세요."));
		}
		
        return new ResponseEntity<Response>(response, makeHttpStatusCode(response));

	}	
	
	
	/**
	 * BLE Gateway의 Biz Logic에서 발생하는 예외 상황에 대해 지정된 형식의 json으로 Http Response를 생성하고 전달한다.
	 * @param e {@link BleApiException}
	 * @return response {@link Response}
	 */
	@ExceptionHandler(BleApiException.class) 
	@ResponseBody
	public ResponseEntity<Response>  handleBlException(HttpServletResponse httpServletResponse, BleApiException e){
		LOGGER.error(">>> BleApiException : {}" , e.getErrorMessage());		
		Response response = new Response(e);		
		ResponseEntity<Response> result = new ResponseEntity<Response>(response, makeHttpStatusCode(response));
		return result;
	}
	
	/**
	 * Valid Annotation의 validation 실패 시 지정된 형식 json으로 Http Respons를 생성하고 전달한다.
	 * */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<Response> handleJsr303ValidException(HttpServletResponse httpServletResponse, MethodArgumentNotValidException e) {
		BindingResult error = e.getBindingResult();
		LOGGER.error(">>> ValidationException : {}", error);
        Response response = new Response(new BleApiException(INVALID_ERROR, "Request Parameter Not Valid[" + error.getFieldError().getField() + "]: " + error.getFieldError().getDefaultMessage()));
        return new ResponseEntity<Response>(response, makeHttpStatusCode(response));
	}
	
	private HttpStatus makeHttpStatusCode(Response resp) {
		Payload payload = resp.getResponse(); 
		if (payload instanceof ResponseHeader) {
			ResponseHeader respHeader = (ResponseHeader) payload;
			Header header = (Header) respHeader.getHeader();
			int statusCode = header.getStatusCode();
			
			if(statusCode == AUTH_TOKEN_EXPIRED)
			{
				return HttpStatus.UNAUTHORIZED;
			}
			else
			{
				switch (statusCode / 100) {
				case 2 : return HttpStatus.OK;
				case 4 : return HttpStatus.BAD_REQUEST;
				case 5 : return HttpStatus.FORBIDDEN;
				default : return HttpStatus.INTERNAL_SERVER_ERROR;
				}
			}
		} else {
			return HttpStatus.OK;
		}

	}
	
	protected String getResponseType(String responseTpl, String errorTpl) {

        if(null != responseTpl && 0 < responseTpl.length()
                && null != errorTpl && 0 < errorTpl.length()) {

            return RESPONSE_HTML;

        }  else {

            return RESPONSE_JSON;
        }
    }
	
	protected void setErrorResponse(HttpServletResponse response, String responseType, String errorResponseTpl, Throwable throwable, String replaceErrorTplKey) throws Exception
	{ 

        String responseBody = null;

        switch(responseType) {

            case RESPONSE_HTML:

                response.setHeader("Content-Type", MediaType.TEXT_HTML_VALUE);
                
                if(null != errorResponseTpl && 0 < errorResponseTpl.length() && null != throwable.getMessage()) {
                    responseBody = errorResponseTpl.replace(replaceErrorTplKey, throwable.getMessage());
                    responseBody = responseBody.replace("{ISS}", "<script type='text/javascript'>");
                    responseBody = responseBody.replace("{/ISS}", "</script>");
                } else {
                    responseBody = errorResponseTpl.replace(replaceErrorTplKey, "알수없는 오류발생, 시스템오류");
                    responseBody = responseBody.replace("{ISS}", "<script type='text/javascript'>");
                    responseBody = responseBody.replace("{/ISS}", "</script>");
                }

                break;

            default:
            case RESPONSE_JSON:
                throw new Exception(throwable);
        }

        try {

            response.getWriter().print(responseBody);

        } catch (IOException e) {
            throw new Exception("시스템오류");
        }
    }
	
	public <T> void setResponseByType(HttpServletResponse response, T returnDTO, String responseType, String successResponseTpl, String replaceSuccessTplKey) throws Exception
	{
		String responseBody = null;
		ObjectMapper mapper = new ObjectMapper();
		switch(responseType)
		{
			case RESPONSE_HTML:
				response.setHeader("Content-Type", MediaType.TEXT_HTML_VALUE);
				try
				{
					responseBody = mapper.writeValueAsString(returnDTO);
				}
				catch (JsonProcessingException e)
				{
					throw new Exception("파라미터 오류발생, 시스템 오류");
				}
				if(null != successResponseTpl && 0 < successResponseTpl.length())
				{
					responseBody = successResponseTpl.replace(replaceSuccessTplKey, responseBody);
					responseBody = responseBody.replace("{ISS}", "<script type='text/javascript'>"); 
					responseBody = responseBody.replace("{/ISS}", "</script>");
				}
				break;
			default:
				case RESPONSE_JSON:
					response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
					try
					{
						responseBody = mapper.writeValueAsString(returnDTO);
					}
					catch(JsonProcessingException e)
					{
						throw new Exception("Json Processing 오류발생, 시스템 오류");
					}
					break;
		}
		try
		{
			response.getWriter().print(responseBody);
		}
		catch (IOException e)
		{
			throw new Exception("시스템오류");
		}
	}
	
}
