package com.studentapp.factory;

import com.studentapp.dao.StudentDao;
import com.studentapp.dao.StudentDaoImpl;

public class StdentDaoFactory {
	private static StudentDao studentDao;
	static {
		studentDao = new StudentDaoImpl();
	}
	public static StudentDao getStudentDao() {
		return studentDao;
	}
}
