package com.melchi.external.module.prod.model;

import lombok.Data;

@Data
public class QnaAnswerInfo {

	private long seq;
	private String subject;
	private String contents;
	private String regdate;
	private String writertype;
	private String writerid;
	private long parentseq;
}
