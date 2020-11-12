package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class AddNote extends AppCompatActivity {
    Button add, View_List;
    EditText note_name, note_content;
    ListView note_list;

    ArrayAdapter noteArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        add = findViewById(R.id.add);
        View_List = findViewById(R.id.View_List);
        note_name = findViewById(R.id.note_name);
        note_content = findViewById(R.id.note_content);
        note_list = findViewById(R.id.note_list);





        //button listeners
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                NotesModel notesModel = new NotesModel(-1, note_name.getText().toString(), note_content.getText().toString());
                Toast.makeText(AddNote.this, notesModel.toString(), Toast.LENGTH_SHORT).show();


                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddNote.this);

                boolean success = dataBaseHelper.addOne(notesModel);

                Toast.makeText(AddNote.this, "Success = " + success, Toast.LENGTH_SHORT).show();

            }
        });



    }
}