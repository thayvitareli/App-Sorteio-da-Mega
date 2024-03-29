package com.example.sorteiosdamega;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private DatabaseHelper database;
    private Button listResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = new DatabaseHelper(MainActivity.this);

        listResults = findViewById(R.id.listResults);

        listResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(MainActivity.this, ListActivity.class);
               startActivity(intent);
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void drawNumbers(View view) {
        int quantidade = 6;
        int min = 1;
        int max = 60;

        result = findViewById(R.id.result);



        if (quantidade > (max - min + 1)) {
            throw new IllegalArgumentException("Quantidade de números a sortear excede o intervalo especificado.");
        }

        Set numerousSortedset = new HashSet<Integer>();
        Random random = new Random();

        for (int i = 0; i < quantidade; i++) {
            numerousSortedset.add((random.nextInt(max - min + 1) + 1));

        }

        database.saveOnDatabase(numerousSortedset.stream().toList());

        result.setText(numerousSortedset.toString().replaceAll("[\\[\\],]", " "));
    }


}
