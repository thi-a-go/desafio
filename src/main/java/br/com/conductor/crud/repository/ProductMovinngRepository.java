package br.com.conductor.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.conductor.crud.model.Product;

public interface ProductMovinngRepository extends JpaRepository<Product, Integer>{

	public List<Product> findByLogic(Integer logic);

	@Query("SELECT p FROM Product p WHERE p.logic = :logic AND p.serial = :serialCode")
	public List<Product> loadProductsByLogic(@Param("logic") Integer logic, @Param("serialCode") String serialCode);
	
}
