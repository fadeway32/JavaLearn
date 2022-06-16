package com.learn.elk;


import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class EsTestIndexDelete {


    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient =new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.30.133",9200,"http"))
        );
        DeleteIndexRequest getIndexRequest = new DeleteIndexRequest("user1");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(getIndexRequest, RequestOptions.DEFAULT);


        System.out.println(response.isAcknowledged());
        restHighLevelClient.close();
    }
}
