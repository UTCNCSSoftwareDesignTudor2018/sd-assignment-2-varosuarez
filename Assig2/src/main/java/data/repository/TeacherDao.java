package data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import data.entity.Teacher;

@Transactional
public interface TeacherDao extends  JpaRepository<Teacher, Long> {
	
	List<Teacher> findAll();
	@SuppressWarnings("unchecked")
	Teacher saveAndFlush(Teacher user);
	void delete(Teacher t);
	Teacher findByIdnumber(String idnumber);
}
