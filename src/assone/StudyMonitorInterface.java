package assone;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author achyut Student Grade class contains the main entry for the
 * application It extends a java swing jFrame for the UI
 */
public class StudyMonitorInterface extends JFrame {

    // An object to hold the student
    Student student;

    //Label for subject, assessment and achievement
    JLabel subjectLabel;
    JLabel assessmentLabel;
    JLabel achievementLabel;
    private final JComboBox<String> subjectCombo; // Subject Combo box for loading subjects
    private final JComboBox<String> assessmentCombo; // assessmentCombo for loading the assessments
    private final JComboBox<String> achievementCombo; // Achievements Combo for loading the achievements

    //Lables for student name, year label and their correspoding text fields
    JLabel studentNameLabel;
    JTextField studentName;
    JLabel yearLabel;
    JTextField year;
    
    
    JLabel adminNameLabel;
    JTextField adminName;
    JLabel passwordLabel;
    JPasswordField password;
    JButton loginBtn;

    // Text Area to show the messages to the user
    JTextArea gradeContainer;
    // Declarations for all the buttons that needs to present to user for the operations
    JButton createStudentBtn;
    JButton loadAssessmentBtn;
    JButton displayAssessmentBtn;
    JButton setGradeBtn;
    JButton displayGradeBtn;
    JButton clearDisplayBtn;
    JButton exitBtn;
    // Contains all the subjects loaded from the csv to the subject class as an arraylist
    ArrayList<String> subjectsList;

    // All subjects and assessments gets loaded into the arraylists
    ArrayList<Subject> allSubject = new ArrayList<Subject>();
    //Contains all the assessments details from the csv files
    ArrayList<Assessment> allAssessments;

    // Some constants for the grades, knowledge and skills level
    public static final String[] GRADES = new String[]{"Very high", "High", "Sound", "Developing", "Emerging"};
    public static final String VERY_HIGH_KNOWLEDGE_DEG = "thorough understanding";
    public static final String VERY_HIGH_SKILL_DEG = "uses a high level of skill in both familiar and new situations";
    public static final String HIGH_KNOWLEDGE_DEG = "clear understanding";
    public static final String HIGH_SKILL_DEG = "uses a high level of skill in familiar situations, and is beginning to use skills in new situations";
    public static final String SOUND_KNOWLEDGE_DEG = "understanding";
    public static final String SOUND_SKILL_DEG = "uses skills in situations familiar to them";
    public static final String DEVELOPING_KNOWLEDGE_DEG = "understands aspects of";
    public static final String DEVELOPING_SKILL_DEG = "uses varying levels of skill in situations familiar to them";
    public static final String EMERGING_KNOWLEDGE_DEG = "basic understanding";
    public static final String EMERGING_SKILL_DEG = "beginning to use skills in familiar situations";

