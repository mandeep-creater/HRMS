package com.hrms.RequestsDTO;


import com.hrms.enums.Status;

public class EmployeeStatusRequest {
        private String email;
        private Status status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
