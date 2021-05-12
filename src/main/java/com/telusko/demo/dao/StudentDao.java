package com.telusko.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.telusko.demo.models.Student;

public interface StudentDao extends JpaRepository<Student, Integer>{

	// Own methods
	
	//List<Student> findByFirstName(String firstName);
	// Method name should start with findBy
	// And then the name of the property, first letter capitalized
	
	//List<Student> findByIdGreaterThan(int id);
	
	//@Query("from Student where firstName=?1 order by id")
	//List<Student> findByFirstNameSortedById(String firstName);
	
	
}
