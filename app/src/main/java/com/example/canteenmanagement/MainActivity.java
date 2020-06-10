package com.example.canteenmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    myDbAdapter myDb;

    private EditText user;
    private EditText pass;
    private Button blogin;
    private Button btest;
    private RadioGroup rg;
    private RadioButton rb;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        TextView textView1 = findViewById(R.id.textView1);
        textView1.setTextColor(Color.WHITE);
        myDb = new myDbAdapter(this);
        user = findViewById(R.id.usLogin);
        pass = findViewById(R.id.usPass);
        blogin = findViewById(R.id.bLogin);
        rg = findViewById(R.id.radioGroup);
        btest = findViewById(R.id.button2);
        t = findViewById(R.id.textView2);


        btest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = rg.getCheckedRadioButtonId();
                rb = findViewById(radioId);
                t.setText("Your choice: " + rb.getText());

            }
        });


        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(user.getText().toString(), pass.getText().toString());
            }
        });


    }

    public void checkButton(View v) {
        int radioId = rg.getCheckedRadioButtonId();
        rb = findViewById(radioId);
        Toast.makeText(this, rb.getText(), Toast.LENGTH_SHORT).show();
    }

    public void validate(String username, String password) {
        Cursor res = myDb.getStudentData();

        int radioId = rg.getCheckedRadioButtonId();
        rb = findViewById(radioId);

        if (rb.getText().toString().compareTo("ADMIN") == 0) {
            if (username.compareTo("Admin@gmail.com") == 0 && password.compareTo("Admin123") == 0) {
                Intent i = new Intent(MainActivity.this, activity_admindash.class);
                startActivity(i);
            } else
                Toast.makeText(this, username + " " + password, Toast.LENGTH_SHORT).show();
        } else if (rb.getText().toString().compareTo("STUDENT") == 0) {
            if (username.compareTo("Student@gmail.com") == 0 && password.compareTo("Student123") == 0) {
                Intent j = new Intent(MainActivity.this, student_dash_main.class);
                startActivity(j);
            } else if(res.getCount()!=0){
                while (res.moveToNext()){
                    if(res.getString(2).compareTo("STUDENT")==0){
                        if(res.getString(0).compareTo(username)==0 &&
                        res.getString(1).compareTo(password)==0){
                            Intent x = new Intent(MainActivity.this, student_dash_main.class);
                            startActivity(x);                        }
                    }
                   // Toast.makeText(this, "Username and Password WRONG", Toast.LENGTH_SHORT).show();

                }
            }
            else
                Toast.makeText(this, "Username and Password WRONG", Toast.LENGTH_SHORT).show();
        } else {
            if (username.compareTo("Canteen@gmail.com") == 0 && password.compareTo("Canteen123") == 0) {
                Intent k = new Intent(MainActivity.this, canteen_dash_main.class);
                startActivity(k);
            }else if(res.getCount()!=0){
                while (res.moveToNext()){
                    if(res.getString(2).compareTo("CANTEEN")==0){
                        if(res.getString(0).compareTo(username)==0 &&
                                res.getString(1).compareTo(password)==0){
                            Intent j = new Intent(MainActivity.this, canteen_dash_main.class);
                            startActivity(j);
                            //res.move(-10);
                        }
                        /*if(res.isLast()){
                            Toast.makeText(this,"User Not Found", Toast.LENGTH_SHORT).show();
                        }*/
                    }

                }
            }
            else
                Toast.makeText(this,"User Not Found", Toast.LENGTH_SHORT).show();
        }
    }
}