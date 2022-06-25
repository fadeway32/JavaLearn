package com.learn.authServer.elkScala.data;

import com.learn.authServer.elkScala.pojo.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {
}
