package com.lowes.bankingapp.model;


import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String errdetails;


    public ErrorDetails(Date timestamp, String message, String errdetails) {
         super();
         this.timestamp = timestamp;
         this.message = message;
         this.errdetails = errdetails;
    }

    public Date getTimestamp() {
         return timestamp;
    }

    public String getMessage() {
         return message;
    }

    public String getDetails() {
         return errdetails;
    }
}