package com.example.dbunit.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.dbunit.model.DbUnitDataSource;

@Repository
public interface DbUnitDataSourceService extends JpaRepository<DbUnitDataSource, Long> {

}
