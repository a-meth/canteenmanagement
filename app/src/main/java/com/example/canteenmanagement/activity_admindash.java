package com.example.canteenmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class activity_admindash extends AppCompatActivity {
    EditText user;
    EditText pass;
    RadioGroup rg;
    RadioButton rb;
    Button bSubmit;
    Button bViewStudents;
    Button bViewCanteen,lg;

    myDbAdapter myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindash);

        myDb = new myDbAdapter(this);


        user = findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);
        rg = findViewById(R.id.apRadioGroup);
        int radioid = rg.getCheckedRadioButtonId();
        rb = findViewById(radioid);
        bSubmit = findViewById(R.id.submit);
        bViewStudents = findViewById(R.id.view);
        bViewCanteen = findViewById(R.id.button);
        lg=findViewById(R.id.button7);
        AddUser();
        ViewStudents();
        ViewCanteen();
    }

    public void AddUser(){
        bSubmit.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                    int i=  rg.getCheckedRadioButtonId();
                    boolean category;
                    rb=findViewById(i);
                    Toast.makeText(activity_admindash.this,rb.getText().toString(),Toast.LENGTH_SHORT).show();
                    if(rb.getText().toString().compareTo("STUDENT")==0)
                        category = true;
                    else
                        category = false;

                     boolean isInserted = myDb.insertData(user.getText().toString(), pass.getText().toString(), category);
                        //boolean isInserted = myDb.insertData(user.getText().toString()/*,pass.getText().toString()*/,true);

                        if(isInserted == true){
                         Toast.makeText(activity_admindash.this,"User added",Toast.LENGTH_SHORT).show();
                     }else
                         Toast.makeText(activity_admindash.this,"User Not Added",Toast.LENGTH_SHORT).show();

                    }
                }
        );
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent atom = new Intent(activity_admindash.this,MainActivity.class);
                startActivity(atom);
            }
        });
    }
    public void ViewStudents(){
        bViewStudents.setOnClickListener(
                new View.OnClickListener()  {
                        @Override
                        public void onClick(View v){
                        Cursor res = myDb.getStudentData();
                        if(res.getCount() == 0){
                        showmessage("Error","Not found");
                        return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            if(res.getString(2).compareTo("STUDENT")==0)
                                buffer.append("Username : " + res.getString(0) +   "\n" + "Password : " + res.getString(1)+"\n");
                        }
                        showmessage("Data",buffer.toString());
                    }
                }
        );
    }
    public void ViewCanteen(){
        bViewCanteen.setOnClickListener(
                new View.OnClickListener()  {
                    @Override
                    public void onClick(View v){
                        Cursor res = myDb.getStudentData();
                        if(res.getCount() == 0){
                            showmessage("Error","Not found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            if(res.getString(2).compareTo("CANTEEN")==0)
                                buffer.append("Username : " + res.getString(0) +   "\n" + "Password : " + res.getString(1)+"\n");
                        }
                        showmessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showmessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}



