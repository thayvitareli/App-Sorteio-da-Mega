package com.example.sorteiosdamega;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button drawButton;
    private TextView result;

    private SQLiteDatabase dataBase;


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

        createDataBase();
        drawButton = findViewById(R.id.drawButton);

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

        System.out.println("função geradora de numeros ");
    saveOnDatabase(numerousSortedset.stream().toList());

        result.setText(numerousSortedset.toString());
    }

    public void createDataBase(){
        try{
            System.out.println("criando database");
            dataBase = openOrCreateDatabase("sorteio", MODE_PRIVATE,null);
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS sorteio("
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + " first_number TINYINT, "
                    + " second_number TINYINT, "
                    + " third_number TINYINT, "
                    + " fourth_number TINYINT, "
                    + " fifth_number TINYINT, "
                    + " sixth_number TINYINT " + ")");
            dataBase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveOnDatabase(List<Integer> numerousSorted){
        SQLiteDatabase database = openOrCreateDatabase("sorteio", MODE_PRIVATE, null);
        System.out.println("save on database");

        // Insira os números sorteados na tabela
        ContentValues values = new ContentValues();
        values.put("first_number", numerousSorted.get(0));
        values.put("second_number",  numerousSorted.get(1));
        values.put("third_number",  numerousSorted.get(2));
        values.put("fourth_number",  numerousSorted.get(3));
        values.put("fifth_number",  numerousSorted.get(4));
        values.put("sixth_number",  numerousSorted.get(5));

        long id = database.insert("sorteio", null, values);
        System.out.println("id -> " + id);
        database.close();
    }

    public void selectFromDatabase(){
        SQLiteDatabase database = openOrCreateDatabase("sorteio", MODE_PRIVATE, null);
        System.out.println("select from database");

        try  {
            Cursor cursor = database.rawQuery("SELECT first_number, second_number, third_number, fourth_number, fifth_number, sixth_number FROM sorteio", null);

            int i = 0;
            while(cursor.moveToNext()){
                System.out.println(cursor.getInt(i));
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
