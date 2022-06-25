package com.learn.authServer.elkScala;

import com.learn.authServer.elkScala.data.ProductDao;
import com.learn.authServer.elkScala.pojo.Product;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:applicaiton.properties")
public class SpringDataDocTest {

    @Autowired
    private ProductDao productDao;



    @Test
    public void test(){
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");
        Iterable<Product> search = productDao.search(termQueryBuilder);
        for (Product product : search) {
            System.out.println(product);
        }
    }
}
