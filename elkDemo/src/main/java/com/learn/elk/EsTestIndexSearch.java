package com.learn.elk;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;

public class EsTestIndexSearch {


    public static void main(String[] args) {
        RestHighLevelClient restHighLevelClient =new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.30.133",9200,"http"))
        );
        GetIndexRequest getIndexRequest = new GetIndexRequest();


    }
}
