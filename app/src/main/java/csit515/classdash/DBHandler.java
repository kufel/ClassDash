package csit515.classdash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import csit515.classdash.dto.Item;

/**
 * Created by Rixoro on 2/27/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "ClassDash.db";

    public static final String USERS_TABLE = "Users";
    public static final String USERS_C_ID = "ID";
    public static final String USERS_C_FIRST_NAME = "First_Name";
    public static final String USERS_C_LAST_NAME = "Last_Name";
    public static final String USERS_C_EMAIL = "Email";
    public static final String USERS_C_PASSWORD = "Password";
    public static final String USERS_C_CLASEES = "Classes";
    public static final String USERS_C_PERMISSION = "Permissions";

    public static final String CLASSES_TABLE = "Classes";
    public static final String CLASSES_C_ID = "ID";
    public static final String CLASSES_C_NAME = "Name";
    public static final String CLASSES_C_TEACHERID = "Teacher_ID";
    public static final String CLASSES_C_ASSIGNMENTS = "Assignments";
    public static final String CLASSES_C_TUTORMAT = "Tutoring_Material";

    public static final String ASSIGNMENT_TABLE = "Assignments";
    public static final String ASSIGNMENT_C_ID = "Assignment_ID";
    public static final String ASSIGNMENT_C_COURSE_ID = "Course_ID";
    public static final String ASSIGNMENT_C_NAME = "Name";
    public static final String ASSIGNMENT_C_DESCRIPTION = "Description";

    public static final String TUTORING_TABLE = "Tutoring";
    public static final String TUTORING_C_ID = "Assignment_ID";
    public static final String TUTORING_C_COURSE_ID = "Course_ID";
    public static final String TUTORING_C_NAME = "Name";
    public static final String TUTORING_C_DESCRIPTION = "Description";

    public static final String FILE_TABLE = "File";
    public static final String FILE_C_ID = "File_ID";
    public static final String FILE_C_TUTORING_ID = "Tutoring_ID";
    public static final String FILE_C_NAME = "Name";

    public static final String ROSTER_TABLE = "Roster";
    public static final String ROSTER_C_CLASSID = "Class_ID";
    public static final String ROSTER_C_STUID = "Student_ID";
    public static final String ROSTER_C_TEACHID = "Teacher_ID";
    public static final String ROSTER_C_GRADE = "Grade";

    public static final String FORUMS_TABLE = "Forums";
    public static final String FORUMS_C_ID = "ID";
    public static final String FORUMS_C_AUTHOR = "Author";
    public static final String FORUMS_C_TITLE = "Title";
    public static final String FORUMS_C_BODY = "Body";

    public static final String POSTS_TABLE = "Posts";
    public static final String POSTS_C_ID = "ID";
    public static final String POSTS_C_FID = "Forum_ID";
    public static final String POSTS_C_ORDER = "Order_Number";
    public static final String POSTS_C_AUTHOR = "Author";
    public static final String POSTS_C_BODY = "Body";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTables = "create table "+USERS_TABLE+" " +
                "("+USERS_C_ID+" integer primary key, "+
                USERS_C_FIRST_NAME+" text, "+
                USERS_C_LAST_NAME+" text, "+
                USERS_C_EMAIL+" text, "+
                USERS_C_PASSWORD+" text, "+
                USERS_C_CLASEES+" text, "+
                USERS_C_PERMISSION+" integer)";
        db.execSQL(createTables);

        createTables = "create table "+CLASSES_TABLE+" "+
                "("+CLASSES_C_ID+" integer primary key, "+
                CLASSES_C_NAME+" text, "+
                CLASSES_C_TEACHERID+" integer, "+
                CLASSES_C_ASSIGNMENTS+" text, "+
                CLASSES_C_TUTORMAT+" text)";
        db.execSQL(createTables);

        createTables = "create table "+ASSIGNMENT_TABLE+" "+
                "("+ASSIGNMENT_C_ID+" integer primary key, "+
                ASSIGNMENT_C_COURSE_ID+" integer, "+
                ASSIGNMENT_C_NAME+" text, " +
                ASSIGNMENT_C_DESCRIPTION + " text)";
        db.execSQL(createTables);

        createTables = "create table "+TUTORING_TABLE+" "+
                "("+TUTORING_C_ID+" integer primary key, "+
                TUTORING_C_COURSE_ID+" integer, "+
                TUTORING_C_NAME+" text," +
                TUTORING_C_DESCRIPTION+" text)";
        db.execSQL(createTables);

        createTables = "create table "+FILE_TABLE+" "+
                "("+FILE_C_ID+" integer primary key, "+
                FILE_C_TUTORING_ID+" integer," +
                FILE_C_NAME+" text)";
        db.execSQL(createTables);

        createTables = "create table "+ROSTER_TABLE+" ("+
                ROSTER_C_CLASSID+" integer primary key, "+
                ROSTER_C_STUID+" integer, "+
                ROSTER_C_TEACHID+" integer, "+
                ROSTER_C_GRADE+" real)";
        db.execSQL(createTables);

        createTables = "create table "+FORUMS_TABLE+" ("+
                FORUMS_C_ID+" integer primary key, "+
                FORUMS_C_AUTHOR+" integer, "+
                FORUMS_C_TITLE+" text, "+
                FORUMS_C_BODY+" text)";
        db.execSQL(createTables);

        createTables = "create table "+POSTS_TABLE+" ("+
                POSTS_C_ID+" integer primary key, "+
                POSTS_C_FID+" integer, "+
                POSTS_C_ORDER+" integer, "+
                POSTS_C_AUTHOR+" integer, "+
                POSTS_C_BODY+" text)";
        db.execSQL(createTables);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+CLASSES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ASSIGNMENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+TUTORING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+FILE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ROSTER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+FORUMS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+POSTS_TABLE);

        onCreate(db);
    }

    public int numberOfRows(String table) {
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, table);
    }

    public Cursor getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM "+USERS_TABLE+" WHERE "+USERS_C_ID+" = " + id + "", null );
        return res;
    }

    public Cursor getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM "+USERS_TABLE+" WHERE "+USERS_C_EMAIL+" = '" + email + "'", null );
        return res;
    }

    public boolean insertUser(String firstName, String lastName, String email, String pass, String classes, int permsn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERS_C_FIRST_NAME, firstName);
        cv.put(USERS_C_LAST_NAME, lastName);
        cv.put(USERS_C_EMAIL, email);
        cv.put(USERS_C_PASSWORD, pass);
        cv.put(USERS_C_CLASEES, classes);
        cv.put(USERS_C_PERMISSION, permsn);
        db.insert(USERS_TABLE, null, cv);
        return true;
    }

    public boolean updateUser(int id, String firstName, String lastName, String email, String pass, String classes){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERS_C_FIRST_NAME, firstName);
        cv.put(USERS_C_LAST_NAME, lastName);
        cv.put(USERS_C_EMAIL, email);
        cv.put(USERS_C_PASSWORD, pass);
        cv.put(USERS_C_CLASEES, classes);
        db.update(USERS_TABLE, cv, USERS_C_ID+" = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public int deleteUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(USERS_TABLE, "id = ?", new String[] { Integer.toString(id) } );
    }

    public ArrayList<String> getAllUsers(){
        ArrayList<String> list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+USERS_TABLE, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            list.add(res.getString(res.getColumnIndex(USERS_C_ID)));
            res.moveToNext();
        }
        return list;
    }

    public ArrayList<String> getAllUsers(String col) {
        ArrayList<String> list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+USERS_TABLE, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            list.add(res.getString(res.getColumnIndex(col)));
            res.moveToNext();
        }
        return list;
    }

    public Cursor getForum(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM "+FORUMS_TABLE+" WHERE "+FORUMS_C_ID+" = " + id + "", null );
        return res;
    }

    public boolean insertForum(String auth, String title, String body){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FORUMS_C_AUTHOR, auth);
        cv.put(FORUMS_C_TITLE, title);
        cv.put(FORUMS_C_BODY, body);
        db.insert(FORUMS_TABLE, null, cv);
        return true;
    }

    public boolean updateForum(int id, String title, String body){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FORUMS_C_TITLE, title);
        cv.put(FORUMS_C_BODY, body);
        db.update(FORUMS_TABLE, cv, FORUMS_C_ID+" = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public int deleteForum(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(CLASSES_TABLE, FORUMS_C_ID+" = ? ", new String[] { Integer.toString(id) } );
    }

    public HashMap<String, String> getAllForums() {
        HashMap<String, String> list = new HashMap<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + FORUMS_TABLE, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            list.put(res.getString(res.getColumnIndex(FORUMS_C_ID)), res.getString(res.getColumnIndex(FORUMS_C_TITLE)));
            res.moveToNext();
        }
        return list;
    }

    public boolean insertRoster(int cid, int stid, int tid, double grade){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROSTER_C_CLASSID, cid);
        cv.put(ROSTER_C_STUID, stid);
        cv.put(ROSTER_C_TEACHID, tid);
        cv.put(ROSTER_C_GRADE, grade);
        db.insert(ROSTER_TABLE, null, cv);
        return true;
    }

    public boolean updateRoster(int cid, int stid, int tid, double grade){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROSTER_C_TEACHID, tid);
        cv.put(ROSTER_C_GRADE, grade);
        db.update(ROSTER_TABLE, cv, ROSTER_C_CLASSID+" = ? and "+ROSTER_C_STUID+" = ?", new String[] { Integer.toString(cid), Integer.toString(stid) } );
        return true;
    }

    public int deleteRoster(int cid, int stid){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(ROSTER_TABLE, ROSTER_C_CLASSID+" = ? and "+ROSTER_C_STUID+" = ?", new String[] { Integer.toString(cid), Integer.toString(stid) } );
    }

    public ArrayList<String> getAllRoster(){
        ArrayList<String> list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ROSTER_TABLE, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            list.add(res.getString(res.getColumnIndex(ROSTER_C_CLASSID))+"__"+res.getString(res.getColumnIndex(ROSTER_C_STUID)));
            res.moveToNext();
        }
        return list;
    }
    public boolean insertClass(String name, int tid, String assgnmts, String tutormat){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CLASSES_C_NAME, name);
        cv.put(CLASSES_C_TEACHERID, tid);
        cv.put(CLASSES_C_ASSIGNMENTS, assgnmts);
        cv.put(CLASSES_C_TUTORMAT, tutormat);
        db.insert(CLASSES_TABLE, null, cv);
        return true;
    }

    public boolean updateClass(int id, String name, int tid, String assgnmts, String tutormat){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CLASSES_C_NAME, name);
        cv.put(CLASSES_C_TEACHERID, tid);
        cv.put(CLASSES_C_ASSIGNMENTS, assgnmts);
        cv.put(CLASSES_C_TUTORMAT, tutormat);
        db.update(CLASSES_TABLE, cv, CLASSES_C_ID+" = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public int deleteClass(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(CLASSES_TABLE, CLASSES_C_ID+" = ?", new String[] { Integer.toString(id) } );
    }

    public ArrayList<Item> getAllClasses(){
        ArrayList<Item> list = new ArrayList<Item>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + CLASSES_TABLE, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            Item item = new Item();
            item.setId(res.getInt(res.getColumnIndex(CLASSES_C_ID)));
            item.setValue(res.getString(res.getColumnIndex(CLASSES_C_NAME)));
            list.add(item);
            res.moveToNext();
        }
        return list;
    }

    public boolean insertPost(int fid, int order, String auth, String body){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(POSTS_C_FID, fid);
        cv.put(POSTS_C_ORDER, order);
        cv.put(POSTS_C_AUTHOR, auth);
        cv.put(POSTS_C_BODY, body);
        db.insert(POSTS_TABLE, null, cv);
        return true;
    }

    public boolean updatePost(int id, String body){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FORUMS_C_BODY, body);
        db.update(FORUMS_TABLE, cv, POSTS_C_ID+" = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public int deletePost(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(CLASSES_TABLE, POSTS_C_ID+" = ? ", new String[] { Integer.toString(id) } );
    }

    public ArrayList<String> getAllPosts(){
        ArrayList<String> list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+POSTS_TABLE, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            list.add(res.getString(res.getColumnIndex(POSTS_C_ID)));
            res.moveToNext();
        }
        return list;
    }
    public Cursor getAllPostsofForum(String fid){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+POSTS_TABLE+" where "+POSTS_C_FID+"= ? order by "+POSTS_C_ORDER, new String[]{ fid });

        return res;
    }

    public boolean insertTutoring(int courseId, String name, String description){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TUTORING_C_COURSE_ID, courseId);
        cv.put(TUTORING_C_NAME, name);
        cv.put(TUTORING_C_DESCRIPTION, description);
        db.insert(TUTORING_TABLE, null, cv);
        return true;
    }

    public ArrayList<Item> getAllTutoringByCourseId(int courseId){
        ArrayList<Item> list = new ArrayList<Item>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + TUTORING_TABLE + " WHERE " + TUTORING_C_COURSE_ID + " = " + courseId + " ORDER BY " + TUTORING_C_NAME + " ASC" , null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            Item item = new Item();
            item.setId(res.getInt(res.getColumnIndex(TUTORING_C_ID)));
            item.setValue(res.getString(res.getColumnIndex(TUTORING_C_NAME)));
            item.setDescription(" ");
            list.add(item);
            res.moveToNext();
        }
        return list;
    }

    public ArrayList<Item> getAllTutoringByTutoringId(int tutoringId){
        ArrayList<Item> list = new ArrayList<Item>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + TUTORING_TABLE + " WHERE " + TUTORING_C_ID + " = " + tutoringId + " ORDER BY " + TUTORING_C_NAME + " ASC" , null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            Item item = new Item();
            item.setId(res.getInt(res.getColumnIndex(TUTORING_C_ID)));
            item.setValue(res.getString(res.getColumnIndex(TUTORING_C_NAME)));
            item.setDescription(" ");
            list.add(item);
            res.moveToNext();
        }
        return list;
    }

    public boolean insertFile(int tutorialId, String name){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FILE_C_TUTORING_ID, tutorialId);
        cv.put(FILE_C_NAME, name);
        db.insert(FILE_TABLE, null, cv);
        return true;
    }

    public ArrayList<Item> getAllFilesByTutoringId(int tutoringId){
        ArrayList<Item> list = new ArrayList<Item>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + FILE_TABLE + " WHERE " + FILE_C_TUTORING_ID + " = " + tutoringId + " ORDER BY " + FILE_C_ID + " ASC" , null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            Item item = new Item();
            item.setId(res.getInt(res.getColumnIndex(FILE_C_ID)));
            item.setValue(res.getString(res.getColumnIndex(FILE_C_NAME)));
            item.setDescription("");
            list.add(item);
            res.moveToNext();
        }
        return list;
    }

    public boolean insertAssignment(int courseId, String name, String description){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ASSIGNMENT_C_COURSE_ID, courseId);
        cv.put(ASSIGNMENT_C_NAME, name);
        cv.put(ASSIGNMENT_C_DESCRIPTION, description);
        db.insert(ASSIGNMENT_TABLE, null, cv);
        return true;
    }

    public ArrayList<Item> getAllAssignmentByCourseId(int courseId){
        ArrayList<Item> list = new ArrayList<Item>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + ASSIGNMENT_TABLE + " WHERE " + ASSIGNMENT_C_COURSE_ID + " = " + courseId, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            Item item = new Item();
            item.setId(res.getInt(res.getColumnIndex(ASSIGNMENT_C_ID)));
            item.setValue(res.getString(res.getColumnIndex(ASSIGNMENT_C_NAME)));
            item.setDescription(res.getString(res.getColumnIndex(ASSIGNMENT_C_DESCRIPTION)));
            list.add(item);
            res.moveToNext();
        }
        return list;
    }
}