    /**
     * Student grade constructor for the common UI generation and the event
     * listener declarations
     */
    public StudyMonitorInterface() {
        // Sets the title of the JFrame
        super(UIConstants.FRAME_TITLE);
        student = new Student();

        // Construction of top panel
        JPanel studyProgressPanel = new JPanel();
        studyProgressPanel.setLayout(new FlowLayout());
        //@todo define constant
        studyProgressPanel.setBorder(new TitledBorder(new EtchedBorder(), UIConstants.FRAME_TITLE));
        //Student details panel
        JPanel studentDetailsPanel = new JPanel();
        studentDetailsPanel.setLayout(new BorderLayout());
        // GUI components for the study Progress Panel
        //@todo define constant
        studentNameLabel = new JLabel(UIConstants.STUDENT_NAME);
        studentName = new JTextField(40);
        yearLabel = new JLabel(UIConstants.YEAR);
        year = new JTextField(UIConstants.YEAR_SIZE);
        
        //Labels for admin login with admin username, password and the login button
        adminNameLabel = new JLabel(UIConstants.ADMING_NAME);
        adminName = new JTextField(25);
        passwordLabel = new JLabel(UIConstants.PASSWORD);
        password = new JPasswordField(25);
        loginBtn = new JButton(UIConstants.LOGIN);

        // Event listener for the student name for the student creation validation
        studentName.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                if (!text.isEmpty()) {
                    String actualYear = year.getText();
                    if (!actualYear.isEmpty()) {
                        createStudentBtn.setEnabled(true);
                        gradeContainer.setText(UIConstants.ABLE_TO_CREATE_STUDENT);
                    }
                } else {
                    gradeContainer.setText(" ");
                    createStudentBtn.setEnabled(false);
                }
            }
        });
        // Event listener for the  year for validating while creating the user
        year.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                if (!text.isEmpty()) {
                    String stdName = studentName.getText();
                    if (!stdName.isEmpty()) {
                        createStudentBtn.setEnabled(true);
                        gradeContainer.setText("You can now create student using the Create Student button");
                    }
                } else {
                    gradeContainer.setText(" ");
                    createStudentBtn.setEnabled(false);
                }
            }
        });
        
        // Panel for admin login on the system
        JPanel adminLoginPanel = new JPanel(new FlowLayout());
        adminLoginPanel.add(adminNameLabel);
        adminLoginPanel.add(adminName);
        adminLoginPanel.add(passwordLabel);
        adminLoginPanel.add(password);
        adminLoginPanel.add(loginBtn);

        // Panel for student name and year
        JPanel studentYearPanel = new JPanel(new FlowLayout());
        studentYearPanel.add(studentNameLabel);
        studentYearPanel.add(studentName);
        studentYearPanel.add(yearLabel);
        studentYearPanel.add(year);

        // Subject label and combo box
        subjectLabel = new JLabel(UIConstants.SUBJECT_LABEL);
        subjectCombo = new JComboBox<String>();

        // Assessment label and combox box
        assessmentLabel = new JLabel(UIConstants.ASSESSMENT_LABEL);
        assessmentCombo = new JComboBox<String>();

        //Achievements label and combo box
        achievementLabel = new JLabel(UIConstants.ACHIEVEMENT_LABEL);
        achievementCombo = new JComboBox<String>(GRADES);

        studentDetailsPanel.add(adminLoginPanel, BorderLayout.NORTH);
        studentDetailsPanel.add(studentYearPanel, BorderLayout.CENTER);
        // Panel to keep the subject assessment and achievements
        JPanel subjectAssPanel = new JPanel(new FlowLayout());
        //Adds all the label and combobox to the panels
        subjectAssPanel.add(subjectLabel);
        subjectAssPanel.add(subjectCombo);
        subjectAssPanel.add(assessmentLabel);
        subjectAssPanel.add(assessmentCombo);
        subjectAssPanel.add(achievementLabel);
        subjectAssPanel.add(achievementCombo);
        studentDetailsPanel.add(subjectAssPanel, BorderLayout.SOUTH);

        studyProgressPanel.add(studentDetailsPanel);

        // Middle panel to show the relevent text in the text area
        JPanel studentPerformancePanel = new JPanel();
        studentPerformancePanel.setPreferredSize(new Dimension(UIConstants.PERFORMANCE_PANEL_WIDTH, UIConstants.PERFORMANCE_PANEL_HEIGHT));
        studentPerformancePanel.setLayout(new FlowLayout());
        studentPerformancePanel.setBorder(new TitledBorder(new EtchedBorder(), UIConstants.STUDENT_PERFORMANCE));

        // A text area to show relevant messages to the user
        gradeContainer = new JTextArea(UIConstants.GRADE_CONTAINER_HEIGHT, UIConstants.GRADE_CONTAINER_WIDTH);
        // Add the textarea to the panel
        studentPerformancePanel.add(gradeContainer);
        studyProgressPanel.add(studentPerformancePanel);

        // Defines the new panel for the command button
        JPanel commandButtonPanel = new JPanel();
        commandButtonPanel.setPreferredSize(new Dimension(UIConstants.COMMAND_BUTTON_PANEL_WIDTH, UIConstants.COMMAND_BUTTON_PANEL_HEIGHT));

        // Buttom panel to show the seven buttons for the operations
        commandButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        commandButtonPanel.setBorder(new TitledBorder(new EtchedBorder(), UIConstants.COMMAND_BUTTON));

        // Define all the buttons for the command button panel
        createStudentBtn = new JButton(UIConstants.CREATE_STUDENT);
        loadAssessmentBtn = new JButton(UIConstants.LOAD_ASSESSMENT);
        displayAssessmentBtn = new JButton(UIConstants.DISPLAY_ASSESSMENT);
        setGradeBtn = new JButton(UIConstants.SET_GRADE);
        displayGradeBtn = new JButton(UIConstants.DISPLAY_GRADE);
        clearDisplayBtn = new JButton(UIConstants.CLEAR_DISPLAY);
        exitBtn = new JButton(UIConstants.EXIT);

        // Initially the create student button is not enabled
        createStudentBtn.setEnabled(false);
        
        /**
         * Action listener for the login button
         * It enables the user to enter the Administrator login details
         */
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                
                JOptionPane.showMessageDialog(null, "Admin Name = "+adminName.getText()+" and password = "+password.getText() , "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        /**
         * Action listener for the create student button It creates a new
         * student object in the backend
         */
        createStudentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                student.setName(studentName.getText());
                student.setYear(year.getText());
                createStudentBtn.setEnabled(false);
                loadAssessmentBtn.setEnabled(true);
                gradeContainer.setText("Student added to the system. You can now load Assessments using the Load Assessment Button");
            }
        });

        // Initially the load assessment button is disabled
        loadAssessmentBtn.setEnabled(false);
        /**
         * Action listener for the Load assessment button It loads the subjects
         * and assessment details from the csv file
         */
        loadAssessmentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (allSubject.size() == 0) {
                    subjectCombo.removeAllItems();
                    readCSV();
                    for (Subject subject : allSubject) {
                        subjectCombo.addItem(subject.getName());
                    }
                    gradeContainer.setText("");
                    JOptionPane.showMessageDialog(null, UIConstants.ASSESSMENT_LOADED, "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        /**
         * Action listener for the clear display button It clears the display in
         * the text area
         */
        clearDisplayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gradeContainer.setText("");
            }
        });

        /**
         * Action listener for the exit button which helps in exiting the
         * program
         */
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /**
         * Action listener for the display assessment button it displays the
         * assessments details for the selected subject
         */
        displayAssessmentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // If the student object is not created display no student is created
                if (student.getName() == null || student.getName().isEmpty()) {
                    JOptionPane.showMessageDialog(null, UIConstants.NO_STUDENT_CREATED, UIConstants.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
                } else if (!isAssessmentLoaded()) {
                    JOptionPane.showMessageDialog(null, UIConstants.SUBJECT_NOT_LOADED, UIConstants.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
                } else {
                    // Get the assessment details from the selected subject index
                    String selectedSubject = String.valueOf(subjectCombo.getSelectedItem());
                    String assDetails = selectedSubject + ":\n";
                    for (Subject subject : allSubject) {
                        if (subject.getName().equals(selectedSubject)) {

                            for (Assessment ass : subject.getAssessments()) {
                                String graded = ass.isGraded() ? UIConstants.GRADED : UIConstants.NOT_GRADED;
                                assDetails = assDetails + ass.getAssId() + " , " + ass.getType() + " , " + ass.getTopic() + " , " + ass.getFormat() + " , " + ass.getDue() + "  ," + graded + "\n";
                            }
                        }
                        gradeContainer.setText(assDetails);
                    }

                }
            }
        });

        /**
         * Action listener for the set grade button It helps in adding grade to
         * a subject and assessment selected
         */
        setGradeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Checks if the student object is not created
                if (student.getName() == null || student.getName().isEmpty()) {
                    JOptionPane.showMessageDialog(null, UIConstants.NO_STUDENT_CREATED, UIConstants.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
                } else if (!isAssessmentLoaded()) {
                    JOptionPane.showMessageDialog(null, UIConstants.SUBJECT_NOT_LOADED, UIConstants.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
                } else {
                    // Get Subject
                    String selectedSubject = String.valueOf(subjectCombo.getSelectedItem());
                    // Get index of assessment
                    int assessmentIndex = assessmentCombo.getSelectedIndex();
                    //get Text of the achievements
                    String selectedGrade = String.valueOf(achievementCombo.getSelectedItem());
                    for (Subject subject : allSubject) {
                        if (subject.getName().equals(selectedSubject)) {
                            subject.getAssessments().get(assessmentIndex).setGraded(true);
                            subject.getAssessments().get(assessmentIndex).setGrade(selectedGrade);
                            subject.getAssessments().get(assessmentIndex).setKnowledgeDegree(getKnowledgeDegree(selectedGrade));
                            subject.getAssessments().get(assessmentIndex).setSkillDegree(getSkillDegree(selectedGrade));
                            gradeContainer.setText(UIConstants.GRADE_UPDATED);
                        }
                    }
                    JOptionPane.showMessageDialog(null, UIConstants.GRADE_UPDATED, UIConstants.INFO_LABEL, JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        /**
         * Action listener for the display grade button It helps in displaying
         * the grades for the selected subject and assessment for that subject
         * in the text area
         */
        displayGradeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check if the student object has not been created
                if (student.getName() == null || student.getName().isEmpty()) {
                    JOptionPane.showMessageDialog(null, UIConstants.NO_STUDENT_CREATED, UIConstants.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
                } else if (!isAssessmentLoaded()) {
                    JOptionPane.showMessageDialog(null, UIConstants.SUBJECT_NOT_LOADED, UIConstants.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
                } else {

                    // Get Subject
                    String selectedSubject = String.valueOf(subjectCombo.getSelectedItem());
                    // Get index of assessment
                    int assessmentIndex = assessmentCombo.getSelectedIndex();
                    //get Text of the achievements
                    String selectedGrade = String.valueOf(achievementCombo.getSelectedItem());
                    String assDetails = selectedSubject + ": ";
                    for (Subject subject : allSubject) {
                        if (subject.getName().equals(selectedSubject)) {
                            MarkedAssessment tempAss = subject.getAssessments().get(assessmentIndex);
                            if (tempAss.isGraded()) {
                                assDetails = assDetails + tempAss.getAssId() + " , " + tempAss.getType() + " , " + tempAss.getTopic() + " , " + tempAss.getFormat() + " , " + tempAss.getDue() + "\n";
                                assDetails = assDetails + UIConstants.ACHIEVEMENTS + tempAss.getGrade() + "\n";
                                assDetails = assDetails + UIConstants.KNOWLEDGE + tempAss.getKnowledgeDegree() + "\n";
                                assDetails = assDetails + UIConstants.SKILL + tempAss.getSkillDegree() + "\n";
                                gradeContainer.setText(assDetails);
                            } else {
                                gradeContainer.setText(UIConstants.NO_GRADE_SET);
                            }
                        }
                    }
                }
            }
        });
        /**
         * Action listeners for the subject combo box to load its corresponding
         * assessment and their details
         */
        subjectCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This function updated the assessment combo box
                changeAssessmentCombo();
            }
        });

        // Add all the buttons to the command button panel at the bottom of the frame
        commandButtonPanel.add(createStudentBtn);
        commandButtonPanel.add(loadAssessmentBtn);
        commandButtonPanel.add(displayAssessmentBtn);
        commandButtonPanel.add(setGradeBtn);
        commandButtonPanel.add(displayGradeBtn);
        commandButtonPanel.add(clearDisplayBtn);
        commandButtonPanel.add(exitBtn);
        studyProgressPanel.add(commandButtonPanel);
        // Finally add the parent panel to the Jframe
        add(studyProgressPanel);
        // Constructor ends here
    }

    /**
     * Get the knowledge Degree text for the passed grade string
     *
     * @param grade
     * @return String
     */
    public String getKnowledgeDegree(String grade) {
        switch (grade) {
            case "Very high":
                return VERY_HIGH_KNOWLEDGE_DEG;
            case "High":
                return HIGH_KNOWLEDGE_DEG;
            case "Sound":
                return SOUND_KNOWLEDGE_DEG;
            case "Developing":
                return DEVELOPING_KNOWLEDGE_DEG;
            case "Emerging":
                return EMERGING_KNOWLEDGE_DEG;
        }
        return UIConstants.NOT_AVAILABLE;
    }

    /**
     * Get the skill degree text for the passed grade string
     *
     * @param grade
     * @return String
     */
    public String getSkillDegree(String grade) {

        switch (grade) {
            case "Very high":
                return VERY_HIGH_SKILL_DEG;
            case "High":
                return HIGH_SKILL_DEG;
            case "Sound":
                return SOUND_SKILL_DEG;
            case "Developing":
                return DEVELOPING_SKILL_DEG;
            case "Emerging":
                return EMERGING_SKILL_DEG;
        }
        return UIConstants.NOT_AVAILABLE;

    }

    /**
     * Change the assessment combo box items corresponding to the selected
     * subject in the subject combo box
     */
    public void changeAssessmentCombo() {
        // Get the selected subject
        String selectedSubject = String.valueOf(subjectCombo.getSelectedItem());
        assessmentCombo.removeAllItems();
        for (Subject subject : allSubject) {
            if (subject.getName().equals(selectedSubject)) {
                // Get all assessment for the chosen subject and add item to the assessment combo box
                for (Assessment ass : subject.getAssessments()) {
                    assessmentCombo.addItem(subject.getName() + " , " + ass.getAssId() + " , " + ass.getType());
                }
            }
        }

    }

    /**
     * Reads, parse the csv file and create a arraylist of the subject
     */
    public void readCSV() {
        //Csv file location
        String csvFile = "/var/www/html/COIT20256Ass1Data.csv";
        String line = "";
        String cvsSplitBy = ",";
        //All subject lists get stored on this varaible
        subjectsList = new ArrayList<String>();
        //Execption handing for the file operations
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                // use comma as separator
                String[] subjectDetails = line.split(cvsSplitBy);
                //Creatses marked assessment object from the csv file
                MarkedAssessment newAssFromCsv = new MarkedAssessment();
                newAssFromCsv.setAssId(Float.valueOf(subjectDetails[1]));
                newAssFromCsv.setDue(subjectDetails[5]);
                newAssFromCsv.setFormat(subjectDetails[4]);
                newAssFromCsv.setGraded(false);
                newAssFromCsv.setTopic(subjectDetails[3]);
                newAssFromCsv.setType(subjectDetails[2]);
                //Creat the subject object from each line of the csv file
                Subject newSubFromCsv = new Subject();
                newSubFromCsv.setName(subjectDetails[0]);
                newSubFromCsv.addAssessment(newAssFromCsv);
                // Check for the subject subject object in the arraylist
                if (containsObj(allSubject, subjectDetails[0])) {
                    for (Subject subject : allSubject) {
                        if (subject.getName().equals(subjectDetails[0])) {
                            subject.addAssessment(newAssFromCsv);
                        }
                    }
                } else {
                    allSubject.add(newSubFromCsv);
                }

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, UIConstants.NO_DATA_FILE, UIConstants.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean isAssessmentLoaded() {
        if (allSubject.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if the passed Subject array list contains object that contains the
     * passes subject name
     *
     * @param list
     * @param name
     * @return boolean
     */
    public static boolean containsObj(ArrayList<Subject> list, String name) {
        for (Subject object : list) {
            if (object.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Main function for JVM to load the application
     *
     * @param args
     */
    public static void main(String[] args) {
        //Creation of the stduentGrade frame with size to make it visible for the user interaction
        StudyMonitorInterface stdGrade = new StudyMonitorInterface();
        stdGrade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stdGrade.setSize(UIConstants.JFRAME_WIDTH, UIConstants.JFRAME_HEIGHT);
        // Centers the jframe irrespective to the screen resolution
        stdGrade.setLocationRelativeTo(null);
        stdGrade.setVisible(true);

    }

}
