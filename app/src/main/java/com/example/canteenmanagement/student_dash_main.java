package com.example.canteenmanagement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class student_dash_main extends AppCompatActivity {
    String qr = "";

    EditText[] e = new EditText[8];
    Integer[] s = new Integer[8];
    Integer[] n = new Integer[8];
    Integer total=0;
    Integer[] p ={7,12,7,40,30,10,40,20};
    TextView[] t = new TextView[8];
    public TextView tot;
    Button b,btotal;
    static Bitmap bitmap = Bitmap.createBitmap(200,200,Bitmap.Config.RGB_565);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash_main);

        //Toast.makeText(this,total,Toast.LENGTH_SHORT).show();

        e[0] =  findViewById(R.id.teaqty);
        e[1] = findViewById(R.id.sodaqty);
        e[2] = findViewById(R.id.porottaqty);
        e[3] = findViewById(R.id.dosaqty);
        e[4] = findViewById(R.id.masaladosaqty);
        e[5] = findViewById(R.id.snacksqty);
        e[6] = findViewById(R.id.friedriceqty);
        e[7] = findViewById(R.id.icecreamqty);
        t[0] = findViewById(R.id.textView9);
        t[1] = findViewById(R.id.textView10);
        t[2] = findViewById(R.id.textView11);
        t[3] = findViewById(R.id.textView12);
        t[4] = findViewById(R.id.textView13);
        t[5] = findViewById(R.id.textView14);
        t[6] = findViewById(R.id.textView15);
        t[7] = findViewById(R.id.textView16);
        btotal = findViewById(R.id.butotal);
        tot =findViewById(R.id.textTotal);

        btotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total=0;
                for(int i = 0;i<8;i++) {
                    if (e[i].getText().toString().compareTo("") != 0) {
                        n[i] = Integer.valueOf(e[i].getText().toString());
                        //Toast.makeText(student_dash_main.this,String.valueOf(n[i]),Toast.LENGTH_SHORT).show();
                        s[i] = n[i] * p[i];
                        total+=s[i];

                    } else {
                        n[i] = 0;
                        s[i] = 0;
                    }
                }
                //Toast.makeText(student_dash_main.this,String.valueOf(total),Toast.LENGTH_SHORT).show();
                tot.setText(String.valueOf(total));
            }

        });

        b = findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                total=0;
                //Value of Items;
                for(int i = 0;i<8;i++) {
                    if (e[i].getText().toString().compareTo("") != 0) {
                        n[i] = Integer.valueOf(e[i].getText().toString());
                        //Toast.makeText(student_dash_main.this,String.valueOf(n[i]),Toast.LENGTH_SHORT).show();
                        s[i] = n[i] * p[i];
                        total+=s[i];

                    } else {
                        n[i] = 0;
                        s[i] = 0;
                    }
                }
                tot.setText(String.valueOf(total));
                Toast.makeText(student_dash_main.this,String.valueOf(total),Toast.LENGTH_SHORT).show();


                for(int i = 0;i<8;i++) {
                    String s1 = t[i].getText().toString() + " " +String.valueOf(n[i]) + " " + String.valueOf(s[i]) + "\n";
                    //Toast.makeText(student_dash_main.this,s1,Toast.LENGTH_SHORT).show();

                    /*String s2 = " ";
                    String s3 = String.valueOf(n[i]);
                    String s4 = String.valueOf(s[i]);
                    String s5 = "\n";*/
                    if (e[i].getText().toString().compareTo("") != 0)
                        qr = qr.concat(s1);

                }
                qr = qr.concat(String.valueOf(total));
                Toast.makeText(student_dash_main.this,qr,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(student_dash_main.this,student_qr_dash.class);
                i.putExtra("qr", qr);
                startActivity(i);
                //qr = "";
            }
        }

        );


    }


}
