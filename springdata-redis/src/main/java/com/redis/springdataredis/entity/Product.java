package com.redis.springdataredis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product")
public class Product implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    @Id
    private int id;
    private String name;
    private int quantity;
    private float price;
}
