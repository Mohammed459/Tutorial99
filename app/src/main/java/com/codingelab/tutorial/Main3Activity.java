package com.codingelab.tutorial;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {



        DBHelper mydb;

        Button bttnshow1;
        Button bttnadd;

        Button delete;
        Button show_all;
        Button search;

        EditText editTextName;
        EditText editTextPhone;
        EditText editTextEmail;

        TextView last_name;
        TextView last_phone;
        TextView last_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

            ArrayAdapter<String> arrayAdapter;

            mydb = new DBHelper(this);

            editTextName = (EditText)findViewById(R.id.editName);
            editTextPhone = (EditText)findViewById(R.id.editPhone);
            editTextEmail = (EditText)findViewById(R.id.editEmail);

            bttnadd = (Button) findViewById(R.id.bttnAdd);
            bttnshow1 = (Button) findViewById(R.id.bttnShow1);
             delete = (Button) findViewById(R.id.delete);
            show_all = (Button) findViewById(R.id.show_all);
            search = (Button) findViewById(R.id.search);
///////////////////////////////////insert buton//////////////////////////////////////////////
            bttnadd.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //remove the following toast...
                    Toast.makeText(getApplicationContext(),
                            "bttnOnClick Pressed", Toast.LENGTH_SHORT).show();

                    String getName = editTextName.getText().toString();
                    String getPhone = editTextPhone.getText().toString();
                    String getEmail = editTextEmail.getText().toString();

                    if (mydb.insertContact(getName, getPhone, getEmail)) {
                        Log.v("georgeLog", "Successfully inserted record to db");
                        Toast.makeText(getApplicationContext(),
                                "Inserted:" + getName + ", " + getPhone + "," + getEmail, Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "DID NOT insert to db :-(", Toast.LENGTH_SHORT).show();
                }
            });
//////////////////////buton show rec///////////////////////////////////////
            bttnshow1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.v("georgeLog", "clicked on fetch");
                    Cursor getData=mydb.getData(1); //specific record (id=1)

                    if (getData.moveToNext()) {// data?
                        Log.v("georgeLog", "data found in DB...");
                        String dName = getData.getString(getData.getColumnIndex("name"));
                        String dPhone = getData.getString(getData.getColumnIndex("phone"));
                        String dEmail = getData.getString(getData.getColumnIndex("email"));
                        Toast.makeText(getApplicationContext(),
                                "rec: " + dName + ", " + dPhone + ", " + dEmail, Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),
                                "did not get any data...:-(", Toast.LENGTH_LONG).show();
                    getData.close();
                }
            });



/////////////////// butuon for show_all//////////////////////////////////////////////////////////////////////////////////
            show_all.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    Intent intent = new Intent(getApplicationContext(),ShowAll.class); ///////////////showall class//////
                    startActivity(intent);
                }
            });

////////////////////botuon for  delete/////////////////////////////////////////////////////////
            delete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    Intent intent = new Intent(getApplicationContext(),delete.class); //////////////////////delete class//////
                    startActivity(intent);
                }
            });
///////////////////////////////////////////////search botuon /////////////////////////////////////////////
            search.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),serach.class); ///////////////////////serach class/////////////////
                    startActivity(intent);
                }
            });


        }
    }



