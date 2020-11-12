package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnAddNote;
    Button View_List;
    ListView note_list;

    ArrayAdapter noteArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddNote = findViewById(R.id.btnAddNote);
        View_List = findViewById(R.id.View_List);
        note_list = findViewById(R.id.note_list);

        DataBaseHelper dataBasehelper = new DataBaseHelper(MainActivity.this);
        noteArrayAdapter = new ArrayAdapter<NotesModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBasehelper.getEveryone());
        note_list.setAdapter(noteArrayAdapter);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openAddNote();
            }
        });
        View_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBasehelper = new DataBaseHelper(MainActivity.this);

                noteArrayAdapter = new ArrayAdapter<NotesModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBasehelper.getEveryone());
                note_list.setAdapter(noteArrayAdapter);
                Toast.makeText(MainActivity.this, "List updated", Toast.LENGTH_SHORT).show();

               // Cursor data = dataBasehelper.getData();
               // ArrayList<String> listData = new ArrayList<>();
//
               // while(data.moveToNext()){
               //     listData.add(data.getString(1));
                //    listData.add(data.getString(2));
               // }
                //ListAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, listData);

               // note_list.setAdapter(adapter);

                //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void openAddNote(){
        Intent intent = new Intent(this, AddNote.class);
        startActivity(intent);
    }
}