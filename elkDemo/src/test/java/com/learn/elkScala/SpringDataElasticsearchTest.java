package com.learn.elkScala;

import com.learn.elkScala.data.ProductDao;
import com.learn.elkScala.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:applicaiton.properties")
public class SpringDataElasticsearchTest {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;


    @Autowired
    private ProductDao productDao;

    /**
     * 创建索引
     */
    @Test
    public  void  createTest(){

        System.out.println("创建索引");
    }

    /*
     * 删除索引
     */
    @Test
    public  void deleteTest(){
        String flag = elasticsearchTemplate.delete(Product.class);
        System.out.println("删除索引"+flag);
    }


    /**
     * 保存文档
     */
    @Test
    public  void sava(){
        Product product = new Product();
        product.setId(21L);
        product.setTitle("小米手机");
        product.setPrice(3999D);
        product.setCategory("手机");
        productDao.save(product);
    }


    /**
     * 更新文档
     */
    @Test
    public  void update(){
        Product product = new Product();
        product.setId(2L);
        product.setTitle("华为手机123");
        product.setPrice(3999D);
        product.setCategory("手机");
        productDao.save(product);
    }


    /**
     * findById
     */
    @Test
    public  void findById(){
        Product product = productDao.findById(2L).get();
        System.out.println(product);
    }

    /**
     * findByAll
     */
    public  void findByAll(){
        for (Product product : productDao.findAll()) {
            System.out.println(product);
        }
    }

    /**
     * 批量新增
     */
    @Test
    public  void batchInsert(){
        ArrayList<Product> productArrayList = new ArrayList<Product>();
        for (int i = 3; i < 20; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setCategory("手机");
            product.setPrice((double) (3999+i*100));
            product.setTitle("苹果手机+"+i);
            product.setImages("www.baidu.com");
            productArrayList.add(product);
        }
        productDao.saveAll(productArrayList);
    }

    /**\
     * 分页
     */
    @Test
    public  void findByPage(){
        Sort id = Sort.by(Sort.Direction.DESC, "id");
        int currentPage =0;
        int pageSize=5;
        PageRequest of = PageRequest.of(currentPage, pageSize);
        Page<Product> all = productDao.findAll(of);
        for (Product product : all.getContent()) {
            System.out.println(product);
        }

    }
}
