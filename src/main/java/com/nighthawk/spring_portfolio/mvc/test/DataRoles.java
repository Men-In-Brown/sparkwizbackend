package com.nighthawk.spring_portfolio.mvc.test;

public class DataRoles {
    private String roleName;

    public DataRoles(String roleName) {
        this.roleName = roleName;
    }

    // Getter and setter for roleName
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // Override toString method for better representation
    @Override
    public String toString() {
        return "DataTestRole{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
