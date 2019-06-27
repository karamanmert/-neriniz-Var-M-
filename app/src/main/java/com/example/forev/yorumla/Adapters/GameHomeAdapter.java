package com.example.forev.yorumla.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forev.yorumla.Fragments.GameFragment;
import com.example.forev.yorumla.Models.GameModel;
import com.example.forev.yorumla.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GameHomeAdapter extends RecyclerView.Adapter<GameHomeAdapter.MyViewHolder> {

    Context context;
    Fragment fragment;
    List<GameModel> list;

    public GameHomeAdapter(Context context, List<GameModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GameHomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_game_home,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GameHomeAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.gameHomeTextView.setText(list.get(i).getGameName().toString());

        Picasso.get().load(list.get(i).getResim()).resize(0,125).into(myViewHolder.gameHomeImageView);

       myViewHolder.gameHomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("gameName", list.get(i).getGameName().toString() );
                bundle.putString("gameType", list.get(i).getGameType().toString() );
                bundle.putString("id", list.get(i).getId().toString() );
                GameFragment gameFragment = new GameFragment();
                gameFragment.setArguments(bundle);
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, gameFragment);
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView gameHomeTextView;
        private ImageView gameHomeImageView;
        private ConstraintLayout gameHomeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            gameHomeImageView = itemView.findViewById(R.id.gameHomeImageView);
            gameHomeTextView = itemView.findViewById(R.id.gameHomeTextView);
            gameHomeLayout = itemView.findViewById(R.id.gameHomeLayout);
        }
    }
}
