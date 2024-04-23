package com.example.sorteiosdamega.activity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sorteiosdamega.database.DatabaseHelper;
import com.example.sorteiosdamega.R;
import com.example.sorteiosdamega.adapter.CustomAdapter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private DatabaseHelper database;
    private ArrayList<Integer> id, first_number,second_number,third_number,fourth_number,fifth_number,sixth_number;
    ImageView empty_imageview;
    TextView no_data;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        database = new DatabaseHelper(ListActivity.this);

        id = new ArrayList<>();
        first_number= new ArrayList<>();
        second_number = new ArrayList<>();
        third_number = new ArrayList<>();
        fourth_number= new ArrayList<>();
        fifth_number= new ArrayList<>();
        sixth_number= new ArrayList<>();

        storeDataInArrays();


        System.out.println("ids");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            id.forEach(value -> System.out.println(value));
        }

        customAdapter = new CustomAdapter(ListActivity.this, id, first_number, second_number,
                third_number, fourth_number, fifth_number, sixth_number);
            recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
    }

    private void storeDataInArrays(){
        System.out.println("readDatabase");
        Cursor cursor = database.readAllData();

        if(cursor.getCount() > 0){

            while (cursor.moveToNext()) {
                id.add(cursor.getInt(0));
                first_number.add(cursor.getInt(1));
                second_number.add(cursor.getInt(2));
                third_number.add(cursor.getInt(3));
                fourth_number.add(cursor.getInt(4));
                fifth_number.add(cursor.getInt(5));
                sixth_number.add(cursor.getInt(6));

            }
        }

        }
    }
