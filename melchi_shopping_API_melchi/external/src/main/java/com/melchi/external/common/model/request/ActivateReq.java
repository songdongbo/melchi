package com.melchi.external.common.model.request;

import static com.melchi.external.common.BaseConst.Code.*;

//import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.melchi.external.common.model.BleApiException;

import lombok.Data;

@Data
public class ActivateReq implements BaseRequest {
	
//	@NotEmpty
	private String useYn;
	
	@Override
	public void validate() throws MethodArgumentNotValidException, BleApiException {
		
		if (!"Y".equals(useYn) && !"N".equals(useYn)) {
			throw new BleApiException(INVALID_ERROR, "can use only Y or N");
		} 
		
	}

}
