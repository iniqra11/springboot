package com.telusko.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.telusko.demo.dao.StudentDao;
import com.telusko.demo.models.Student;

@Controller
public class HomeController {
	
	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping("home")
	public String getHomePage()
	{
		return "home";
	}
	
	
	@RequestMapping("addStudent")
	public String addStudent(Student student)
	{
		studentDao.save(student);
		return "getStudent";
	}

	
	@RequestMapping("fetchStudent")
	public ModelAndView getStudentById(@RequestParam("id") int id)
	{
		ModelAndView mav = new ModelAndView("display");
		Student student = studentDao.findById(id).orElse(new Student());
		//System.out.println(studentDao.findByFirstName("Iqra"));
		mav.addObject("student",student);
		return mav;
	}
	
	
	// REST Web services
	// All the code below will be placed in a class annotated with @RestController
	@RequestMapping("/students")
	@ResponseBody
	public List<Student> getAllStudents()
	{
		return studentDao.findAll();
	}
	
	@RequestMapping("/student/{id}")  // here id is wild card
	@ResponseBody
	public Optional<Student> getStudent(@PathVariable int id)
	{
		return studentDao.findById(id);
	}
	
	// By default mapping is Get
	// We can avoid writing @ResponseBody everytime, by making the class a @RestController
	@PostMapping("/student")
	public Student addNewStudent(@RequestBody Student student)
	{
		studentDao.save(student);
		return student;
	}
	
	@DeleteMapping("/student/{id}")
	public Student deleteStudent(@PathVariable int id)
	{
		Student s = studentDao.getOne(id);
		studentDao.delete(s);
		return s;
	}
	
	@PutMapping("/student")
	public Student saveOrUpdateStudent(@RequestBody Student student)
	{
		studentDao.save(student);
		return student;
	}
}
