package csit515.classdash;

/**
 * Created by Kufel on 3/1/2017.
 */

public class SQL {

    private DBHandler mydb;

    public SQL(DBHandler db) {
        mydb = db;
        userSQL();
        classesSQL();
        tutoringSQL();
        assignmentsSQL();
        forumsSQL();
        files();
    }

    private void userSQL() {
        //mydb.insertUser("Test", "Test", "test@test.com", "pass", "", 1);
        mydb.insertUser("Nick", "Garcia", "nick@aol.com", "pass", "", 1);
        mydb.insertUser("Josh", "Garcia", "josh@gmail.com", "pass", "", 1);
    }

    private void classesSQL() {
        mydb.insertClass("Software Engineering", 0, " ", " ");
        mydb.insertClass("Statistics", 0, " ", " ");
        mydb.insertClass("Physics", 0, " ", " ");
    }

    private void forumsSQL(){
        mydb.insertForum("nick@aol.com", "Help with Data Structures", "I need help understanding the concept of Big O. Like how do you measure the time complexity of an algorithm?");
    }

    private void assignmentsSQL() {
        mydb.insertAssignment(1, "Assignment 1", "Write a short feasibility report that describes the project that you have selected. The exact form of the report is up to you, but it should be well written and suitable to present to an external client. The length is likely to be between five and ten pages.");
        mydb.insertAssignment(1, "Assignment 2", "During the semester each team will give three presentations with associated reports on the work completed. You will make a 45 minute presentation to the client, the Instructor and the Teaching Assistant assigned to your project.  Everybody is expected to be a presenter at least once.");
        mydb.insertAssignment(1, "Assignment 3", "The final presentation and documentation will follow the same format as the others, except that you have an extra week to complete your documentation.   You will make a 45 minute presentation to the client, the Instructor and the Teaching Assistant assigned to your project.  Remember that everybody is expected to be a presenter at least once during the semester.");

        mydb.insertAssignment(2, "Assignment 1", "1. What are categorical and numerical variables? What is statistical distribution? \n" +
                                                "2. Know the corresponding graphical method to display the distributions for each type variable.\n" +
                                                "3. Be able to draw the stem-leaf plot manually, know how to describe the distribution from the plot.");
        mydb.insertAssignment(2, "Assignment 2", "1. Know how to draw a scatter plot, be able to obtain information from a scatter plot. \n" +
                                                "2. Know how to determine the explanatory variable and response variable from a study. \n" +
                                                "3. Know the meaning of correlation coeﬃcient r and all the facts about it. \n" +
                                                "4. Be able to match correlation value to corresponding scatter plot.");
        mydb.insertAssignment(2, "Assignment 3", "1. Know how to compute joint distribution \n" +
                                                "2. Know how to computer marginal distribution \n" +
                                                "3. Know how to compute conditional distribution and use it to summarize the relation-ship between two categorical variables.");

        mydb.insertAssignment(3, "Assignment 1", "A person starts running with a constant velocity trying to catch a streetcar that is initially\n" +
                                                "2.0 × 10^1\n" +
                                                "m away from a person and has just started to accelerate from rest with a constant\n" +
                                                "acceleration of 0.9 ms^2. The person runs just fast enough to catch the streetcar and hop on. ");
        mydb.insertAssignment(3, "Assignment 2", "In a recent publication of Nature, Australian scientists determined that during the last ice age\n" +
                                                "(22,000 to 19,000 years ago) the sea level reached its low point, 425 to 440 feet below the\n" +
                                                "present level due to the change of sea water to glacial ice.\n" +
                                                "a) What approximate volume of ice would this correspond to?\n" +
                                                "b) If this additional ice were evenly divided between the polar ice caps, estimate how far the\n" +
                                                "northern hemisphere ice sheet would extend southward. ");
        //mydb.insertAssignment(3, "Assignment 3", "A long description.");
    }

    private void tutoringSQL() {
        mydb.insertTutoring(1, "Introduction to Software", "");
        mydb.insertTutoring(1, "Team Work", "");
        mydb.insertTutoring(1, "Design", "");

        mydb.insertTutoring(2, "Intruction to Statistics", "");
        mydb.insertTutoring(2, "History of Statistics", "");

        mydb.insertTutoring(3, "2D Dismessions", "");
        mydb.insertTutoring(3, "Light", "");
    }

    private  void files()  {
        mydb.insertFile(1, "Statistics.docx");
        mydb.insertFile(1, "doc1.doc");
        mydb.insertFile(1, "history.pdf");

        mydb.insertFile(2, "Statistics.docx");
        mydb.insertFile(2, "doc1.doc");

        mydb.insertFile(3, "design.pdf");
        mydb.insertFile(3, "help-with-design.docx");

        mydb.insertFile(4, "stats.doc");
        mydb.insertFile(4, "lecture-1.pdf");
        mydb.insertFile(4, "syllabus.doc");
        mydb.insertFile(4, "instructions-software-install.pdf");

        mydb.insertFile(5, "intoToPhysics.ppt");

        mydb.insertFile(6, "lecture1.pdf");
        mydb.insertFile(6, "data.csv");
        mydb.insertFile(6, "data-light.csv");

        mydb.insertFile(7, "lecture2.pdf");
    }

}
