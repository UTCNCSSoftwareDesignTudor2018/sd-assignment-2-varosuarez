package data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import data.entity.Course;
import data.entity.Enrolment;
import data.entity.Student;

@Transactional
public interface EnrolmentDao extends  JpaRepository<Enrolment, Long> {
	
	List<Enrolment> findAll();
	@SuppressWarnings("unchecked")
	Enrolment saveAndFlush(Enrolment g);
	List<Enrolment> findByStudent(Student s);
	List<Enrolment> findByCourse(Course c);
	Enrolment findByCourseAndStudent(Course c, Student s);
	void delete(Enrolment g);
}
