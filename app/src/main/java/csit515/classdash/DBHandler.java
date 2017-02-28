package csit515.classdash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Rixoro on 2/27/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "ClassDash.db";
    public static final String USERPRO_TABLE = "User_Profile";
    public static final String USERPRO_C_ID = "ID";
    public static final String USERPRO_C_NAME = "Name";
    public static final String USERPRO_C_EMAIL = "Email";
    public static final String USERPRO_C_PASSWORD = "Password";
    public static final String USERPRO_C_CLASEES = "Classes";
    public static final String USERPRO_C_PERMISSION = "Permissions";

    public static final String CLASSES_TABLE = "Classes";
    public static final String CLASSES_C_ID = "ID";
    public static final String CLASSES_C_NAME = "NAME";
    public static final String CLASSES_C_TEACHERID = "Teacher_ID";
    public static final String CLASSES_C_ASSIGNMENTS = "Assignments";
    public static final String CLASSES_C_TUTORMAT = "Tutoring_Material";

    public static final String ASSIGNMENTS_TABLE = "Assignments";
    public static final String ASSIGNMENTS_C_ID = "Assignment_ID";
    public static final String ASSIGNMENTS_C_STUID = "Student_ID";
    public static final String ASSIGNMENTS_C_CLASSID = "Class_ID";
    public static final String ASSIGNMENTS_C_COMP = "Complete";
    public static final String ASSIGNMENTS_C_GRADE = "GRADE";

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
        String createTables = "create table "+USERPRO_TABLE+" " +
                "("+USERPRO_C_ID+" integer primary key, "+
                USERPRO_C_NAME+" text, "+
                USERPRO_C_EMAIL+" text, "+
                USERPRO_C_PASSWORD+" text, "+
                USERPRO_C_CLASEES+" text, "+
                USERPRO_C_PERMISSION+" integer)";
        db.execSQL(createTables);

        createTables = "create table "+CLASSES_TABLE+" "+
                "("+CLASSES_C_ID+" integer primary key, "+
                CLASSES_C_NAME+" text, "+
                CLASSES_C_TEACHERID+" integer, "+
                CLASSES_C_ASSIGNMENTS+" text, "+
                CLASSES_C_TUTORMAT+" text)";
        db.execSQL(createTables);

        createTables = "create table "+ASSIGNMENTS_TABLE+" ("+
                ASSIGNMENTS_C_ID+" integer primary key, "+
                ASSIGNMENTS_C_STUID+" integer primary key, "+
                ASSIGNMENTS_C_CLASSID+" integer, "+
                ASSIGNMENTS_C_COMP+" integer, "+
                ASSIGNMENTS_C_GRADE+ " real)";
        db.execSQL(createTables);

        createTables = "create table "+ROSTER_TABLE+" ("+
                ROSTER_C_CLASSID+" integer primary key, "+
                ROSTER_C_STUID+" integer primary key, "+
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
        db.execSQL("DROP TABLE IF EXISTS "+USERPRO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+CLASSES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ASSIGNMENTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ROSTER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+FORUMS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+POSTS_TABLE);

        onCreate(db);
    }

    public int numberOfRows(String table) {
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, table);
    }

    public boolean insertUser(String name, String email, String pass, String classes, int permsn){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERPRO_C_NAME, name);
        cv.put(USERPRO_C_EMAIL, email);
        cv.put(USERPRO_C_PASSWORD, pass);
        cv.put(USERPRO_C_CLASEES, classes);
        cv.put(USERPRO_C_PERMISSION, permsn);
        db.insert(USERPRO_TABLE, null, cv);
        return true;
    }

    public boolean updateUser(int id, String name, String email, String pass, String classes){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERPRO_C_NAME, name);
        cv.put(USERPRO_C_EMAIL, email);
        cv.put(USERPRO_C_PASSWORD, pass);
        cv.put(USERPRO_C_CLASEES, classes);
        db.update(USERPRO_TABLE, cv, USERPRO_C_ID+" = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public int deleteUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(USERPRO_TABLE, "id = ?", new String[] { Integer.toString(id) } );
    }

    public ArrayList<String> getAllUsers(){
        ArrayList<String> list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+USERPRO_TABLE, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            list.add(res.getString(res.getColumnIndex(USERPRO_C_ID)));
            res.moveToNext();
        }
        return list;
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

    public ArrayList<String> getAllForums(){
        ArrayList<String> list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+FORUMS_TABLE, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            list.add(res.getString(res.getColumnIndex(FORUMS_C_ID)));
            res.moveToNext();
        }
        return list;
    }

    public boolean insertAssignment(int stid, int cid, int comp, int grade){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ASSIGNMENTS_C_STUID, stid);
        cv.put(ASSIGNMENTS_C_CLASSID, cid);
        cv.put(ASSIGNMENTS_C_COMP, comp);
        cv.put(ASSIGNMENTS_C_GRADE, grade);
        db.insert(CLASSES_TABLE, null, cv);
        return true;
    }

    public boolean updateClass(int id, int stid, int cid, int comp, double grade){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ASSIGNMENTS_C_STUID, stid);
        cv.put(ASSIGNMENTS_C_CLASSID, cid);
        cv.put(ASSIGNMENTS_C_COMP, comp);
        cv.put(ASSIGNMENTS_C_GRADE, grade);
        db.update(CLASSES_TABLE, cv, ASSIGNMENTS_C_CLASSID+" = ? and "+ASSIGNMENTS_C_STUID+" = ?", new String[] { Integer.toString(cid), Integer.toString(stid) } );
        return true;
    }

    public int deleteAssignment(int cid, int stid){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(CLASSES_TABLE, ASSIGNMENTS_C_CLASSID+" = ? and "+ASSIGNMENTS_C_STUID+" = ?", new String[] { Integer.toString(cid), Integer.toString(stid) } );
    }

    public ArrayList<String> getAllAssigments(){
        ArrayList<String> list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ASSIGNMENTS_TABLE, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            list.add(res.getString(res.getColumnIndex(ASSIGNMENTS_C_ID)));
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

    public ArrayList<String> getAllClasses(){
        ArrayList<String> list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CLASSES_TABLE, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            list.add(res.getString(res.getColumnIndex(CLASSES_C_ID)));
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

}
