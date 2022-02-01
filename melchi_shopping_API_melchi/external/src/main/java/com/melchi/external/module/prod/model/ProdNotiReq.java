package com.melchi.external.module.prod.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProdNotiReq {

	@NotNull
	private long productcd;
	private List<ProdNoti> noti_data;
}
