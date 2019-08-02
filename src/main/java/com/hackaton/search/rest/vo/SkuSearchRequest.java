package com.hackaton.search.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SkuSearchRequest {

	@JsonProperty
	private String text;

	public String getText() {

		return text;
	}

	public void setText(String text) {

		this.text = text;
	}
}
