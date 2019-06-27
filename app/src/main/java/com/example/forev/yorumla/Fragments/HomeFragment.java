package com.example.forev.yorumla.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forev.yorumla.Adapters.CarHomeAdapter;
import com.example.forev.yorumla.Adapters.ComputerHomeAdapter;
import com.example.forev.yorumla.Adapters.GameHomeAdapter;
import com.example.forev.yorumla.Adapters.MakeupHomeAdapter;
import com.example.forev.yorumla.Models.CarModel;
import com.example.forev.yorumla.Models.ComputerModel;
import com.example.forev.yorumla.Models.GameModel;
import com.example.forev.yorumla.Models.MakeupModel;
import com.example.forev.yorumla.R;
import com.example.forev.yorumla.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    View view;

    private List<CarModel> list;
    private RecyclerView homeCarRecyclerView;
    private CarHomeAdapter carHomeAdapter;


    private List<GameModel> gameModelList;
    private RecyclerView homeGameRecyclerView;
    private GameHomeAdapter gameHomeAdapter;

    private List<MakeupModel> makeupModelList;
    private RecyclerView homeMakeupRecyclerView;
    private MakeupHomeAdapter makeupHomeAdapter;

    private List<ComputerModel> computerModelList;
    private RecyclerView homecomputerRecyclerView;
    private ComputerHomeAdapter computerHomeAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        define();
        listCarHome();
        listGameHome();
        listMakeupHome();
        computerHome();
        return view;
    }

    public void define()
    {
        homeCarRecyclerView = (RecyclerView)view.findViewById(R.id.homeCarRecyclerView);
        list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        homeCarRecyclerView.setLayoutManager(layoutManager);

        homeGameRecyclerView = (RecyclerView)view.findViewById(R.id.homeGameRecyclerView);
        gameModelList= new ArrayList<>();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        homeGameRecyclerView.setLayoutManager(layoutManager2);

        homeMakeupRecyclerView = (RecyclerView)view.findViewById(R.id.homeMakeupRecyclerView);
        makeupModelList= new ArrayList<>();
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        homeMakeupRecyclerView.setLayoutManager(layoutManager3);

        homecomputerRecyclerView = (RecyclerView)view.findViewById(R.id.homeComputerRecyclerView);
        computerModelList= new ArrayList<>();
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        homecomputerRecyclerView.setLayoutManager(layoutManager4);

    }

    public void listCarHome()
    {
        Call<List<CarModel>> req = ManagerAll.getInstance().getCarHome();
        req.enqueue(new Callback<List<CarModel>>() {
            @Override
            public void onResponse(Call<List<CarModel>> call, Response<List<CarModel>> response) {
                list = response.body();
                carHomeAdapter = new CarHomeAdapter(getContext(),list,getActivity());
                homeCarRecyclerView.setAdapter(carHomeAdapter);
            }

            @Override
            public void onFailure(Call<List<CarModel>> call, Throwable t) {

            }
        });
    }

    public void listGameHome()
    {
        Call<List<GameModel>> req = ManagerAll.getInstance().getGameHome();
        req.enqueue(new Callback<List<GameModel>>() {
            @Override
            public void onResponse(Call<List<GameModel>> call, Response<List<GameModel>> response) {
                gameModelList = response.body();
                gameHomeAdapter = new GameHomeAdapter(getContext(),gameModelList);
                homeGameRecyclerView.setAdapter(gameHomeAdapter);
            }

            @Override
            public void onFailure(Call<List<GameModel>> call, Throwable t) {

            }
        });
    }
    public void listMakeupHome()
    {
        Call<List<MakeupModel>> req=ManagerAll.getInstance().getMakeupHome();
        req.enqueue(new Callback<List<MakeupModel>>() {
            @Override
            public void onResponse(Call<List<MakeupModel>> call, Response<List<MakeupModel>> response) {
                makeupModelList=response.body();
                makeupHomeAdapter=new MakeupHomeAdapter(getContext(),makeupModelList);
                homeMakeupRecyclerView.setAdapter(makeupHomeAdapter);
            }

            @Override
            public void onFailure(Call<List<MakeupModel>> call, Throwable t) {

            }
        });
    }
    public void computerHome()
    {
        Call<List<ComputerModel>> req=ManagerAll.getInstance().getComputerHome();
        req.enqueue(new Callback<List<ComputerModel>>() {
            @Override
            public void onResponse(Call<List<ComputerModel>> call, Response<List<ComputerModel>> response) {
                computerModelList=response.body();
                computerHomeAdapter= new ComputerHomeAdapter(getContext(),computerModelList);
                homecomputerRecyclerView.setAdapter(computerHomeAdapter);
            }

            @Override
            public void onFailure(Call<List<ComputerModel>> call, Throwable t) {

            }
        });
    }


}
