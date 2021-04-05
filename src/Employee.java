public class Employee {
    String employeeCode;
    String NRIC;
    int phone;
    public Employee(String employeeCode, String NRIC, int phone) {
        this.employeeCode = employeeCode;
        this.NRIC = NRIC;
        this.phone = phone;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
