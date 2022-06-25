package com.learn.authServer.elkScala.dataOperate;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class EsTestDocAverageQuery {

    /**
     * 模糊查询
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient =new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.30.133",9200,"http")));

        SearchRequest searchRequest = new SearchRequest("user");

        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();



        AggregationBuilder  aggregationBuilder = AggregationBuilders.max("maxAge").field("age");




        searchSourceBuilder.aggregation(aggregationBuilder);







        // 关键到resquest上
        searchRequest.source(searchSourceBuilder);

        // 获取结果
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response);
        System.out.println(response.getTook());
        System.out.println(response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }

        restHighLevelClient.close();
    }
}
