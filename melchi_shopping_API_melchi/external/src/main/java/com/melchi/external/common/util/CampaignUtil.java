package com.melchi.external.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.melchi.external.common.CommonCode;

public class CampaignUtil {
	
	/**
	 * 캠페인 시작일자를 기준으로 캠페인의 상태 값을 추출한다.
	 * @param startDate
	 * @return
	 * @throws Exception
	 */
	/*public static String getCampaignStatus(String startDate) throws Exception
	{
		String result = CommonCode.CAMPAIGN_STATUS_USE;
				
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String today = format.format(Calendar.getInstance().getTime());
		
		//시작일이 오늘보다 이후이면
		if(startDate.compareTo(today) > 0)
		{
			result = CommonCode.CAMPAIGN_STATUS_WAIT;
		}
		
		return result;
	}*/

}
