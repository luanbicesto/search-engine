package com.hackaton.search.service;

import com.hackaton.search.model.Sku;
import com.hackaton.search.repository.SkuRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SkuService {

	private SkuRepository skuRepository;

	public SkuService(final SkuRepository skuRepository) {
		this.skuRepository = skuRepository;
	}

	public List<Sku> findSkusByText(String text) {
		return skuRepository.findSkusByText(text);
	}

	public Sku addSku(Sku sku) {
		skuRepository.addSku(sku);
		return sku;
	}
}
