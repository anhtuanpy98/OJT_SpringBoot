package com.java;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.java.entity.Student;
import com.java.entity.StudentV2;
import com.java.repository.StudentRepo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class StudentRepoTest {

	@Autowired
	private StudentRepo studentRepo;
	
	private Student student;

	@BeforeEach
	public void setUp() {
		Date now = new Date();
		student = new Student(11, "acx", "b", now);
	}

//	@AfterEach
//	public void tearDown() {
//		studentRepo.deleteAll();
//		student = null;
//	}

	@Test
	public void givenStudentToAddShouldReturnAddedStudent() {
		studentRepo.save(student);
		Student fetchedStudent = studentRepo.findStudentById(11);
		assertEquals(11, fetchedStudent.getId());
	}

	@Test
	public void givenIdTODeleteThenShouldDeleteTheStudent() {
		Date now = new Date();
		Student student = new Student(11, "acx", "b", now);
		studentRepo.save(student);
		studentRepo.deleteById(student.getId());
		Optional<Student> optional = studentRepo.findById(student.getId());
		assertEquals(Optional.empty(), optional);
	}

}
