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
        forumsSQL();
        assignmentsSQL();
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

    private void tutoringSQL() {
        mydb.insertTutoring(1, "Tutorial 1-1");
        mydb.insertTutoring(1, "Tutorial 1-2");
        mydb.insertTutoring(1, "Tutorial 1-3");

        mydb.insertTutoring(2, "Tutorial 2-1");
        mydb.insertTutoring(2, "Tutorial 2-2");
        mydb.insertTutoring(2, "Tutorial 2-3");

        mydb.insertTutoring(3, "Tutorial 3-1");
        mydb.insertTutoring(3, "Tutorial 3-2");
        mydb.insertTutoring(3, "Tutorial 3-3");
    }

    private void forumsSQL(){
        mydb.insertForum("nick@aol.com", "Help with Data Structures", "I need help understanding the concept of Big O. Like how do you measure the time complexity of an algorithm?");
    }

    private void assignmentsSQL() {
        mydb.insertAssignment(1, "Assignment 1-1");
        mydb.insertAssignment(1, "Assignment 1-2");
        mydb.insertAssignment(1, "Assignment 1-3");

        mydb.insertAssignment(2, "Assignment 2-1");
        mydb.insertAssignment(2, "Assignment 2-2");
        mydb.insertAssignment(2, "Assignment 2-3");

        mydb.insertAssignment(3, "Assignment 3-1");
        mydb.insertAssignment(3, "Assignment 3-2");
        mydb.insertAssignment(3, "Assignment 3-3");
    }

    public static final String ASSIGNMENTS_TABLE = "Assignments";
    public static final String ASSIGNMENTS_C_ID = "Assignment_ID";
    public static final String ASSIGNMENTS_C_STUID = "Student_ID";
    public static final String ASSIGNMENTS_C_CLASSID = "Class_ID";
    public static final String ASSIGNMENTS_C_COMP = "Complete";
    public static final String ASSIGNMENTS_C_GRADE = "GRADE";
}
