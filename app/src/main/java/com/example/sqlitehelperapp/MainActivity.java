package com.example.sqlitehelperapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private  DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String [] from=new String[]{ DatabaseHelper.ID,
         DatabaseHelper.SUBJECT,DatabaseHelper.DESC};

    final int[] to=new int[]{ R.id.id,R.id.title,R.id.desc};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);
        dbManager=new DBManager(this);
        dbManager.open();
        Cursor cursor=dbManager.fetch();

        listView=findViewById(R.id.listview);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter=new SimpleCursorAdapter( this,R.layout.activity_view_record,
                cursor,from,to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView idTextView=view.findViewById(R.id.id);
                TextView titleTextView=view.findViewById(R.id.title);
                TextView descTextView=view.findViewById(R.id.desc);

                String id=idTextView.getText().toString();
                String title=titleTextView.getText().toString();
                String desc=descTextView.getText().toString();

                Intent intent=new Intent(getApplicationContext(),
                        ModifyMainActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("desc",desc);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.add_record)
        {
            Intent intent=new Intent(this,AddMainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}