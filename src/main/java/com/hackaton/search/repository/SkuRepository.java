package com.hackaton.search.repository;

import com.hackaton.search.model.Sku;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.regexpQuery;

@Repository
public class SkuRepository {

	private ElasticsearchOperations elasticsearchOperations;

	public SkuRepository(final ElasticsearchOperations elasticsearchOperations) {
		this.elasticsearchOperations = elasticsearchOperations;
	}

	public void addSku(Sku sku) {
		IndexQuery indexQuery = new IndexQuery();
		indexQuery.setObject(sku);
		elasticsearchOperations.index(indexQuery);
	}

	public List<Sku> findSkusByText(String text) {
		return elasticsearchOperations.queryForList(buildQuery(text), Sku.class);
	}

	private SearchQuery buildQuery(String skuText) {
		return new NativeSearchQueryBuilder()
				.withQuery(new QueryStringQueryBuilder(skuText))
				//.withFilter(regexpQuery("id", ".*" + skuText + ".*"))
				.build();
	}
}
