package csit515.classdash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private EditText emailtxt, passtxt;
    private Button loginbtn, regbtn;
    private DBHandler mybd;
    SharedPreferences shprefs;
    SharedPreferences.Editor editor;
    public static String SHPR = "CLASSDASHSHPREFS";
    public static String CURRUSER = "CURRUSER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Stetho.initializeWithDefaults(this);

        emailtxt = (EditText) findViewById(R.id.li_email_edittxt);
        passtxt = (EditText) findViewById(R.id.li_pass_edtxt);
        loginbtn = (Button) findViewById(R.id.li_login_btn);
        regbtn = (Button) findViewById(R.id.li_reg_btn);
        mybd = new DBHandler(this);
        shprefs = getSharedPreferences(SHPR, MODE_APPEND);
        editor = shprefs.edit();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean match = false;

                String username = "";
                ArrayList<String> lUsers = mybd.getAllUsers();
                for(String s:lUsers){
                    Cursor rs = mybd.getUser(Integer.parseInt(s));
                    rs.moveToFirst();
                    String testEmail = rs.getString(rs.getColumnIndex(DBHandler.USERS_C_EMAIL));
                    String testPass = rs.getString(rs.getColumnIndex(DBHandler.USERS_C_PASSWORD));

                    if(testEmail.equals(emailtxt.getText().toString()) && testPass.equals(passtxt.getText().toString())){
                        match = true;
                        username = testEmail;
                        break;
                    }
                }

                if(match){
                    editor.putString(CURRUSER, username);
                    editor.commit();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Snackbar.make(v, "Invalid Email and/or Password", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
