package data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;

//@Document(collection="student")
@Entity
@Table(name = "student", schema = "public")
public class Student implements data.entity.Entity{
	
	@Id
	@Column(unique = true, nullable = false)
	private Long id;
	@Column(unique = false, nullable = false)
	private String name;
	@Column(unique = false, nullable = true)
	private String surname;
	@Column(nullable = true)
	private String username;
	@Column(nullable = true)
	private String password;
	@Column(unique = true, nullable = false)
	private String idnumber;
	@Column(nullable = true)
	private String address;
	@Column(unique = false, nullable = true)
	private String studentcode;
	@OneToMany(targetEntity = Enrolment.class, mappedBy = "student")
	private List<Enrolment> enrolments = new ArrayList<>();
	public Student(Long id, String name, String surname, String username, String password, String idNumber,
			String address, String studentcode, List<Enrolment> enrolments) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.idnumber = idNumber;
		this.address = address;
		this.studentcode = studentcode;
		this.enrolments = enrolments;
	}
	public Student() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idNumber) {
		this.idnumber = idNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStudentcode() {
		return studentcode;
	}
	public void setStudentcode(String studentcode) {
		this.studentcode = studentcode;
	}
	public List<Enrolment> getEnrolments() {
		return enrolments;
	}
	public void setEnrolments(List<Enrolment> enrolments) {
		this.enrolments = enrolments;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((enrolments == null) ? 0 : enrolments.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idnumber == null) ? 0 : idnumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((studentcode == null) ? 0 : studentcode.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Student other = (Student) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (enrolments == null) {
			if (other.enrolments != null)
				return false;
		} else if (!enrolments.equals(other.enrolments))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idnumber == null) {
			if (other.idnumber != null)
				return false;
		} else if (!idnumber.equals(other.idnumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (studentcode == null) {
			if (other.studentcode != null)
				return false;
		} else if (!studentcode.equals(other.studentcode))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username
				+ ", password=" + password + ", idNumber=" + idnumber + ", address=" + address + ", studentcode="
				+ studentcode + "]";
	}
	public void addCourse(Enrolment c1) {
		this.enrolments.add(c1);
	}
	public void deletetCourse(Enrolment c1) {
		this.enrolments.remove(c1);
	}
	
	

}
