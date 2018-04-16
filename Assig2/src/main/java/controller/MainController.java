package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import data.entity.Course;
import data.entity.Enrolment;
import data.entity.Student;
import data.entity.Teacher;



@Controller
public class MainController {
	@Autowired
	private CourseController cController;
	@Autowired
	private StudentController sController;
	@Autowired
	private TeacherController tController;
	@Autowired
	private EnrolmentController eController;
	
	
	private Teacher teacherLogged;
	private Student studentLogged;
	
	private List<Course> courses;
	private List<Enrolment> enrolments;
	
	private List<Enrolment> enrolmentsTeachingCourses;
	
	//private EntityFactory eFactory = new EntityFactory();
	
	@RequestMapping(value = "/")
	public String landing() {
		return "mainView";
	}
	
	@PostMapping(value = "/loginStudent")
	public String login(HttpServletRequest request, Model model) {
		refreshStudent();
		studentLogged = sController.findByIdNumber("student1");
		model.addAttribute("courses", courses);
		model.addAttribute("student", studentLogged);
		model.addAttribute("enrolments", enrolments);
		
		return "studentView";
	}
	
	@PostMapping(value = "/enrol")
	public String addEnrol(HttpServletRequest request, Model model) {
		try{
			
		String cname = request.getParameter("cname");
		Course course = cController.findByCourseName(cname);
		Enrolment e = new Enrolment(-1, studentLogged, course);
		
		for(Enrolment en: enrolments){
			if(en.getCourse().getCoursename().equals(course.getCoursename()))
				return "errorEnroll";
		}
			
		
		int i=0;
		for(Enrolment en: eController.getAllEnrolments()){
			if(en.getId()==i){
				i++;
			}else{
				break;
			}	
		}
		e.setId(i);
		eController.addEnrolment(e);
		
		refreshStudent();
		
		model.addAttribute("courses", courses);
		model.addAttribute("student", studentLogged);
		model.addAttribute("enrolments", enrolments);
		
		}catch(Exception e){
			e.printStackTrace();
			return "errorEnroll";
		}
		return "studentView";
	}
	
	@PostMapping(value = "/deleteEnrol")
	public String deleteEnrol(HttpServletRequest request, Model model) {
		try{
			
		String cname = request.getParameter("cname");
		List<Enrolment> e = eController.findByCourse(cController.findByCourseName(cname));
		for(Enrolment en: e)
		eController.deleteEnrolment(en);
		
		refreshStudent();
		
		enrolments.remove(cController.findByCourseName(cname));
		
		model.addAttribute("courses", courses);
		model.addAttribute("student", studentLogged);
		model.addAttribute("enrolments", enrolments);
		
		}catch(Exception e){
			e.printStackTrace();
			return "errorDeleteEnroll";
		}
		return "studentView";
	}
	
	@PostMapping(value = "/updateStudent")
	public String updateStudent(HttpServletRequest request, Model model) {
		try{
			
		String Idnumber = request.getParameter("ID");
		if(Idnumber.equals("")) Idnumber = studentLogged.getIdnumber();
		for(Student s: sController.getAllStudents())
		{
			if(s.getIdnumber().equals(Idnumber) && Idnumber!=studentLogged.getIdnumber())
			{
				return "errorUpdateStudentReplicatedID";
			}
		}
		studentLogged.setIdnumber(Idnumber);
		
		String name = request.getParameter("name");
		if(name.equals("")) name = studentLogged.getName();
		studentLogged.setName(name);
		
		String surname = request.getParameter("surname");
		if(surname.equals("")) surname = studentLogged.getSurname();
		studentLogged.setSurname(surname);
		
		String address = request.getParameter("address");
		if(address.equals("")) address = studentLogged.getAddress();
		studentLogged.setAddress(address);
		
		String code = request.getParameter("code");
		if(code.equals("")) code = studentLogged.getStudentcode();
		studentLogged.setStudentcode(code);
		
		sController.addStudent(studentLogged);
		
		//refreshStudent();
		
		model.addAttribute("courses", courses);
		model.addAttribute("student", studentLogged);
		model.addAttribute("enrolments", enrolments);
		
		}catch(Exception e){
			e.printStackTrace();
			return "errorUpdateStudent";
		}
		return "studentView";
	}
	
