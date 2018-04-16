package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import data.entity.Student;
import data.repository.StudentDao;

@Controller
public class StudentController {
	

	@Autowired
    private StudentDao stDao;	  
	  
	@RequestMapping("/get-all-Students")
	@ResponseBody
	public List<Student> getAllStudents(){
	  List<Student> students = stDao.findAll();
	  return students;
    } 
	@RequestMapping("/find-by-IdNumberStudent")
	@ResponseBody
	public Student findByIdNumber(String idNumber){
	  Student st = stDao.findByIdnumber(idNumber);
	  return st;
    } 
	@RequestMapping("/delete-student")
	@ResponseBody
	public void deleteStudent(Student st){
		stDao.delete(st);
    } 
	@RequestMapping("/add-student")
	@ResponseBody
	public void addStudent(Student st){
		stDao.saveAndFlush(st);
    } 

}
