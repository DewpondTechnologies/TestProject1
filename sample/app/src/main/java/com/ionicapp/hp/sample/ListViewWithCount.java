package com.ionicapp.hp.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewWithCount extends AppCompatActivity
{

    EditText editText;
    Button addButton;
    ListView listView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    String listviewCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_with_count);


        editText = (EditText) findViewById(R.id.editText);
        addButton = (Button) findViewById(R.id.addItem);
        listView = (ListView) findViewById(R.id.listView);
        listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (listView.getCount() <10 )
                {
                    listItems.add(editText.getText().toString());
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                }
                else
                {
                    addButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
//                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG).show();
//                Snackbar.make(v, listItems.toString().trim(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                listviewCount = ""+listView.getCount();
                Toast.makeText(getApplicationContext(), listviewCount ,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
