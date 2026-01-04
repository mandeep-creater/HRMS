package com.hrms.ResponseDTO;

public class CompanyDropdownDTO {
    private int companyId;
    private String companyName;



    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public CompanyDropdownDTO(int companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }
}
