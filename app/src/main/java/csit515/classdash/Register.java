package csit515.classdash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private EditText edtxtfname, edtxtlname, edtxtEmail, edtxtpass, edtxtconpass;
    private DBHandler mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mydb = new DBHandler(this);
        edtxtfname = (EditText) findViewById(R.id.edtxt_fname);
        edtxtlname = (EditText) findViewById(R.id.edtxt_lname);
        edtxtEmail = (EditText) findViewById(R.id.edtxt_email);
        edtxtpass = (EditText) findViewById(R.id.edtxt_pass);
        edtxtconpass = (EditText) findViewById(R.id.edtxt_conpass);
    }

    public void registerUser(View view) {
        String fname = edtxtfname.getText().toString();
        String lname = edtxtlname.getText().toString();
        String email = edtxtEmail.getText().toString();
        String pass = edtxtpass.getText().toString();
        String conpass = edtxtconpass.getText().toString();
        if(fname.length() >0 && lname.length() >0 && email.length() >0 && pass.length() >0 && conpass.length() >0){
            if(email.split("@").length!=2 || !email.split("@")[1].equals("montclair.edu")) {
                Toast.makeText(this, R.string.errmsg2_reg, Toast.LENGTH_LONG).show();
            }
            else if(pass.length()<6){
                Toast.makeText(this, R.string.errmsg3_reg,Toast.LENGTH_LONG).show();
            }
            else if(!pass.matches("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$")){
                Toast.makeText(this, R.string.ermsg5_reg, Toast.LENGTH_LONG).show();
            }
            else if(!pass.equals(conpass)){
                Toast.makeText(this, R.string.errmsg4_reg,Toast.LENGTH_LONG).show();
            }
            else {
                ArrayList<String> users = mydb.getAllUsers(DBHandler.USERS_C_EMAIL);
                boolean cond = true;
                for(String e:users){
                    if(email.equals(e)){
                        cond = false;
                        break;
                    }
                }
                if(cond) {
                    mydb.insertUser(fname, lname, email, pass, "", 1);
                    Toast.makeText(this, R.string.success_reg, Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
                else
                    Toast.makeText(this, R.string.ermsg6_reg, Toast.LENGTH_LONG).show();
            }

        }
        else {
            Toast.makeText(this, R.string.errmsg1_reg,Toast.LENGTH_LONG).show();
        }
    }
}
