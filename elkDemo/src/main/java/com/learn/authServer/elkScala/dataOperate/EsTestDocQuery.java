package com.learn.authServer.elkScala.dataOperate;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class EsTestDocQuery {

    /**
     * 全量查询
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient =new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.30.133",9200,"http")));


        SearchRequest request =new SearchRequest();

        request.indices("user");

        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));


        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }


        restHighLevelClient.close();
    }
}
