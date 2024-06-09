package com.redis.springdataredis.repo;

import com.redis.springdataredis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    public static final String HASH_KEY ="Product";

    @Autowired
    private RedisTemplate template;

    public Product save(Product p){
        template.opsForHash().put(HASH_KEY,p.getId(),p);
        return p;
    }

    public List<Product> getAllProducts(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Product getById(int id){
        System.out.println("hit the db");
        return (Product)template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteProduct(Product p){
        template.opsForHash().delete(HASH_KEY,p.getId());
        return "Product removed!";
    }
}
