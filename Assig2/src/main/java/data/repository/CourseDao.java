package data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import data.entity.Course;

@Transactional
public interface CourseDao extends  JpaRepository<Course, Long> {
	
	List<Course> findAll();
	@SuppressWarnings("unchecked")
	Course saveAndFlush(Course user);
	Course findByCoursename(String coursename);
	Course findByid(int id);
	void delete(Course c);
}
