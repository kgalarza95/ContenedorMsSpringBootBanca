package com.test.banco.cuenta.repository;

import com.test.banco.cuenta.model.LogTransaccional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kgalarza
 */
@Repository
public interface LogTransaccionalRepository extends JpaRepository<LogTransaccional, Long> {

}
