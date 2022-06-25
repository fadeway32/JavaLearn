package com.learn.authServer.elkScala.dataOperate;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

public class EsTestIndexCreate {


    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.30.133", 9200, "http"))
        );
        // 创建索引
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user1");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);



        System.out.println("索引操作状态" + createIndexResponse.isAcknowledged());

        restHighLevelClient.close();

    }
}
