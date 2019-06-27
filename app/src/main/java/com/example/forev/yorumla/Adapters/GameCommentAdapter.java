package com.example.forev.yorumla.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.forev.yorumla.Models.GameCommentModel;
import com.example.forev.yorumla.R;

import java.util.List;

public class GameCommentAdapter extends RecyclerView.Adapter<GameCommentAdapter.ViewHolder> {

    List<GameCommentModel> list;
    Context context;

    public GameCommentAdapter(List<GameCommentModel> list, Context context){
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView comment;
        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);

            comment = (TextView)itemView.findViewById(R.id.comment);
            ratingBar = (RatingBar)itemView.findViewById(R.id.ratingBar);
        }
    }

    @NonNull
    @Override
    public GameCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameCommentAdapter.ViewHolder viewHolder, int i) {
        viewHolder.comment.setText(list.get(i).getComment());
        viewHolder.ratingBar.setRating(Float.parseFloat(list.get(i).getRatingsize()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
