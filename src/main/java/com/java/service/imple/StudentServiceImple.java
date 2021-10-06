package com.java.service.imple;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.entity.Student;
import com.java.model.PaginatedStudentResponse;
import com.java.repository.StudentRepo;
import com.java.service.StudentService;

@Service
public class StudentServiceImple implements StudentService{
	@Autowired
	private StudentRepo studentRepo;

	@Override
	public PaginatedStudentResponse readStudents(Pageable pageable) {
		Page<Student> students = studentRepo.findAll(pageable);
        return PaginatedStudentResponse.builder()
                .numberOfItems(students.getTotalElements()).numberOfPages(students.getTotalPages())
                .studentList(students.getContent())
                .build();
	}

	@Override
	public PaginatedStudentResponse filterStudents(String name, Pageable pageable) {
		Page<Student> students = studentRepo.findAllByNameContains(name, pageable);
        return PaginatedStudentResponse.builder()
                .numberOfItems(students.getTotalElements()).numberOfPages(students.getTotalPages())
                .studentList(students.getContent())
                .build();
	}

	@Override
	public List<Student> findAll() {
		List<Student> list = studentRepo.findAll();
		
		return list;
	}

	@Override
	public Optional<Student> findById(int id) {
		Date now = new Date();
		Student student2 = new Student(id, "Bill", "b", now);
		studentRepo.save(student2);
		
		Optional<Student> student = studentRepo.findById(id);
		return student;
	}

//	@Override
//	public Page<Student> getStudentPage(int id, int page, int limit) {
//		//Page<Student> students = studentRepo.findAll(pageable);
//		Pageable pageable = PageRequest.of(page, limit);
//		Page<Student> students = studentRepo.findStudentByStudent_id(id, pageable);
//		
//		return students;
//	}

}
