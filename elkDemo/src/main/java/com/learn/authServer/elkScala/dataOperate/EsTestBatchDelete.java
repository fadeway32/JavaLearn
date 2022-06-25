package com.learn.authServer.elkScala.dataOperate;


import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class EsTestBatchDelete {


    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient =new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.30.133",9200,"http"))
        );

        BulkRequest bulkRequest =new BulkRequest();


        bulkRequest.add(new DeleteRequest().index("user").id("1002"));


        BulkResponse responses = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(responses.getTook());
        System.out.println(responses.getItems());


        restHighLevelClient.close();
    }
}
