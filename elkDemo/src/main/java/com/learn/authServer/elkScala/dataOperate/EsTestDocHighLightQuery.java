package com.learn.authServer.elkScala.dataOperate;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

public class EsTestDocHighLightQuery {

    /**
     * 模糊查询
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient =new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.30.133",9200,"http")));

        SearchRequest searchRequest = new SearchRequest("user");

        SearchSourceBuilder searchSourceBuilder =new SearchSourceBuilder();

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "jack2");
        // 高亮
        HighlightBuilder highlightBuilder =new HighlightBuilder();

        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");


        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.query(termQueryBuilder);



        // 关键到resquest上
        searchRequest.source(searchSourceBuilder);

        // 获取结果
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getHits().getTotalHits());
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }

        restHighLevelClient.close();
    }
}
