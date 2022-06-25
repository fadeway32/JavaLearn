package com.learn.authServer.elkScala.dataOperate;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class EsTestDocRangeQuery {

    /**
     * 组合查询
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient =new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.30.133",9200,"http")));

        SearchRequest searchRequest = new SearchRequest("user");

        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();
        // 范围查找
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");
        rangeQueryBuilder.from(34);
        rangeQueryBuilder.to(35);


        searchSourceBuilder.query(rangeQueryBuilder);



        // 关键到resquest上
        searchRequest.source(searchSourceBuilder);

        // 获取结果
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }

        restHighLevelClient.close();
    }
}
