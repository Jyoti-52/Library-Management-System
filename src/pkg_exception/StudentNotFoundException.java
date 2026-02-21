package pkg_exception;

public class StudentNotFoundException extends Exception{
    public StudentNotFoundException() {
        super();
    }

    @Override
    public String toString() {
        return "StudentNotFoundException is Generated";
    }
}
