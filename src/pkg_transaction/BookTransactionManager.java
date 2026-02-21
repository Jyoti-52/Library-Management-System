package pkg_transaction;

import pkg_person.Student;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

public class BookTransactionManager {
    ObjectOutputStream oos_book_transaction=null;
    ObjectInputStream ois_book_transaction=null;

    File book_transaction_file=null;

    ArrayList<BookTransaction> book_transaction_list=null;

    private Stack<BookTransaction> returnStack = new Stack<>();


    private Queue<BookTransaction> issueQueue = new LinkedList<>();



    public BookTransactionManager(){
        book_transaction_file=new File("book_transaction.dat");
        book_transaction_list=new ArrayList<BookTransaction>();


        if(book_transaction_file.exists()){
            try {
                ois_book_transaction=new ObjectInputStream(new FileInputStream(book_transaction_file));
                book_transaction_list= (ArrayList<BookTransaction>) ois_book_transaction.readObject();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean issueBook(int rollNo,int isbn){
        int total_books_issued=0;

        for(BookTransaction book_transaction:book_transaction_list){
            if((book_transaction.getRollNo()==rollNo)
                    &&(book_transaction.getReturnDate()==null))
                total_books_issued+=1;

            if(total_books_issued>=3)
                return false;
        }

        String issue_date =
                new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        BookTransaction book_transaction =
                new BookTransaction(isbn,rollNo,issue_date,null);

        issueQueue.add(book_transaction);

        // existing logic
        book_transaction_list.add(book_transaction);
        return true;
    }


    public boolean returnBook(int rollNo,int isbn){
        for(BookTransaction book_transaction:book_transaction_list){
            if((book_transaction.getRollNo()==rollNo)
                    &&(book_transaction.getIsbn()==isbn)
                    &&(book_transaction.getReturnDate()==null)){

                String return_date =
                        new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                book_transaction.setReturnDate(return_date);

                returnStack.push(book_transaction);

                return true;
            }
        }
        return false;
    }

    public void showLastReturnedBook(){
        if(!returnStack.isEmpty()){
            System.out.println("Last Returned Book:");
            System.out.println(returnStack.peek());
        }else{
            System.out.println("No books returned yet.");
        }
    }

    public void writeToFile(){
        try {
            oos_book_transaction=new ObjectOutputStream(new FileOutputStream(book_transaction_file));
            oos_book_transaction.writeObject(book_transaction_list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showAll(){
        for(BookTransaction book_transaction:book_transaction_list){
            System.out.println(book_transaction);
        }
    }
}
