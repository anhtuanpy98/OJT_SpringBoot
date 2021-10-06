package com.java.api.crudStudent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.NameAlreadyBoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.config.YAMLConfig;
import com.java.entity.Student;
import com.java.entity.StudentV2;
import com.java.exception.NameStudentIsExisted;
import com.java.exception.StudyNotFoundException;
import com.java.repository.StudentRepo;
import com.java.service.StudentService;


@RestController
//@RequestMapping("/OJT_SpringBoot")
public class StudentApi {
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private StudentService studentService;

	@GetMapping("/getStudent")
	public List<Student> getStudent() {
		List<Student> list = studentRepo.findAll();

		return list;
	}

	@GetMapping("/getStudent/{id}")
	public Student getStudent2(@PathVariable("id") int id) {
		System.out.println(id);
		Student st = studentRepo.findStudentById(id);

		return st;
	}

	// ---------------------------URI------------------
	@GetMapping("/getStudent2/{id}")
	public StudentV2 getStudent22(@PathVariable("id") int id) {
		System.out.println(id);

		Date now = new Date();

		return new StudentV2(id, "a", "b", "M", now);
	}

	// ---------------------------request param------------------

	@GetMapping(value = "/getStudent2/{id}/param", params = "version=1")
	public Student paramV(@PathVariable("id") int id) {

		Student st = studentRepo.findStudentById(id);

		return st;
	}

	@GetMapping(value = "/getStudent2/{id}/param", params = "version=2")
	public StudentV2 paramV2(@PathVariable("id") int id) {
		System.out.println();

		Date now = new Date();

		return new StudentV2(102, "a", "b", "M", now);
	}

	// --------------------------- HEADER------------------

	@GetMapping(value = "/student/{id}/header", headers = "X-API-VERSION=1")
	public Student headerV1(@PathVariable("id") int id) {
		Student st = studentRepo.findStudentById(id);
		return st;
	}

	@GetMapping(value = "/student/header", headers = "X-API-VERSION=2")
	public StudentV2 headerV2() {
		Date now = new Date();

		return new StudentV2(102, "a", "b", "M", now);
	}

	// ---------------------------ACCEPT HEADER------------------
	@GetMapping(value = "/student/produces/{id}", produces = "application/vnd.company.app-v1+json")
	public Student acceptheaderV1(@PathVariable("id") int id) {
		Student st = studentRepo.findStudentById(id);
		return st;
	}

	@GetMapping(value = "/student/produces", produces = "application/vnd.company.app-v2+json")
	public StudentV2 acceptheaderV2() {
		Date now = new Date();

		return new StudentV2(102, "a", "b", "M", now);
	}

	// --------------PAGEABLE-------------
	// Phân trang khi tìm kiếm bài viết theo đề mục
	@GetMapping("/student/search")
    public ResponseEntity readBooks (Pageable pageable) {
        return ResponseEntity.ok(studentService.readStudents(pageable));
    }
	
	@GetMapping("/student/search/filter")
    public ResponseEntity readBooksWithFilter (@RequestParam("byName") String query, Pageable pageable) {
        return ResponseEntity.ok(studentService.filterStudents(query, pageable));
    }

//	@PostMapping("addStudent")
//	public String addStudent(@RequestBody Student student) {
//		if(studentRepo.findById(student.getId())!=null) {
//			
//		}
//		studentRepo.save(student);
//
//		return "Thanh cong";
//	}

	@Autowired
	private YAMLConfig myConfig;

	@PostMapping("addStudent/{id}")
	public ResponseEntity<Object> addStudent(@Valid @PathVariable("id") int id, @RequestBody Student student)
			throws ParseException {

		if (student.getAddress() == null) {
			student.setAddress(myConfig.getAddress());
			System.out.println("Error: " + myConfig.getAddress() + "     ??");
		}

		if (studentRepo.findStudentByName(student.getName()) != null) {
			throw new NameStudentIsExisted();
		}

//		SimpleDateFormat sm = new SimpleDateFormat("yyyy-mm-dd");
//	    // myDate is the Java.util.Date in yyyy-mm-dd format
//	    // Converting it into String using formatter
//	    String strDate = sm.format(student.getBirthday());
//	    //Converting the String back to Java.util.Date
//	    Date dt = sm.parse(strDate);
//		
//	    student.setBirthday(dt);

		studentRepo.save(student);

		return new ResponseEntity<>("Student is add successfully", HttpStatus.OK);
	}

//	@PutMapping("updateStudent")
//	public String updateStudent(@RequestBody Student student) {
//		
//		studentRepo.save(student);
//		
//		return "Update Thanh cong";
//	}

	@RequestMapping(value = "/updateStudent/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Student student) {
		if (studentRepo.findStudentById(id) == null)
			throw new StudyNotFoundException();
		studentRepo.save(student);
		return new ResponseEntity<>("Study is updated successfully", HttpStatus.OK);
	}

	@DeleteMapping("deleteStudent/{id}")
	public String updateStudent(@PathVariable("id") int id) {

		studentRepo.deleteById(id);

		return "delete Thanh cong";
	}
}
