package com.example.sqlitehelperapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button addTodoBtn;
    private EditText sujectEditText;
    private EditText descEditText;
    private  DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add_main);

        sujectEditText=findViewById(R.id.subject_edittext);
        descEditText=findViewById(R.id.descriptsion_editText);
        addTodoBtn=findViewById(R.id.add_record);

        dbManager=new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add_record:
                final String name=sujectEditText.getText().toString();
                final String desc=descEditText.getText().toString();

                dbManager.insert(name,desc);
                Intent intent=new Intent( AddMainActivity.this,
                        MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}