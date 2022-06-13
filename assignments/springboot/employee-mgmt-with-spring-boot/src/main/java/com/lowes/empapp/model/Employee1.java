package com.lowes.empapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
@XmlRootElement

@Entity
@Table
public class Employee1 {
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@NotEmpty
	@Size(max = 20, message = "Should not exceed more than 20 chars")
	@Column
	private String name;
	
	@NotNull
	@PositiveOrZero
	@Column
	private Integer age = null;

	@NotEmpty
	@Column
	private String gender;
	private boolean contractor;
	@Column
    @ElementCollection(targetClass=String.class)
	private List<String> skills;
	@Column
	private String designation;
	@Column
	private String department;
	@Column
	private String address;
	@Column
	private String country;

	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	 
	private LocalDate doj;
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
@Temporal(TemporalType.TIMESTAMP)
//private LocalDateTime createdTime;
@CreationTimestamp 
@Column(name = "createdTime",insertable=true ,updatable=false)
private Date createdTime;
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
@Temporal(TemporalType.TIMESTAMP)
//private LocalDateTime modifiedTime;
@UpdateTimestamp@Column(name = "modifiedTime", insertable=false ,updatable=true)
private Date modifiedTime;
 public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public boolean isContractor() {
		return contractor;
	}

	public void setContractor(boolean contractor) {
		this.contractor = contractor;
	}
	
	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}	

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
   

    public Employee1(String name, int age,String gender,boolean contractor,List<String> skills, String department, String designation, String address,String country, LocalDate doj, Date nowTime, Date nowTime2) {
    	this.name = name;
        this.age = age;
        this.gender=gender;
        this.contractor=contractor;
        this.department = department;
        this.skills=skills;
        this.designation = designation;
        this.address=address;
        this.country = country;
        this.doj = doj;
        this.createdTime = nowTime;
        this.modifiedTime = nowTime2;
    }

	public Employee1() {
		// TODO Auto-generated constructor stub
	}

	

}
