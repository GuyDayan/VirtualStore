package entitles;

import market.EmployeeType;

public class Employee extends Customer{

    private EmployeeType employeeType;


    public Employee(String firstName, String lastName, String userName, String password,int userType, boolean isClubMember, Integer rank) {
        super(firstName, lastName, userName, password,userType,isClubMember);
        this.employeeType = initRank(rank);
    }

    public EmployeeType initRank(Integer rank){
        switch (rank){
            case 1:
                return EmployeeType.REGULAR;
            case 2:
                return EmployeeType.MANAGER;
            case 3:
                return EmployeeType.STAFF_MEMBER;
        }
        return null;
    }

    public String welcomeMessage(){
        StringBuilder message = new StringBuilder();
        message.append("Hello ").append(this.getFirstName()).append(" ").append(this.getLastName()).
        append(" ").append(this.getEmployeeType()).append(" ! ");
        return message.toString();
    }
    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }
}
