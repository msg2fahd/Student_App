package com.studentapp.factory;

import com.studentapp.service.StudentService;
import com.studentapp.service.StudentServiceImpl;


public class StudentServiceFactory {
	private static StudentService studentService;
	static {
		studentService = new StudentServiceImpl();
		
	}
	public static StudentService getStudentService() {
		return studentService;
	}
}	

