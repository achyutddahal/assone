package assone;

/**
 * Child class of assessment class to store the grade, knowledge and skill degree
 * @author achyut
 */
public class MarkedAssessment extends Assessment {

    private String grade;
    private String knowledgeDegree;
    private String skillDegree;

    /**
     * Get the grade of the assessment 
     * @return String
     */
    public String getGrade() {
        return grade;
    }

    /** 
     * Sets the grade of the assessment
     * @param grade 
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Get the degree of knowledge for the markedAssessmentd
     * @return String
     */
    public String getKnowledgeDegree() {
        return knowledgeDegree;
    }

    /**
     * Sets the knowledge degree of the assessment
     * @param knowledgeDegree 
     */
    public void setKnowledgeDegree(String knowledgeDegree) {
        this.knowledgeDegree = knowledgeDegree;
    }

    /**
     * Get the skill degree for the assessment
     * @return String
     */
    public String getSkillDegree() {
        return skillDegree;
    }

    /**
     * Sets the skill degree for the assessment
     * @param skillDegree 
     */
    public void setSkillDegree(String skillDegree) {
        this.skillDegree = skillDegree;
    }    

}
