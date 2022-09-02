package org.develcorp.services.customer.repository;

import org.develcorp.services.customer.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface PersonRepository <E extends Person, ID extends Serializable> extends JpaRepository<E, ID> {
}
