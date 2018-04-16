package data.entity;


import java.sql.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "course", schema = "public")
public class Course implements data.entity.Entity{
	@Id
	private int id;
	@Column(unique = true, nullable = false)
	private String coursename;
	@Column(nullable = false)
	private Date start_date;
	@Column(nullable = false)
	private Date end_date;
	@OneToMany(targetEntity = Enrolment.class, mappedBy = "course")
	private List<Enrolment> enrolments;
	@ElementCollection(targetClass=Date.class)
	private List<Date> exams;
	@OneToOne()
	private Teacher teacher;
	public Course(int id, String courseName, Date startDate, Date endDate, List<Enrolment> students, List<Date> exams,
			Teacher teacher) {
		super();
		this.id = id;
		this.coursename = courseName;
		this.start_date = startDate;
		this.end_date = endDate;
		this.enrolments = students;
		this.exams = exams;
		this.teacher = teacher;
	}
	public Course() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public List<Enrolment> getStudents() {
		return enrolments;
	}
	public void setStudents(List<Enrolment> students) {
		this.enrolments = students;
	}
	public List<Date> getExams() {
		return exams;
	}
	public void setExams(List<Date> exams) {
		this.exams = exams;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coursename == null) ? 0 : coursename.hashCode());
		result = prime * result + ((end_date == null) ? 0 : end_date.hashCode());
		result = prime * result + ((exams == null) ? 0 : exams.hashCode());
		result = prime * result + id;
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
		result = prime * result + ((enrolments == null) ? 0 : enrolments.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
		Course other = (Course) obj;
		if (coursename == null) {
			if (other.coursename != null)
				return false;
		} else if (!coursename.equals(other.coursename))
			return false;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (exams == null) {
			if (other.exams != null)
				return false;
		} else if (!exams.equals(other.exams))
			return false;
		if (id != other.id)
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (enrolments == null) {
			if (other.enrolments != null)
				return false;
		} else if (!enrolments.equals(other.enrolments))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + coursename + ", startDate=" + start_date + ", endDate=" + end_date
				 + ", exams=" + exams + ", teacher=" + teacher + "]";
	}
	
}
