package com.warchlak.persistence.repository;

import com.warchlak.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer>
{
	Product findByName(String name);
}
