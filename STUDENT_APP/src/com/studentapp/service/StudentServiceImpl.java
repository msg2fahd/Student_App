package com.studentapp.service;

import com.studentapp.beans.Student;
import com.studentapp.dao.StudentDao;
import com.studentapp.factory.StdentDaoFactory;

public class StudentServiceImpl implements StudentService {

	@Override
	public String addStudent(Student student) {
		StudentDao studentDao = StdentDaoFactory.getStudentDao();
		String status = studentDao.add(student);
		return status;
	}

	@Override
	public Student searchStudent(String sid) {
		StudentDao studentDao = StdentDaoFactory.getStudentDao();
		Student student = studentDao.search(sid);
		return student;
	}

	@Override
	public String updateStudent(Student student) {
		StudentDao studentDao = StdentDaoFactory.getStudentDao();
		String status = studentDao.update(student);
		return status;
	}

	@Override
	public String deleteStudent(String sid) {
		StudentDao studentDao = StdentDaoFactory.getStudentDao();
		String status = studentDao.delete(sid);
		return status;

	}

}