	@PostMapping(value = "/OutStudent")
	public String OutStudent() {
		return "mainView";
	}
	
	
	public void refreshStudent(){
		courses = cController.getAllCourses();
		enrolments = eController.getAllEnrolments();
	}//end refreshStudent
	
	//---------------------------------------------------------------------------
	// teacher view
	
	@PostMapping(value = "/loginTeacher")
	public String loginTeacher(HttpServletRequest request, Model model) {
		
		teacherLogged = tController.findByIdNumber("teacher1");
		
		refreshTeacher();
		
		
		model.addAttribute("teacher", teacherLogged);
		model.addAttribute("enrolments", enrolmentsTeachingCourses);
		
		return "teacherView";
	}
	
	

	@PostMapping(value = "/createStudent")
	public String createStudent(HttpServletRequest request, Model model) {
		try{
			
		Student s = new Student();
		
		String Idnumber = request.getParameter("ID");
		if(Idnumber.equals("")) return "errorCreateStudentEmpty";
		for(Student st: sController.getAllStudents())
		{
			if(st.getIdnumber().equals(Idnumber))
			{
				return "errorUpdateStudentReplicatedID";
			}
		}
		s.setIdnumber(Idnumber);
		
		String name = request.getParameter("name");
		if(name.equals("")) return "errorCreateStudentEmpty";
		s.setName(name);
		
		String surname = request.getParameter("surname");
		if(surname.equals("")) return "errorCreateStudentEmpty";
		s.setSurname(surname);
		
		String address = request.getParameter("address");
		if(address.equals("")) return "errorCreateStudentEmpty";
		s.setAddress(address);
		
		String code = request.getParameter("code");
		if(code.equals("")) return "errorCreateStudentEmpty";
		s.setStudentcode(code);
		
		int i=0;
		for(Student st: sController.getAllStudents()){
			if(st.getId()==i){
				i++;
			}else{
				break;
			}	
		}
		s.setId((long) i);
		s.setUsername(name);
		s.setPassword(name);
		
		sController.addStudent(s);
		
		model.addAttribute("teacher", teacherLogged);
		model.addAttribute("enrolments", enrolmentsTeachingCourses);
		
		}catch(Exception e){
			e.printStackTrace();
			return "errorUpdateStudent";
		}
		return "teacherView";
	}
	
	
	@PostMapping(value = "/deleteStudent")
	public String deleteStudent(HttpServletRequest request, Model model) {
		try
		{
		String id = request.getParameter("IDdelete");
		if(id.equals(""))
		{
			return "errorDeleteStudent";
		}
		
		Student s = sController.findByIdNumber(id);
		
		if(s==null){
			return "errorDeleteStudent";
		}
		
		List<Enrolment> e = eController.findByStudent(s);
		for(Enrolment en: e)
		eController.deleteEnrolment(en);
		
		sController.deleteStudent(s);
		
		refreshTeacher();
		
		model.addAttribute("teacher", teacherLogged);
		model.addAttribute("enrolments", enrolmentsTeachingCourses);
		
		}catch(Exception e){
			e.printStackTrace();
			return "errorDeleteStudent";
		}
		return "teacherView";
	}
	
	
	private void refreshTeacher() {
		
		List<Enrolment> enrolmentsAux = new ArrayList<>();
		for(Student s: sController.getAllStudents()){
			for(Enrolment e : eController.getAllEnrolments()){
				for(Course c: teacherLogged.getTeachingCourses()){
					if(e.getCourse().getCoursename().equals(c.getCoursename()) 
							&& e.getStudent().getIdnumber().equals(s.getIdnumber())){
						enrolmentsAux.add(e);
					}
				}
			}
		}
		
		enrolmentsTeachingCourses = enrolmentsAux;
	}
	
