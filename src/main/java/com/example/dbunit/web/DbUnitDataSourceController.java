package com.example.dbunit.web;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.dbunit.model.DbUnitDataSource;
import com.example.dbunit.service.DbUnitDataSourceService;

@RestController
@RequestMapping("/api/v1")
public class DbUnitDataSourceController {

	@Autowired
	private DbUnitDataSourceService dbUnitDataSourceService;

	@RequestMapping(value = "/dbUnitDataSources/", method = RequestMethod.GET)
	public List<Object> getAllDbUnitDataSources() {
		Iterable<DbUnitDataSource> dbUnitDataSources = dbUnitDataSourceService.findAll();

		return StreamSupport.stream(dbUnitDataSources.spliterator(), false).collect(Collectors.toList());
	}

	@RequestMapping(value = "/dbUnitDataSource/{id}", method = RequestMethod.GET)
	public DbUnitDataSource getDbUnitDataSource(@PathVariable("id") long id) {
		return dbUnitDataSourceService.findOne(id);
	}

	@RequestMapping(value = "/dbUnitDataSource/", method = RequestMethod.POST)
	public void createDbUnitDataSource(@RequestBody DbUnitDataSource bUnitDataSource) {
		dbUnitDataSourceService.save(bUnitDataSource);
	}

	@RequestMapping(value = "/dbUnitDataSource/{id}", method = RequestMethod.DELETE)
	public void deleteDbUnitDataSource(@PathVariable("id") long id) {
		dbUnitDataSourceService.delete(id);
	}
}
