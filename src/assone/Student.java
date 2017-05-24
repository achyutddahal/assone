package assone;

import java.util.ArrayList;

/**
 * Class for a student object
 * @author achyut
 */
public class Student {

    private String name;
    private String year;
    ArrayList<String> subjects = new ArrayList<String>();

    /**
     * Empty Constructor
     */
    public Student() {
    }   
    
    /**
     * Constructor with parameters
     * @param name
     * @param year 
     */
    public Student(String name, String year) {
        this.name = name;
        this.year = year;
    }

    /**
     * Get the name of the student
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the student
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the year of the student
     * @return String
     */
    public String getYear() {
        return year;
    }
 
    /**
     * Sets the year of the student
     * @param year 
     */
    public void setYear(String year) {
        this.year = year;
    }

    /** 
     * Get all the subjects that the students is enrolled 
     * @return ArrayList
     */
    public ArrayList<String> getSubjects() {
        return subjects;
    }

    /**
     * Sets the array list for all of the subjects that a student is enrolled in
     * @param subjects 
     */
    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }

}
