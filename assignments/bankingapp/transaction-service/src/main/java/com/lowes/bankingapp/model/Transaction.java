package com.lowes.bankingapp.model;



import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@XmlRootElement
@Entity
@Table
public class Transaction {

	@Id
	@GeneratedValue
	int id;
	@NotBlank(message="Field accType is required")
	@Size(max = 20, message = "Should not exceed more than 20 chars")
	String accType;
	@Size(max = 20, message = "Should not exceed more than 20 chars")
	String description;
	@CreationTimestamp 
	@Column(name = "createdTime",insertable=true ,updatable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime transactionTime;
	@NotNull(message="Field accountId is required")
	int accountId;
	@NotNull(message="Field amount is required")
	double amount;
	@Column
	int fundtransferID;
	public int getFundtransferID() {
		return fundtransferID;
	}

	public void setFundtransferID(int fundtransferID) {
		this.fundtransferID = fundtransferID;
	}


	
	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}
	
	


	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
public Transaction() {
	
}
	


}
