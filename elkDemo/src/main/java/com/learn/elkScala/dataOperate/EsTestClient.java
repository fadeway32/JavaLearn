package com.learn.elkScala.dataOperate;



import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class EsTestClient {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient =new RestHighLevelClient(
                 RestClient.builder(new HttpHost("192.168.30.133",9200,"http"))
        );
        restHighLevelClient.close();
    }
}
