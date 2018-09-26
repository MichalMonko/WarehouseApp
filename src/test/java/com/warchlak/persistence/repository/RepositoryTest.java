package com.warchlak.persistence.repository;

import com.warchlak.persistence.entity.Device;
import com.warchlak.persistence.entity.Part;
import com.warchlak.persistence.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
		Product product = new Product("foo");
		product = repository.save(product);
		
		Optional<Product> productWrapper = repository.findById(product.getId());
		Assert.assertTrue(productWrapper.isPresent());
		
		product = productWrapper.get();
		Assert.assertNotNull(product);
		Assert.assertEquals("foo", product.getName());
	}
	
	@Test
	public void device_save_test()
	{
		Product device = new Device("foo");
		device = repository.save(device);
		
		Optional<Product> productWrapper = repository.findById(device.getId());
		Assert.assertTrue(productWrapper.isPresent());
		
		device = productWrapper.get();
		Assert.assertNotNull(device);
		Assert.assertEquals("foo", device.getName());
	}
	
	@Test
	public void part_save_test()
	{
		Product part = new Part("foo");
		part = repository.save(part);
		
		Optional<Product> productWrapper = repository.findById(part.getId());
		Assert.assertTrue(productWrapper.isPresent());
		
		part = productWrapper.get();
		Assert.assertNotNull(part);
		Assert.assertEquals("foo", part.getName());
	}
	
	@Test
	public void device_with_associated_parts_save_test()
	{
		Device device = new Device("machine");
		HashSet<Part> parts = new HashSet<>();
		
		Part part = new Part("gear");
		parts.add(part);
		part = new Part("piston");
		parts.add(part);
		
		device.setParts(parts);
		
		device = repository.save(device);
		
		Assert.assertEquals(parts, device.getParts());
	}
	
	@Test
	public void part_with_associated_devices_save_test()
	{
		Part part = new Part("gear");
		
		Set<Device> devices = new HashSet<>();
		Device device = new Device("bike");
		devices.add(device);
		device = new Device("motorcycle");
		devices.add(device);
		
		part.setMatchingDevices(devices);
		
		part = repository.save(part);
		
		Assert.assertEquals(devices, part.getMatchingDevices());
	}
	
	
}
