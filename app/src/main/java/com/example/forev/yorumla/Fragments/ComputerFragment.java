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

import com.example.forev.yorumla.Adapters.ComputerCommentAdapter;
import com.example.forev.yorumla.Models.CommentComputerAddModel;
import com.example.forev.yorumla.Models.ComputerCommentModel;
import com.example.forev.yorumla.Models.ComputerModel;
import com.example.forev.yorumla.R;
import com.example.forev.yorumla.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ComputerFragment extends Fragment {

    View view;
    String markaName,hardwareName,id;
    TextView computer_marka,computer_hardware;
    ImageView computer_image;
    RatingBar computer_ratingbar;
    Button computer_comment_button;
    int count;
    float avg;
    float sum=0;

    RecyclerView computerRecyclerView;
    ComputerCommentAdapter computerCommentAdapter;
    List<ComputerCommentModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_computer, container, false);
        define();
        getInfo();
        computer_comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAc();
            }
        });
        return view;
    }

    public void define()
    {
        markaName = getArguments().get("markaName").toString();
        hardwareName = getArguments().getString("hardwareName").toString();
        id = getArguments().getString("id").toString();
        computer_marka = (TextView)view.findViewById(R.id.computer_marka);
        computer_hardware = (TextView)view.findViewById(R.id.computer_hardware);
        computer_image = (ImageView)view.findViewById(R.id.computer_image);
        computer_ratingbar = (RatingBar)view.findViewById(R.id.computer_ratingbar);
        computer_comment_button = (Button)view.findViewById(R.id.computer_comment_button);
        computerRecyclerView = (RecyclerView)view.findViewById(R.id.computerRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        computerRecyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();

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

    public void getInfo()
    {
        Call<List<ComputerModel>> req = ManagerAll.getInstance().getComputer(hardwareName,markaName);
        req.enqueue(new Callback<List<ComputerModel>>() {
            @Override
            public void onResponse(Call<List<ComputerModel>> call, Response<List<ComputerModel>> response) {
                Picasso.get().load(response.body().get(0).getResim()).into(computer_image);
                computer_marka.setText(markaName);
                computer_hardware.setText(hardwareName);
                getRating();

            }

            @Override
            public void onFailure(Call<List<ComputerModel>> call, Throwable t) {

            }
        });
    }

    public void getRating()
    {
        Call<List<ComputerCommentModel>> req = ManagerAll.getInstance().getComputerComment(id);
        req.enqueue(new Callback<List<ComputerCommentModel>>() {
            @Override
            public void onResponse(Call<List<ComputerCommentModel>> call, Response<List<ComputerCommentModel>> response) {
                for(int i = 0; i< response.body().size(); i++)
                {
                    sum = sum + Float.parseFloat(response.body().get(i).getRatingsize());
                    count++;
                }

                avg = sum/count;

                computer_ratingbar.setRating(avg);

                list = response.body();
                computerCommentAdapter = new ComputerCommentAdapter(list,getContext());
                computerRecyclerView.setAdapter(computerCommentAdapter);
            }

            @Override
            public void onFailure(Call<List<ComputerCommentModel>> call, Throwable t) {

            }
        });
    }

    public void addComment(String comment,String ratingsize)
    {
        Call<CommentComputerAddModel> req = ManagerAll.getInstance().addComputerModel(id,comment,ratingsize);
        req.enqueue(new Callback<CommentComputerAddModel>() {
            @Override
            public void onResponse(Call<CommentComputerAddModel> call, Response<CommentComputerAddModel> response) {
                getRating();
            }

            @Override
            public void onFailure(Call<CommentComputerAddModel> call, Throwable t) {

            }
        });
    }


}
