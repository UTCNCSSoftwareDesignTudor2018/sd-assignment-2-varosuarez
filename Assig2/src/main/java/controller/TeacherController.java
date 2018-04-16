package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import data.entity.Teacher;
import data.repository.TeacherDao;

@Controller
public class TeacherController {
	

	@Autowired
    private TeacherDao tDao;	  
	  
	@RequestMapping("/get-all-Teachers")
	@ResponseBody
	public List<Teacher> getAllTeachers(){
	  List<Teacher> teachers = tDao.findAll();
	  return teachers;
    } 
	@RequestMapping("/find-by-IdNumber")
	@ResponseBody
	public Teacher findByIdNumber(String idNumber){
	  Teacher t = tDao.findByIdnumber(idNumber);
	  return t;
    } 
	@RequestMapping("/delete-teacher")
	@ResponseBody
	public void deleteTeacher(Teacher t){
		tDao.delete(t);
    } 
	@RequestMapping("/add-teacher")
	@ResponseBody
	public void addTeacher(Teacher t){
		tDao.saveAndFlush(t);
    } 

}
