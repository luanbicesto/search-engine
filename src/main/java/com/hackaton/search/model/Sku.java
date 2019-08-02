package com.hackaton.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "sku-index", type = "sku")
public class Sku {

	@Id
	private String id;
	private String description;
	private String imageUrl;

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getImageUrl() {

		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {

		this.imageUrl = imageUrl;
	}
}
