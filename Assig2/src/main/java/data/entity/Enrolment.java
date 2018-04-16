package data.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "enrolment", schema = "public")
public class Enrolment implements data.entity.Entity{
	
	@Id
	private int id;
	@Column(nullable = true)
	private int grade;
	@OneToOne()
	private Student student;
	@OneToOne()
	public Course course;
	
	public Enrolment(int id, int grade, Student student, Course course) {
		super();
		this.id = id;
		this.grade = grade;
		this.student = student;
		this.course = course;
	}
	
	public Enrolment(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}


	public Enrolment() {
		super();
	}


	public Enrolment(int grade, Student student, Course course) {
		super();
		this.grade = grade;
		this.student = student;
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + grade;
		result = prime * result + id;
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrolment other = (Enrolment) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (grade != other.grade)
			return false;
		if (id != other.id)
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", grade=" + grade + ", student=" + student.getName() + ", course=" + course.getCoursename() + "]";
	}
	
}
