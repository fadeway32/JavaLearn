package com.learn.authServer.elkScala.dataOperate;


import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsTestBatchInsert {


    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient =new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.30.133",9200,"http"))
        );

        BulkRequest bulkRequest =new BulkRequest();


        bulkRequest.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON,"name","jack2","age",30,"sex","男"));
        bulkRequest.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON,"name","jack3","age",33,"sex","女"));
        bulkRequest.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON,"name","jack4","age",34,"sex","女"));
        bulkRequest.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON,"name","jack5","age",35,"sex","男"));
        bulkRequest.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON,"name","jack6","age",35,"sex","男"));


        BulkResponse responses = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(responses.getTook());
        System.out.println(responses.getItems());


        restHighLevelClient.close();
    }
}
