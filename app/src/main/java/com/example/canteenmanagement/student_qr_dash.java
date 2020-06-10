package com.example.canteenmanagement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class student_qr_dash extends AppCompatActivity {

    ImageView i;
    Button b1,b2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_qr_dash);
        Bundle bundle = getIntent().getExtras();
        String qr = getIntent().getStringExtra("qr");
        i = findViewById(R.id.imageView7);
        Toast.makeText(this,qr + "New Activity",Toast.LENGTH_SHORT).show();

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            try{
                BitMatrix bitMatrix =  qrCodeWriter.encode(qr, BarcodeFormat.QR_CODE,200,200);
                Bitmap bitmap = Bitmap.createBitmap(200,200,Bitmap.Config.RGB_565);


                for(int x=0;x<200;x++){
                    for(int y = 0;y<200;y++){
                        bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK:Color.WHITE);
                    }
                }

                i.setImageBitmap(bitmap);
                Toast.makeText(this, "Scan This QR at Canteen \n Screenshot If necessary", Toast.LENGTH_LONG).show();
            /*String path = Environment.getExternalStorageDirectory().toString();
            File filename = new File (path,"QR.PNG");


          /*  try (FileOutputStream out = new FileOutputStream(filename)) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (IOException e) {
                e.printStackTrace();
            }*/


            }
            catch (Exception e){
                e.printStackTrace();
            }
            b1 = findViewById(R.id.button3);

            b1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent i = new Intent(student_qr_dash.this,student_dash_main.class);
                    startActivity(i);
                }
        });
            b2 = findViewById(R.id.button4);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(student_qr_dash.this,MainActivity.class);
                    startActivity(i);
                }
            });
        }


}
