package assone;

/**
 * Class to store the assessment details
 *
 * @author achyut
 */
public class Assessment {

    private Float assId;
    private String type;
    private String topic;
    private String format;
    private String due;
    boolean graded;

    /**
     * Empty Constructor
     */
    public Assessment() {
    }

    /**
     * Parametrized constructor
     *
     * @param assId
     * @param type
     * @param topic
     * @param format
     * @param due
     * @param graded
     */
    public Assessment(Float assId, String type, String topic, String format, String due, boolean graded) {
        this.assId = assId;
        this.type = type;
        this.topic = topic;
        this.format = format;
        this.due = due;
        this.graded = graded;
    }

    /**
     * Get Assessment Id
     *
     * @return float
     */
    public Float getAssId() {
        return assId;
    }

    /**
     * Sets the assessment Id
     *
     * @param assId
     */
    public void setAssId(Float assId) {
        this.assId = assId;
    }

    /**
     * Get the type of the assessment
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the assessment
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the topic of the assessment
     *
     * @return String
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the topic of the assessment
     *
     * @param topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Get the format of the assessment
     *
     * @return String
     */
    public String getFormat() {
        return format;
    }

    /**
     * Set the format of the assessment
     *
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Get the due date of the assessment
     *
     * @return due
     */
    public String getDue() {
        return due;
    }

    /**
     * Sets the due date for the assessment
     *
     * @param due
     */
    public void setDue(String due) {
        this.due = due;
    }

    /**
     * Check if the assessment is graded
     *
     * @return boolean
     */
    public boolean isGraded() {
        return graded;
    }

    /**
     * Set the graded status of the assessment
     *
     * @param graded
     */
    public void setGraded(boolean graded) {
        this.graded = graded;
    }

}
