package com.example.dbunit.service;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.dbunit.BaseDbUnitTest;
import com.example.dbunit.SpringBootDbUnitApplication;
import com.example.dbunit.configuration.DbUnitConfiguration;
import com.example.dbunit.model.DbUnitDataSource;
import com.example.dbunit.service.DbUnitDataSourceService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootDbUnitApplication.class, DbUnitConfiguration.class })
@ContextConfiguration(classes = DbUnitConfiguration.class)
@DataJpaTest
public class DbUnitDataSourceServiceTest extends BaseDbUnitTest {

	private static final Long ID = 1L;

	@Autowired
	private DbUnitDataSourceService dbUnitDataSourceService;

	@Test
	public void testGetAllOssuiApplications() throws Exception {
		dbUnitDataSourceService.save(createDataSourceEntity());
		dbUnitDataSourceService.save(createDataSourceEntity());

		Assert.assertEquals(2, Lists.newArrayList(dbUnitDataSourceService.findAll()).size());
	}

	@Test
	public void testGetOssuiApplicationById() throws Exception {
		dbUnitDataSourceService.save(createDataSourceEntity());

		DbUnitDataSource dbUnitDataSource = dbUnitDataSourceService.findOne(ID);
		Assert.assertNotNull(dbUnitDataSource);
	}


}