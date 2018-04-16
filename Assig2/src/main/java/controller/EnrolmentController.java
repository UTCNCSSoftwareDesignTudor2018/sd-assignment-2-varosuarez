package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import data.entity.Course;
import data.entity.Enrolment;
import data.entity.Student;
import data.repository.EnrolmentDao;

@Controller
public class EnrolmentController {
	

	@Autowired
    private EnrolmentDao gDao;	  
	  
	@RequestMapping("/get-all-grades")
	@ResponseBody
	public List<Enrolment> getAllEnrolments(){
	  List<Enrolment> teachers = gDao.findAll();
	  return teachers;
    } 
	@RequestMapping("/find-grade-by-student")
	@ResponseBody
	public List<Enrolment> findByStudent(Student s){
	  List<Enrolment> g = gDao.findByStudent(s);
	  return g;
    } 
	@RequestMapping("/find-grade-by-course")
	@ResponseBody
	public List<Enrolment> findByCourse(Course c){
		List<Enrolment> g = gDao.findByCourse(c);
	  return g;
    } 
	@RequestMapping("/find-enrolment-by-course-student")
	@ResponseBody
	public Enrolment findByCourseAndStudent(Course c, Student s){
		Enrolment g = gDao.findByCourseAndStudent(c, s);
	  return g;
    } 
	@RequestMapping("/delete-enrolment")
	@ResponseBody
	public void deleteEnrolment(Enrolment d){
		gDao.delete(d);
    } 
	@RequestMapping("/add-Enrolment")
	@ResponseBody
	public void addEnrolment(Enrolment g){
		gDao.saveAndFlush(g);
    } 

}
