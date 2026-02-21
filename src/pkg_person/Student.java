package pkg_person;

public class Student extends Person{
    private int rolNo;
    private int std;
    private String division;

    public int getRolNo() {
        return rolNo;
    }

    public void setRolNo(int rolNo) {
        this.rolNo = rolNo;
    }

    public int getStd() {
        return std;
    }

    public void setStd(int std) {
        this.std = std;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Student(String name, String emailId, String phoneNumber, String address, String dob, int rolNo, int std, String division) {
        super(name, emailId, phoneNumber, address, dob);
        this.rolNo = rolNo;
        this.std = std;
        this.division = division;
    }

    public Student() {
        super();
    }

    @Override
    public String toString() {
        return "Student{" +
                "rolNo=" + rolNo +
                ", std=" + std +
                ", divison='" + division + '\'' +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
