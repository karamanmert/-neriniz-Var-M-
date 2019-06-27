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

import com.example.forev.yorumla.Adapters.GameCommentAdapter;
import com.example.forev.yorumla.Models.CommentGameAddModel;
import com.example.forev.yorumla.Models.GameCommentModel;
import com.example.forev.yorumla.Models.GameModel;
import com.example.forev.yorumla.R;
import com.example.forev.yorumla.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GameFragment extends Fragment {

    View view;
    ImageView game_image;
    TextView fragmentGameName,fragmentGameType;
    Button game_comment_button;

    String gameName,gameType,game_id;

    RatingBar game_ratingbar;
    RecyclerView gameRecyclerView;

    List<GameCommentModel> list;
    GameCommentAdapter gameCommentAdapter;

    int count;
    float avg;
    float sum=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_game, container, false);
        define();
        getInfo();

        game_comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAc();
            }
        });
        return view;
    }

    public void define()
    {
        gameName = getArguments().get("gameName").toString();
        gameType = getArguments().get("gameType").toString();
        game_id = getArguments().get("id").toString();
        game_image = (ImageView)view.findViewById(R.id.game_image);
        fragmentGameName = (TextView)view.findViewById(R.id.fragmentGameName);
        fragmentGameType = (TextView)view.findViewById(R.id.fragmentGameType);
        game_comment_button = (Button)view.findViewById(R.id.game_comment_button);
        game_ratingbar = (RatingBar)view.findViewById(R.id.game_ratingbar);
        gameRecyclerView = (RecyclerView)view.findViewById(R.id.carRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        gameRecyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();


    }


    public void getInfo()
    {
        final Call<List<GameModel>> req = ManagerAll.getInstance().getGame(gameName,gameType);
        req.enqueue(new Callback<List<GameModel>>() {
            @Override
            public void onResponse(Call<List<GameModel>> call, Response<List<GameModel>> response) {
                fragmentGameName.setText(response.body().get(0).getGameName());
                fragmentGameType.setText(response.body().get(0).getGameType());
                Picasso.get().load(response.body().get(0).getResim()).into(game_image);
                getRatingSize();
            }

            @Override
            public void onFailure(Call<List<GameModel>> call, Throwable t) {

            }
        });
    }

    public void getRatingSize()
    {
        final Call<List<GameCommentModel>> req = ManagerAll.getInstance().getGameComment(game_id);
        req.enqueue(new Callback<List<GameCommentModel>>() {
            @Override
            public void onResponse(Call<List<GameCommentModel>> call, Response<List<GameCommentModel>> response) {
                for(int i = 0; i< response.body().size(); i++)
                {
                    sum = sum + Float.parseFloat(response.body().get(i).getRatingsize());
                    count++;
                }

                avg = sum/count;
                game_ratingbar.setRating(avg);

                list = response.body();
                gameCommentAdapter = new GameCommentAdapter(list,getContext());
                gameRecyclerView.setAdapter(gameCommentAdapter);

            }

            @Override
            public void onFailure(Call<List<GameCommentModel>> call, Throwable t) {

            }
        });
    }

    public void addComment(String comment,String ratingSize)
    {
        Call<CommentGameAddModel> req = ManagerAll.getInstance().addGameComment(game_id,comment,ratingSize);
        req.enqueue(new Callback<CommentGameAddModel>() {
            @Override
            public void onResponse(Call<CommentGameAddModel> call, Response<CommentGameAddModel> response) {
                getRatingSize();
            }

            @Override
            public void onFailure(Call<CommentGameAddModel> call, Throwable t) {

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


}
