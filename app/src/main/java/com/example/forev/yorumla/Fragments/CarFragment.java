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

import com.example.forev.yorumla.Adapters.CommentAdapter;
import com.example.forev.yorumla.Models.CarModel;
import com.example.forev.yorumla.Models.CommentAddModel;
import com.example.forev.yorumla.Models.CommentModel;
import com.example.forev.yorumla.R;
import com.example.forev.yorumla.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarFragment extends Fragment {

    View view;
    String car_id,userid,marka,model,motor;
    TextView car_marka,car_model,car_motor;
    ImageView car_image;
    RatingBar car_ratingbar;
    Button comment_button;
    int count;
    float avg;
    float sum=0;

    RecyclerView carRecyclerView;
    CommentAdapter commentAdapter;
    List<CommentModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_car, container, false);
        define();
        getInfo();

        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAc();
            }
        });

        return view;
    }

    public void define()
    {
        marka = getArguments().get("marka").toString();
        model = getArguments().get("model").toString();
        motor = getArguments().get("motor").toString();
        car_id = getArguments().get("id").toString();
        userid = getArguments().get("userid").toString();
        car_marka = (TextView)view.findViewById(R.id.car_marka);
        car_model = (TextView)view.findViewById(R.id.car_model);
        car_motor = (TextView)view.findViewById(R.id.car_motor);
        car_image = (ImageView)view.findViewById(R.id.car_image);
        car_ratingbar = (RatingBar)view.findViewById(R.id.car_ratingbar);
        comment_button = (Button)view.findViewById(R.id.comment_button);

        carRecyclerView = (RecyclerView)view.findViewById(R.id.carRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        carRecyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
    }

    public void getInfo()
    {
        Call<List<CarModel>> req = ManagerAll.getInstance().getCar(marka,model,motor);
        req.enqueue(new Callback<List<CarModel>>() {
            @Override
            public void onResponse(Call<List<CarModel>> call, Response<List<CarModel>> response) {
                Picasso.get().load(response.body().get(0).getResim()).into(car_image);
                car_marka.setText("Marka: " + marka);
                car_model.setText("Model: " +model);
                car_motor.setText("Motor: " +motor);
                getRatingSize();
            }

            @Override
            public void onFailure(Call<List<CarModel>> call, Throwable t) {

            }
        });
    }

    public void getRatingSize()
    {
        final Call<List<CommentModel>> req = ManagerAll.getInstance().getComment(car_id);
        req.enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {

                for(int i = 0; i< response.body().size(); i++)
                {
                    sum = sum + Float.parseFloat(response.body().get(i).getRatingsize());
                    count++;
                }

                avg = sum/count;

                car_ratingbar.setRating(avg);

                list = response.body();
                commentAdapter = new CommentAdapter(list,getContext());
                carRecyclerView.setAdapter(commentAdapter);
            }

            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable t) {

            }
        });
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

    public void addComment(String comment,String rating)
    {
        Call<CommentAddModel> req = ManagerAll.getInstance().addComment(userid,car_id,comment,rating);
        req.enqueue(new Callback<CommentAddModel>() {
            @Override
            public void onResponse(Call<CommentAddModel> call, Response<CommentAddModel> response) {
                getRatingSize();
            }

            @Override
            public void onFailure(Call<CommentAddModel> call, Throwable t) {

            }
        });
    }


}
