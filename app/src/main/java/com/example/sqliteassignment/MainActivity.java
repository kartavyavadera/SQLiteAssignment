package com.example.sqliteassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etUpdateSno,etUpdateSname,etUpdateSage;
    Button btnUpdate,btnDelete,btnView,btnLogout;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new DBHelper(this);
        etUpdateSno=findViewById(R.id.etUpdateSno);
        etUpdateSname=findViewById(R.id.etUpdateSname);
        etUpdateSage=findViewById(R.id.etUpdateSage);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        btnView=findViewById(R.id.btnView);
        btnLogout=findViewById(R.id.btnLogout);




        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=dbHelper.getAll();
                if(c.getCount()==0)
                {
                    Toast.makeText(MainActivity.this,"No Record Found!!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    StringBuffer sb=new StringBuffer();
                    while (c.moveToNext())
                    {
                        sb.append("Student No : "+c.getString(0)+"\n");
                        sb.append("Student Name : "+c.getString(1)+"\n");
                        sb.append("Student Age : "+c.getString(2)+"\n\n\n");
                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Student Data");
                    builder.setMessage(sb.toString());
                    builder.show();
                }

            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.clear();
                myEdit.commit();
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Logout Successfully",Toast.LENGTH_LONG).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ssno=etUpdateSno.getText().toString();
                Boolean i=dbHelper.delete(ssno);

                if(i)
                {
                    Toast.makeText(MainActivity.this,"Record deleted Successfully",Toast.LENGTH_LONG).show();
                    Cursor c=dbHelper.getAll();
                    if(c.getCount()==0)
                    {
                        Toast.makeText(MainActivity.this,"No Record Found!!",Toast.LENGTH_LONG).show();
                    }
                    StringBuffer sb=new StringBuffer();
                    while (c.moveToNext())
                    {
                        sb.append("Student No : "+c.getString(0)+"\n");
                        sb.append("Student Name : "+c.getString(1)+"\n");
                        sb.append("Student Age : "+c.getString(2)+"\n\n\n");
                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Student Data");
                    builder.setMessage(sb.toString());
                    builder.show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Error!!",Toast.LENGTH_LONG).show();

                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ssno=etUpdateSno.getText().toString();
                String sname=etUpdateSname.getText().toString();
                String sage=etUpdateSage.getText().toString();

                Boolean i=dbHelper.update(ssno,sname,sage);

                if(i)
                {
                    Toast.makeText(MainActivity.this,"Record updated Successfully",Toast.LENGTH_LONG).show();
                    Cursor c=dbHelper.getAll();
                    if(c.getCount()==0)
                    {
                        Toast.makeText(MainActivity.this,"No Record Found!!",Toast.LENGTH_LONG).show();
                    }
                    StringBuffer sb=new StringBuffer();
                    while (c.moveToNext())
                    {
                        sb.append("Student No : "+c.getString(0)+"\n");
                        sb.append("Student Name : "+c.getString(1)+"\n");
                        sb.append("Student Age : "+c.getString(2)+"\n\n\n");
                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Student Data");
                    builder.setMessage(sb.toString());
                    builder.show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Error!!",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}