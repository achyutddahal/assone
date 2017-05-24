package assone;

import java.util.ArrayList;

/**
 *Class for a subject
 * @author achyut
 */
public class Subject {

    // Subject name
    private String name;
    // All marked assessment ( child of assessments) for the subject
    ArrayList<MarkedAssessment> assessments = new ArrayList<MarkedAssessment>();

    /**
     * Empty Constructor
     */
    public Subject() {
    }

    /**
     * Constructor with parameter
     * @param name 
     */
    public Subject(String name) {
        this.name = name;
    }

    /**
     * Get the name of the subject
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the subject
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**  
     * Get all marked assessments for the subject
     * @return ArrayList
     */
    public ArrayList<MarkedAssessment> getAssessments() {
        return assessments;
    }

    /**
     * Sets the marked assessments for the subjects
     * @param assessments 
     */
    public void setAssessments(ArrayList<MarkedAssessment> assessments) {
        this.assessments = assessments;
    }
    
    /**
     * Add the assessment to the assessments array list
     * @param ass 
     */
    public void addAssessment(MarkedAssessment ass){
        this.assessments.add(ass);
    }   

}