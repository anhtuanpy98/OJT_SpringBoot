package com.java.service;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.java.entity.Student;
import com.java.model.PaginatedStudentResponse;

public interface StudentService {
	//public Page<Student> getStudentPage(int id, int page, int limit);
	public PaginatedStudentResponse readStudents(Pageable pageable);
	public PaginatedStudentResponse filterStudents(String name, Pageable pageable);
	
	public List<Student> findAll();
	public Optional<Student> findById(int id);
}
