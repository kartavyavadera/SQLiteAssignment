package com.example.sqliteassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etSno,etSname,etSage;
    Button btnLogin,btnRegister;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper=new DBHelper(this);
        etSno=findViewById(R.id.etSno);
        etSname=findViewById(R.id.etSname);
        etSage=findViewById(R.id.etSage);
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ssno=etSno.getText().toString();
               Boolean flag=dbHelper.getInfo(ssno);

               if(flag)
               {
                   Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                   startActivity(intent);
                   Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();


                   SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                   SharedPreferences.Editor myEdit = sharedPreferences.edit();
                   myEdit.putString("sno", etSno.getText().toString());
                   myEdit.commit();

               }
               else
               {
                   Toast.makeText(LoginActivity.this,"User not registered.....",Toast.LENGTH_LONG).show();
               }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sno=etSno.getText().toString();
                String sname=etSname.getText().toString();
                String sage=etSage.getText().toString();

                if(sno.equals("")||sname.equals("")||sage.equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Enter All Values!!",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Boolean i= dbHelper.Insert(sno,sname,sage);
                    if(i==true)
                    {
                        etSname.setText("");
                        etSno.setText("");
                        etSage.setText("");
                        Toast.makeText(LoginActivity.this,"Record inserted successfully",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Record not inserted",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

    }
}