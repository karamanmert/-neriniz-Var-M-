package com.example.forev.yorumla.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forev.yorumla.Adapters.MakeupAdapter;
import com.example.forev.yorumla.Models.CommentMakeupAddModel;
import com.example.forev.yorumla.Models.CommentMakeupModel;
import com.example.forev.yorumla.Models.MakeupModel;
import com.example.forev.yorumla.R;
import com.example.forev.yorumla.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeupFragment extends Fragment {

    View view;
    String makyajName,makyajMalzeme;
    ImageView makeup_image;
    TextView fragmentMakyajName,fragmentMakyajMalzeme;
    RatingBar makeup_ratingbar;
    RecyclerView makeupRecyclerView;
    Button makeup_comment_button;
    String makeupid;
    float sum=0;
    float avg;
    int count=0;
    List<CommentMakeupModel> list;
    MakeupAdapter makeupAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_makeup, container, false);
        define();
        getInfo();
        makeup_comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAc();
            }
        });
        return view;
    }
    public void dialogAc()
    {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.alertdialog_comment,null);

        final EditText alertComment = (EditText)view.findViewById(R.id.alert_comment);
        final RatingBar alertRating = (RatingBar)view.findViewById(R.id.alert_ratingbar);

        Button add = (Button)view.findViewById(R.id.alert_commentadd);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        final AlertDialog dialog = alert.create();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!alertComment.getText().toString().equals(""))
                {
                    addComment(alertComment.getText().toString(),String.valueOf(alertRating.getRating()));
                }
                else
                {
                    Toast.makeText(getContext(),"Lütfen tüm alanları doldurunuz..",Toast.LENGTH_LONG).show();
                }
                dialog.cancel();
            }
        });

        dialog.show();
    }
    public void addComment(String comment,String ratingsize)
    {
        Call<CommentMakeupAddModel> req=ManagerAll.getInstance().addMakeupComment(makeupid,comment,ratingsize);
        req.enqueue(new Callback<CommentMakeupAddModel>() {
            @Override
            public void onResponse(Call<CommentMakeupAddModel> call, Response<CommentMakeupAddModel> response) {
                getRating();
                Toast.makeText(getContext(),"Yorumunuz eklendi.",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<CommentMakeupAddModel> call, Throwable t) {

            }
        });
    }
    public void define()
    {
        makyajName=getArguments().get("makyajName").toString();
        makyajMalzeme=getArguments().get("makyajMalzeme").toString();
        makeup_image=(ImageView)view.findViewById(R.id.makeup_image);
        fragmentMakyajName=(TextView)view.findViewById(R.id.fragmentMakyajName);
        fragmentMakyajMalzeme=(TextView)view.findViewById(R.id.fragmentMakyajMalzeme);
        makeup_ratingbar=(RatingBar)view.findViewById(R.id.makeup_ratingbar);
        makeupRecyclerView=(RecyclerView)view.findViewById(R.id.makeupRecyclerView);
        makeup_comment_button=(Button)view.findViewById(R.id.makeup_comment_button);
        makeupid=getArguments().get("id").toString();
        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        makeupRecyclerView.setLayoutManager(layoutManager);

    }

    public void getInfo()
    {
        Call<List<MakeupModel>> req=ManagerAll.getInstance().getMakeup(makyajName,makyajMalzeme);
        req.enqueue(new Callback<List<MakeupModel>>() {
            @Override
            public void onResponse(Call<List<MakeupModel>> call, Response<List<MakeupModel>> response) {
                fragmentMakyajName.setText(response.body().get(0).getMakyajName().toString());
                fragmentMakyajMalzeme.setText(response.body().get(0).getMakyajMalzeme().toString());
                Picasso.get().load(response.body().get(0).getResim()).into(makeup_image);
                getRating();
            }

            @Override
            public void onFailure(Call<List<MakeupModel>> call, Throwable t) {

            }
        });

    }
    public void getRating()
    {
        Call<List<CommentMakeupModel>> req=ManagerAll.getInstance().getMakeupComment(makeupid);
        req.enqueue(new Callback<List<CommentMakeupModel>>() {
            @Override
            public void onResponse(Call<List<CommentMakeupModel>> call, Response<List<CommentMakeupModel>> response) {
                for(int i = 0; i< response.body().size(); i++)
                {
                    sum = sum + Float.parseFloat(response.body().get(i).getRatingsize());
                    count++;
                }

                avg = sum/count;
                makeup_ratingbar.setRating(avg);
                list=response.body();
                makeupAdapter= new MakeupAdapter(list,getContext());
                makeupRecyclerView.setAdapter(makeupAdapter);
            }

            @Override
            public void onFailure(Call<List<CommentMakeupModel>> call, Throwable t) {

            }
        });
    }


}
