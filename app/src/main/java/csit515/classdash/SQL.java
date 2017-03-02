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
    }

    private void userSQL() {
        mydb.insertUser("Nick", "Garcia", "nick@aol.com", "pass", "", 1);
        mydb.insertUser("Josh", "Garcia", "josh@gmail.com", "pass", "", 1);
    }

    private void classesSQL() {
        mydb.insertClass("Java 101", 0, "Assigments1", "Tutorial1");
        mydb.insertClass("Java 102", 0, "Assigments2", "Tutorial2");
        mydb.insertClass("Java 103", 0, "Assigments3", "Tutorial3");
        mydb.insertClass("Java 104", 0, "Assigments4", "Tutorial4");
        mydb.insertClass("Java 105", 0, "Assigments5", "Tutorial5");
    }

    private void assignmentsSQL() {
        //mydb.insertAssignment();
    }
}
