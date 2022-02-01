package com.melchi.external.module.prod.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProdNoti {
	@NotNull
	private String seq;
	private long productcd;
	private String item;
	private String contents;
	private String isava;
}
