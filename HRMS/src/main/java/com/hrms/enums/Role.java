package com.hrms.enums;

public enum Role {
    SUPER_ADMIN,
    ADMIN, // Can create companies, manage all HRs & employees
    HR,           // Can manage employees in their company
    EMPLOYEE      // Normal employee, can see own data
}
