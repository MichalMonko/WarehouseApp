package com.warchlak.persistence;

import com.warchlak.persistence.repository.RepositoryTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@SpringBootTest
@Suite.SuiteClasses({RepositoryTest.class})
public class PersistenceApplicationTests
{
	
	@Test
	public void contextLoads()
	{
	}
	
}
