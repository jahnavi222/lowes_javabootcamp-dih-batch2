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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
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
public class FundTransfer {

	@Id
	@GeneratedValue
	int id;
	
	@NotNull(message = "please provide account id for srcAccountId ")
	@Min(value=0, message="numericField: srcAccountId positive number, min 0 is required")
	@PositiveOrZero
	int srcAccountId;
	@NotNull(message= "numericField:targerAccid positive number value is required")
	@Min(value=0, message="numericField:targerAccid positive number, min 0 is required")
	@PositiveOrZero
	int targerAccid ;
	@NotNull(message="numericField:amount positive number value is required")
	@Min(value=0, message="numericField: amount positive number, min 0 is required")
	@DecimalMin("1.00")
	double amount;
	@NotBlank(message="Field description is required")
	String description ;
	String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSrcAccountId() {
		return srcAccountId;
	}
	public void setSrcAccountId(int srcAccountId) {
		this.srcAccountId = srcAccountId;
	}
	public int getTargerAccid() {
		return targerAccid;
	}
	public void setTargerAccid(int targerAccid) {
		this.targerAccid = targerAccid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
