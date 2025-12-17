package com.hrms.Response;



public class EmpRegistrationResponse {
    private String name;
    private String email;
   // private  String companyCode;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmpRegistrationResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public EmpRegistrationResponse(){}

}
