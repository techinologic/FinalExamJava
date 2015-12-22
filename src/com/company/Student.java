package com.company;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Paolo T. inocencion on 12/20/2015.
 * Sources:
 * Introduction to Java Programming, Comprehensive Ed. - Daniel Liang - Chapter 15
 * http://stackoverflow.com/questions/15633228/how-to-remove-all-white-spaces-in-java
 * http://code.makery.ch/blog/javafx-dialogs-official/
 * http://stackoverflow.com/questions/15159988/javafx-2-2-textfield-maxlength
 */

public class Student {
    private String student_name;
    private String entry_date;
    private long coding_hours;
    private char expected_grade;

    /**
     * Default constructor
     */
    public Student() {
        this("John Smith", "December 21, 2015", 20, 'A');
    }

    public Student(String student_name, String entry_date, long coding_hours, char expected_grade) {
        this.student_name = student_name;
        this.entry_date = entry_date;
        this.coding_hours = coding_hours;
        this.expected_grade = expected_grade;
    }

    public void writeToFile(){

        try {
            // Removes spaces in studentname.
            String nospace_student_name=student_name.replaceAll("\\s+","");
            List<String> lines = Arrays.asList(student_name + "\t" + entry_date + "\t" +
                    String.valueOf(coding_hours) + "\t" + String.valueOf(expected_grade));
            Path file = Paths.get(nospace_student_name+"_"+entry_date+".txt");
            System.out.println(file+" created.");
            Files.write(file, lines, Charset.forName("UTF-8"));
            //Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println("File creation error.");
        }


    }


}