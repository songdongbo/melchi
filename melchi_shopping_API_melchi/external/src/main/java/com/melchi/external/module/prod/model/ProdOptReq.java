package com.melchi.external.module.prod.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProdOptReq {

	@NotNull
	private long productcd;
	private List<ProdOpt> op1_data;
	private List<ProdOpt> op2_data;
	private List<ProdOpt> op3_data;
}
