package com.redis.springdataredis;

import com.redis.springdataredis.entity.Product;
import com.redis.springdataredis.repo.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
@EnableCaching
public class SpringdataRedisApplication {

	@Autowired
	ProductDao repo;

	@PostMapping("/save")
	public Product save(@RequestBody Product product){
		return repo.save(product);

	}

	@GetMapping
	public List<Product> getAll(){
		return repo.getAllProducts();
	}

	@GetMapping("/{id}")
	@Cacheable(key ="#id", value ="Product", unless ="#result.price > 100000")
	public Product getById(@PathVariable int id){
		return repo.getById(id);//cache all products whose price is less than 1 lc, else fetch from db
	}

	@DeleteMapping
	@CacheEvict(key ="#id", value ="Product")
	public String delete(@RequestBody Product product){
		return repo.deleteProduct(product);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringdataRedisApplication.class, args);
	}

}
