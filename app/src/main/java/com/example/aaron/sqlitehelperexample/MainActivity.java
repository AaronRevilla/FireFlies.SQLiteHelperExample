package com.example.aaron.sqlitehelperexample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout personList;
    TextView person;
    DataBaseHandler db;
    EditText pName;
    EditText pNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pName = ((EditText) findViewById(R.id.pName));
        pNumber = ((EditText) findViewById(R.id.pNumber));
        db = new DataBaseHandler(this);
        personList = ((LinearLayout) findViewById(R.id.personList));
        readPersonsFromDB();
    }

    public void readPersonsFromDB(){
        personList.removeAllViews();
        List<Contact> personas = db.getAllContacts();
        for(Contact person: personas){
            TextView txtView = new TextView(this);
            txtView.setText(person.toString());
            personList.addView(txtView);
        }
    }


    public void addPersonAction(View view) {

        Contact person = new Contact(pName.getText().toString(), pNumber.getText().toString());
        db.addContact(person);
        readPersonsFromDB();
        pName.setText("");
        pNumber.setText("");
    }
}
