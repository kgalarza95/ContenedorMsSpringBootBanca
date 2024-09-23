package com.test.banco.cliente.repository;

import com.test.banco.cliente.model.LogTransaccional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kgalarza
 */
@Repository
public interface LogTransaccionalRepository extends JpaRepository<LogTransaccional, Long> {

}
