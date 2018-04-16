package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import data.entity.Course;
import data.repository.CourseDao;

@Controller
public class CourseController {
	
	@Autowired
    private CourseDao cDao;	  
	  
	@RequestMapping("/get-all-Courses")
	@ResponseBody
	public List<Course> getAllCourses(){
	  List<Course> courses = cDao.findAll();
	  return courses;
    } 
	@RequestMapping("/find-by-cName")
	@ResponseBody
	public Course findByCourseName(String courseName){
	  Course c = cDao.findByCoursename(courseName);
	  return c;
    } 
	@RequestMapping("/find-by-course_id")
	@ResponseBody
	public Course findByid(int id){
	  Course c = cDao.findByid(id);
	  return c;
    } 
	@RequestMapping("/delete-Course")
	@ResponseBody
	public void deleteCourse(Course c){
		cDao.delete(c);
    } 
	@RequestMapping("/add-course")
	@ResponseBody
	public void addCourse(Course c){
		cDao.saveAndFlush(c);
    } 

}
