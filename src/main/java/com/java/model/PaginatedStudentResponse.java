package com.java.model;

import java.util.List;

import com.java.entity.Student;

import lombok.Builder;

@Builder
public class PaginatedStudentResponse {
	private List<Student> studentList;
    private Long numberOfItems;
    private int numberOfPages;
    
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public Long getNumberOfItems() {
		return numberOfItems;
	}
	public void setNumberOfItems(Long numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
    
    
}
