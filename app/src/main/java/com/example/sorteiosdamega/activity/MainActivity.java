package com.example.sorteiosdamega.activity;

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

import com.example.sorteiosdamega.database.DatabaseHelper;
import com.example.sorteiosdamega.R;

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

        // instância um objeto da classe DatabaseHelper
        database = new DatabaseHelper(MainActivity.this);

        // recupera o componente no layout que irá exibir o resultado
        listResults = findViewById(R.id.listResults);

        listResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // quando clicado inicia a activity ListActivity, que exibirá todos os sorteios realizados
               Intent intent = new Intent(MainActivity.this, ListActivity.class);
               startActivity(intent);
            }
        });

    }


    //metodo para sortear os números
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void drawNumbers(View view) {
        int quantidade = 6;
        int min = 1;
        int max = 60;

        //recuperamos o component text para exibir o resultado
        result = findViewById(R.id.result);



        //faz uma validação antes de continuar
        if (quantidade > (max - min + 1)) {
            throw new IllegalArgumentException("Quantidade de números a sortear excede o intervalo especificado.");
        }

        // iniciamos um HashSet (consideramos o ideal por não permitir números duplicados na estrutura)
        Set numerousSortedset = new HashSet<Integer>();

        Random random = new Random();

        //realizamos o sorteio e inserimos no HashSet
        for (int i = 0; i < quantidade; i++) {

            numerousSortedset.add((random.nextInt(max - min + 1) + 1));

        }

        //salva no banco de dados
        database.saveOnDatabase(numerousSortedset.stream().toList());


        //exibe o resultado na activity
        result.setText(numerousSortedset.toString().replaceAll("[\\[\\],]", " "));
    }


}
