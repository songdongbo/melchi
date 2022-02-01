package com.melchi.external.common.model;

import lombok.Data;

import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.melchi.external.common.model.request.BaseRequest;

@Data
public class PageInfo implements BaseRequest {
	private Integer page;
	private Integer size;
	private Integer resultCount;
	private Integer totalCount;
	
	@JsonIgnore
	private Integer startRowNum;
	@JsonIgnore
	private Integer endRowNum;
	
	public PageInfo() {}
	
	public PageInfo(int page, int size) {
		this.page = page;
		this.size = size;
	}
	
	public PageInfo(int page, int size, int resultCount, int totalCount) {
		this.page = page;
		this.size = size;
		this.resultCount = resultCount;
		this.totalCount = totalCount;
	}
	
	@Override
	public void validate() throws MethodArgumentNotValidException, BleApiException 
	{
	}
	
	/*for paging*/
	public Integer getStartRowNum()
	{
		return ((page-1)*size)+1;
	}
	
	/*for paging*/
	public Integer getEndRowNum()
	{
		return (page)*size;
	}
}
