package com.learn.elkScala.dataOperate;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class EsTestDocReproduceQuery {

    /**
     * 组合查询
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient =new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.30.133",9200,"http")));

        SearchRequest searchRequest1 = new SearchRequest("user");

        // 范围查询  must 必须 should 或者
        SearchSourceBuilder builder =new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("age",35));
        boolQueryBuilder.should(QueryBuilders.matchQuery("name","jiang"));
        builder.query(boolQueryBuilder);

        searchRequest1.source(builder);


        // 获取结果
        SearchResponse response = restHighLevelClient.search(searchRequest1, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit);
        }

        restHighLevelClient.close();
    }
}
