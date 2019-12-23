package com.studentapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studentapp.beans.Student;
import com.studentapp.factory.StudentServiceFactory;
import com.studentapp.service.StudentService;

@WebServlet("*.do")
public class ControllerServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String request_URI = request.getRequestURI();
		//System.out.println(request_URI);
		RequestDispatcher rd = null;
		
		if(request_URI.endsWith("addform.do")) {
			rd = request.getRequestDispatcher("./addform.html");
			rd.forward(request, response);
		}
		if(request_URI.endsWith("searchform.do")) {
			rd = request.getRequestDispatcher("./searchform.html");
			rd.forward(request, response);
		}
		if(request_URI.endsWith("updateform.do")) {
			rd = request.getRequestDispatcher("./updateform.html");
			rd.forward(request, response);
		}
		if(request_URI.endsWith("deleteform.do")) {
			rd = request.getRequestDispatcher("./deleteform.html");
			rd.forward(request, response);
		}
		if(request_URI.endsWith("add.do")) {
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String saddr = request.getParameter("saddr");
			Student student = new Student();
			student.setSid(sid);
			student.setSname(sname);
			student.setSaddr(saddr);
			StudentService studentService = StudentServiceFactory.getStudentService();
			String status = studentService.addStudent(student);
			if(status.equals("existed")) {
				rd = request.getRequestDispatcher("existed.html");
				rd.forward(request,response);
			}
			if(status.equals("success")) {
				rd = request.getRequestDispatcher("success.html");
				rd.forward(request,response);
			}
			if(status.equals("failure")) {
				rd = request.getRequestDispatcher("failure.html");
				rd.forward(request,response);
			}
			
		}
		if(request_URI.endsWith("search.do")) {
			String sid = request.getParameter("sid");
			StudentService studentService = StudentServiceFactory.getStudentService();
			Student student = studentService.searchStudent(sid);
			if(student==null) {
				rd=request.getRequestDispatcher("notexisted.html");
				rd.forward(request, response);
			}else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<body bgcolor='green'>");
				out.println("<br><br>");
				out.println("<table align='center' border='1'>");
				out.println("<tr><td>Student ID</td><td>"+student.getSid()+"</td></tr>");
				out.println("<tr><td>Student Name</td><td>"+student.getSname()+"</td></tr>");
				out.println("<tr><td>Student Address</td><td>"+student.getSaddr()+"</td></tr>");
				out.println("</table></body></html>");
			}
				
		}
		if(request_URI.endsWith("edit.do")) {
			String sid = request.getParameter("sid");
			StudentService studentService = StudentServiceFactory.getStudentService();
			Student student = studentService.searchStudent(sid);
			if(student==null) {
				rd=request.getRequestDispatcher("notexisted.html");
				rd.forward(request, response);
			}else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<body bgcolor='green'>");
				out.println("<br><br>");
				out.println("<form method='post' action='update.do'>");
				out.println("<table align='center' border='1'>");
				out.println("<tr><td>Student ID</td><td>"+student.getSid()+"</td></tr>");
				out.println("<input type='hidden' name='sid' value='"+student.getSid()+"'/>");
				out.println("<tr><td>Student Name</td><td><input type='text' name='sname' value='"+student.getSname()+"'/></td></tr>");
				out.println("<tr><td>Student Address</td><td><input type='text' name='saddr' value='"+student.getSaddr()+"'/></td></tr>");
				out.println("<tr><td><input type='submit' value='Update'/></td></tr>");
				out.println("</table></form></body></html>");
				
			}
		
		}
		if(request_URI.endsWith("update.do")) {
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String saddr = request.getParameter("saddr");
			Student student = new Student();
			student.setSid(sid);
			student.setSname(sname);
			student.setSaddr(saddr);
			StudentService studentService = StudentServiceFactory.getStudentService();
			String status = studentService.updateStudent(student);
			if(status.equals("success")) {
				rd = request.getRequestDispatcher("success.html");
				rd.forward(request,response);
			}else {
				rd = request.getRequestDispatcher("failure.html");
				rd.forward(request,response);
			}
			
		}
		if(request_URI.endsWith("delete.do")) {
			String sid = request.getParameter("sid");
			StudentService studentService = StudentServiceFactory.getStudentService();
			String status = studentService.deleteStudent(sid);
			if(status.equals("success")) {
				rd = request.getRequestDispatcher("success.html");
				rd.forward(request,response);
			}
			if(status.equals("failure")) {
				rd = request.getRequestDispatcher("failure.html");
				rd.forward(request,response);
			}
			if(status.equals("notexisted")) {
				rd = request.getRequestDispatcher("notexisted.html");
				rd.forward(request,response);
			}

		}
			
			
	}	

}
