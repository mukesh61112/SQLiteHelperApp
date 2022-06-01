package com.example.sqlitehelperapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyMainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText titleText;
    private Button udateBtn,deleteBtn;
    private  EditText descText;

    private  long id_;
    private  DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify_main);

        dbManager=new DBManager(this);
        dbManager.open();

        titleText=findViewById(R.id.subject_edittext);
        descText=findViewById(R.id.descriptsion_editText);

         udateBtn=findViewById(R.id.btn_update);
         deleteBtn=findViewById(R.id.btn_delete);

        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        String name=intent.getStringExtra("title");
        String desc=intent.getStringExtra("desc");

        id_=Long.parseLong(id);
        titleText.setText(name);
        descText.setText(desc);
        udateBtn.setOnClickListener((View.OnClickListener) this);


        deleteBtn.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_update:
                String title=titleText.getText().toString();
                String desc =descText.getText().toString();

                dbManager.update(id_,title,desc);
                this.returnHome();
                break;
            case R.id.btn_delete:
                dbManager.delete(id_);
                this.returnHome();
                break;
        }
    }
    public void returnHome()
    {
        Intent intent=new Intent(getApplicationContext(),
                MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

    }
}