	@PostMapping(value = "/enrolStudent")
	public String enrolStudent(HttpServletRequest request, Model model) {
		try{
		String IDEnrol = request.getParameter("IDEnrol");
		String courseNameEnrol = request.getParameter("courseNameEnrol");
		if(IDEnrol.equals("") || courseNameEnrol.equals("")){
			return "errorEnrolTeacher";
		}
		
		Course course = cController.findByCourseName(courseNameEnrol);
		Student student = sController.findByIdNumber(IDEnrol);
		if(course==null || student==null){
			return "errorEnrolTeacherNull";
		}
		boolean isTeached = false;
		for(Course c: teacherLogged.getTeachingCourses()){
			if(course.getCoursename().equals(c.getCoursename())){
				isTeached = true;
				break;
			}
		}
		if(!isTeached){
			return "errorEnrolTeacherCourse";
		}
		
		Enrolment e = new Enrolment(-1, student, course);
		// student already has that course
		for(Enrolment en: student.getEnrolments()){
			if(en.getCourse().getCoursename().equals(course.getCoursename()))
				return "errorEnrollRepeatedCourse";
		}	
		
		int i=0;
		for(Enrolment en: eController.getAllEnrolments()){
			if(en.getId()==i){
				i++;
			}else{
				break;
			}	
		}
		e.setId(i);
		eController.addEnrolment(e);
		
		refreshTeacher();

		model.addAttribute("teacher", teacherLogged);
		model.addAttribute("enrolments", enrolmentsTeachingCourses);
		
		
		}catch(Exception e){
			e.printStackTrace();
			return "errorEnroll";
		}
		return "teacherView";
	}
	
	@PostMapping(value = "/gradeStudent")
	public String gradeStudent(HttpServletRequest request, Model model) {
		try{
		String IDEnrol = request.getParameter("IDGrade");
		String courseNameEnrol = request.getParameter("courseNameGrade");
		String grade = request.getParameter("Grade");
		if(IDEnrol.equals("") || courseNameEnrol.equals("") || grade.equals("") ){
			return "errorEnrolTeacher";
		}
		
		Course course = cController.findByCourseName(courseNameEnrol);
		Student student = sController.findByIdNumber(IDEnrol);
		if(course==null || student==null){
			return "errorEnrolTeacherNull";
		}
		boolean isTeached = false;
		for(Course c: teacherLogged.getTeachingCourses()){
			if(course.getCoursename().equals(c.getCoursename())){
				isTeached = true;
				break;
			}
		}
		if(!isTeached){
			return "errorEnrolTeacherCourse";
		}
		
		Enrolment e = new Enrolment(Integer.parseInt(grade), student, course);
		int i=0;
		for(Enrolment en: eController.getAllEnrolments()){
			if(en.getId()==i){
				i++;
			}else{
				break;
			}	
		}
		e.setId(i);
		Enrolment e2 = eController.findByCourseAndStudent(course, student);
		for(Enrolment en: student.getEnrolments()){
			if(en.equals(e2)){
				eController.deleteEnrolment(e2);
				break;
			}
		}
		eController.addEnrolment(e);
		
		refreshTeacher();

		model.addAttribute("teacher", teacherLogged);
		model.addAttribute("enrolments", enrolmentsTeachingCourses);
		
		
		}catch(Exception e){
			e.printStackTrace();
			return "errorEnroll";
		}
		return "teacherView";
	}
	
	@PostMapping(value = "/generateReport")
	public String generateReport() {
		
		return "reportView";
	}
	
	@PostMapping(value = "/OutTeacher")
	public String OutTeacher() {
		return "mainView";
	}
	
}
