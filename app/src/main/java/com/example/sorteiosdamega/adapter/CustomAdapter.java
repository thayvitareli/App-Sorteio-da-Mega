package com.example.sorteiosdamega.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sorteiosdamega.R;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;

    // Possui um array para cada conjunto de numero sorteado. Ex: todos os primeiros números sorteados, estão salvos no array first_number
    private ArrayList<Integer> id, first_number,second_number,third_number,fourth_number,fifth_number,sixth_number;
    private SQLiteDatabase dataBase;

    public CustomAdapter(Context context, ArrayList<Integer> id, ArrayList<Integer> first_number, ArrayList<Integer> second_number, ArrayList<Integer> third_number, ArrayList<Integer> fourth_number, ArrayList<Integer> fifth_number, ArrayList<Integer> sixth_number) {
        this.context = context;
        this.id = id;
        this.first_number = first_number;
        this.second_number = second_number;
        this.third_number = third_number;
        this.fourth_number = fourth_number;
        this.fifth_number = fifth_number;
        this.sixth_number = sixth_number;
    }


    // Faz a troca do conteúdo
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_draw, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    //    holder.id_text.setText(String.valueOf(id.get(position)));
        holder.first_text.setText(String.valueOf(first_number.get(position)));
        holder.second_text.setText(String.valueOf(second_number.get(position)));
        holder.third_text.setText(String.valueOf(third_number.get(position)));
        holder.fourth_text.setText(String.valueOf(fourth_number.get(position)));
        holder.fifth_text.setText(String.valueOf(fifth_number.get(position)));
        holder.sixth_text.setText(String.valueOf(sixth_number.get(position)));
    }

    //conta a quantidade de itens da lista
    @Override
    public int getItemCount() {
        int maxSize = Math.max(id.size(), Math.max(first_number.size(), Math.max(second_number.size(),
                Math.max(third_number.size(), Math.max(fourth_number.size(),
                        Math.max(fifth_number.size(), sixth_number.size()))))));
        return maxSize;
    }


    // Método para criar novo View Holder
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id_text, first_text, second_text, third_text, fourth_text,fifth_text, sixth_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_text = itemView.findViewById(R.id.id_text);
            first_text = itemView.findViewById(R.id.first_text);
            second_text = itemView.findViewById(R.id.second_text);
            third_text = itemView.findViewById(R.id.third_text);
            fourth_text = itemView.findViewById(R.id.fourth_text);
            fifth_text = itemView.findViewById(R.id.fifth_text);
            sixth_text = itemView.findViewById(R.id.sixth_text);

        }
    }


}



