package com.example.canteenmanagement;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class canteen_dash_main extends AppCompatActivity {
    TextView textView1,textView2;
    //AlertDialog b;
    int tot,i,lenstr;
    String s,a;
    Button p,r,lg;
    int total = 0;
    int flag=1,add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_dash_main);
        textView1 = findViewById(R.id.canteeendash1textView);
        textView2 = findViewById(R.id.textView5);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, PERMISSION_GRANTED);

        p=findViewById(R.id.button5);
        r =findViewById(R.id.button6);
        lg = findViewById(R.id.button8);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add==1){
                    tot = tot + total;
                    textView1.setText("₹"+ String.valueOf(tot));
                    add = 0;
                    flag=0;
                }
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tot-total>=0&&flag==0&&add==0) {
                    tot = tot - total;
                    textView1.setText("₹" + String.valueOf(tot));
                    flag=1;
                    add=1;
                }
            }
        });

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ctom = new Intent(canteen_dash_main.this,MainActivity.class);
                startActivity(ctom);
            }
        });
    }
    public void ScanButton(View view){
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data){
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode,data);

        if(intentResult!= null){
            AlertDialog.Builder b = new AlertDialog.Builder(getApplicationContext());
            if(intentResult.getContents() == null) {
                b.setCancelable(true);
                b.setTitle("Error");
                b.setMessage("Cancelled");
                //textView.setText("Cancelled");
            }
            else {
                b.setCancelable(true);
                b.setTitle("Order");
                b.setMessage(intentResult.getContents().toString());
                //s = intentResult.getContents();
                /*a = s.substring(s.length()-3);
                i = Integer.parseInt(a);
                tot = tot +i;*/
                s = intentResult.getContents();
                textView2.setText(s);
                lenstr = s.length() - 1;
                total = 0;
                int i = 1;
                while (Character.isDigit(s.charAt(lenstr))) {
                    total = total + (Character.getNumericValue(s.charAt(lenstr))) * i;
                    i = i * 10;
                    lenstr--;
                }
                flag=0;
                add=1;
            }
               //Toast.makeText(this,intentResult.getContents(),Toast.LENGTH_SHORT).show();

               //Toast.makeText(this,s.substring(s.length()-2),Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode,data);
    }

}
