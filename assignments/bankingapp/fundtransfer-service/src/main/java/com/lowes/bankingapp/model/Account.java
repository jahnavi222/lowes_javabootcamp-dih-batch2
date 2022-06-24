package com.lowes.bankingapp.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@XmlRootElement
@Entity
@Table
public class Account {

	@Id
	@GeneratedValue
	int id;
	@NotBlank(message="Field type is required")
	@Size(max = 20, message = "Should not exceed more than 20 chars")
	String type;
	@NotBlank(message="Field name is required")
	@Size(max = 20, message = "Should not exceed more than 20 chars")
	String name;
	@NotBlank(message="Field status is required")
	@Size(max = 20, message = "Should not exceed more than 20 chars")
	String status;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	@CreatedDate
	@Temporal(TemporalType.DATE)
	Date openDate=new Date();
	@NotNull(message="Field balance is required")
	@PositiveOrZero
	double balance;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = null == openDate ? null : new Date();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
