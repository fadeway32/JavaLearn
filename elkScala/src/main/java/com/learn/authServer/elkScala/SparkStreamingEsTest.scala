package com.learn.authServer.elkScala

import org.apache.http.HttpHost
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.elasticsearch.action.index.{IndexRequest, IndexResponse}
import org.elasticsearch.client.{RequestOptions, RestClient, RestHighLevelClient}
import org.elasticsearch.common.xcontent.XContentType
object SparkStreamingEsTest {


  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Estest");

    val ssc = new StreamingContext(sparkConf, Seconds(3));

    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("192.168.30.33", 9200);


    ds.foreachRDD(rdd => {
      rdd.foreach(
        data => {
          val client = new
              RestHighLevelClient(RestClient.builder(new HttpHost("192.168.30.13", 9200)))
          val  request =new IndexRequest();
          val ss =data.split(" ")
          request.index("product").id(ss(0));
          val json =
            s"""
             | { "data" : "${ss(1)}"  }
             | """.stripMargin
             request.source(json,XContentType.JSON)

          val response: IndexResponse =client.index(request,RequestOptions.DEFAULT);
          println(response.getResult)
          client.close()
        }
      )
    })

    ssc.start()
    ssc.awaitTermination()


  }

}
