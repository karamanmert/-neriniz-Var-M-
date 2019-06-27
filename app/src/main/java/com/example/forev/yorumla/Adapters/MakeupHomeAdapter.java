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

import com.example.forev.yorumla.Fragments.MakeupFragment;
import com.example.forev.yorumla.Models.MakeupModel;
import com.example.forev.yorumla.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MakeupHomeAdapter extends RecyclerView.Adapter<MakeupHomeAdapter.MyViewHolder> {

    Context context;
    Fragment fragment;
    List<MakeupModel> list;

    public MakeupHomeAdapter(Context context, List<MakeupModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MakeupHomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_makeup_home,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MakeupHomeAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.makeupHomeTextView.setText(list.get(i).getMakyajName().toString());

        Picasso.get().load(list.get(i).getResim()).resize(0,125).into(myViewHolder.makeupHomeImageView);

       myViewHolder.makeupHomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("makyajName", list.get(i).getMakyajName().toString() );
                bundle.putString("makyajMalzeme", list.get(i).getMakyajMalzeme().toString() );
                bundle.putString("id",list.get(i).getId().toString());
                MakeupFragment makeupFragment = new MakeupFragment();
                makeupFragment.setArguments(bundle);
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, makeupFragment);
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView makeupHomeTextView;
        private ImageView makeupHomeImageView;
        private ConstraintLayout makeupHomeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            makeupHomeTextView = itemView.findViewById(R.id.makeupHomeTextView);
            makeupHomeImageView = itemView.findViewById(R.id.makeupHomeImageView);
            makeupHomeLayout = itemView.findViewById(R.id.makeHomeLayout);
        }
    }
}
