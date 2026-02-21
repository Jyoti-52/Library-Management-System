package pkg_person;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class StudentManager {
    ObjectOutputStream oos_student=null;
    ObjectInputStream ois_student=null;

    File student_file=null;

    ArrayList<Student> student_list=null;

    public StudentManager(){
        student_file=new File("Student.dat");
        student_list=new ArrayList<Student>();

        if(student_file.exists()){
            try {
                ois_student=new ObjectInputStream(new FileInputStream(student_file));
                student_list= (ArrayList<Student>) ois_student.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void addAStudent(Student student){
        student_list.add(student);
    }

    public Student get(int rollNo){
        for(Student student:student_list){
            if(student.getRolNo()==rollNo){
                return student;
            }
        }
        return null;
    }

    public void viewAllStudents(){
        for(Student student:student_list)
            System.out.println(student);
    }
    public boolean deleteStudent(int delete_rollNo){

        ListIterator<Student> student_iterator= (ListIterator<Student>) student_list.listIterator();
        while (student_iterator.hasNext())
        {
            Student student=student_iterator.next();
            if(student.getRolNo()==delete_rollNo)
            {
                student_list.remove(student);
                return true;
            }
        }
        return false;
    }
    public boolean updateStudent(int update_rollNo,String name, String emailId, String phoneNumber, String address, String dob,int std, String division){
        ListIterator<Student> student_iterator= (ListIterator<Student>) student_list.listIterator();
        while (student_iterator.hasNext())
        {
            Student student=student_iterator.next();
            if(student.getRolNo()==update_rollNo){
                student.setAddress(address);
                student.setDivision(division);
                student.setStd(std);
                student.setDob(dob);
                student.setEmailId(emailId);
                student.setName(name);
                student.setPhoneNumber(phoneNumber);
                return true;
            }
        }
        return false;
    }
    public void  writeToFile(){
        try{
            oos_student=new ObjectOutputStream(new FileOutputStream(student_file));
            oos_student.writeObject(student_list);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
