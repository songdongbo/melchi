package com.melchi.external.common.model;

import lombok.Data;

@Data
public class Period {
	private String type;
	private String startDate;
	private String endDate;
	
	/**
	 * 시작시간을 리턴(시작시간은 0시 0분 0초를 기준으로 셋팅)
	 * @return
	 */
	public String getStartTime()
	{
		if(startDate != null)
		{
			return startDate + "000000";
		}
		return startDate;
	}
	
	/**
	 * 시작시간을 리턴(시작시간은 0시 0분 0초를 기준으로 셋팅)
	 * @return
	 */
	public String getEndTime()
	{
		if(endDate != null)
		{
			return endDate + "235959";
		}
		return endDate;
	}
}
