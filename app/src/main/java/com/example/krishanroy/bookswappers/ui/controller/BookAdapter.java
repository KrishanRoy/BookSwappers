package com.example.krishanroy.bookswappers.ui.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.krishanroy.bookswappers.R;
import com.example.krishanroy.bookswappers.ui.model.Persons;
import com.example.krishanroy.bookswappers.ui.view.BookViewHolder;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    List<Persons> personsList;

    public BookAdapter(List<Persons> personsList) {
        this.personsList = personsList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BookViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_itemview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int position) {
        bookViewHolder.onBind(personsList.get(position));
    }

    @Override
    public int getItemCount() {
        return personsList.size();
    }
}
