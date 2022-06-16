package com.learn.elk;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.elk.pojo.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsTestDocUpdate {


    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient =new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.30.133",9200,"http"))
        );

        // 插入数据
        UpdateRequest indexRequest =new UpdateRequest();

        // 插入json
        indexRequest.index("user").id("001");


        User user = new User();
        user.setAge(1);
        user.setName("jiang");
        user.setSex("男");

        ObjectMapper mapper =new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        indexRequest.doc(XContentType.JSON,"sex","女");

        // 相应
        UpdateResponse re = restHighLevelClient.update(indexRequest, RequestOptions.DEFAULT);
        System.out.println(re.getResult());


        restHighLevelClient.close();
    }
}
