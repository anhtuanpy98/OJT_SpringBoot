package com.java.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.entity.Student;


@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
	public Student findStudentById(int id);
	public Student findStudentByName(String name);
	
	//public Page<Student> findStudentByStudent_id(int id, Pageable pageable);
	public Page<Student> findAll(Pageable pageable);
	public Page<Student> findAllByNameContains(String name, Pageable pageable);
}
