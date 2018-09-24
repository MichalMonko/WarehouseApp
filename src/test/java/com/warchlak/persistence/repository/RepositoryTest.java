package com.warchlak.persistence.repository;

import com.warchlak.persistence.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest
{
	@Autowired
	private ProductRepository repository;
	
	@Test
	public void product_save_test()
	{
		Product product = new Product();
		product.setName("foo");
		repository.save(product);
		
		Optional<Product> productWrapper = repository.findById(1);
		Assert.assertTrue(productWrapper.isPresent());
		
		product = productWrapper.get();
		Assert.assertNotNull(product);
		Assert.assertEquals("foo", product.getName());
	}
	
}
