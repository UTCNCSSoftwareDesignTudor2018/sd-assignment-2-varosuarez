package data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import data.entity.Student;

@Transactional
public interface StudentDao extends  JpaRepository<Student, Long> {
	
	List<Student> findAll();
	@SuppressWarnings("unchecked")
	Student saveAndFlush(Student user);
	void delete(Student st);
	Student findByIdnumber(String idnumber);
}
