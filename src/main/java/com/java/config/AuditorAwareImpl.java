//package com.java.config;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.AuditorAware;
//
//import com.java.entity.Student;
//import com.java.repository.StudentRepo;
//
//public class AuditorAwareImpl implements AuditorAware<Student>{
//	@Autowired
//    private StudentRepo studentRepo;
//
//	@Override
//	public Optional<Student> getCurrentAuditor() {
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        return Optional.ofNullable(studentRepo.findStudentByName(username));
//	}
//
//}
