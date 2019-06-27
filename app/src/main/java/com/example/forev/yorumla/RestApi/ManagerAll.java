package com.example.forev.yorumla.RestApi;

import com.example.forev.yorumla.Models.CarModel;
import com.example.forev.yorumla.Models.CommentAddModel;
import com.example.forev.yorumla.Models.CommentComputerAddModel;
import com.example.forev.yorumla.Models.CommentGameAddModel;
import com.example.forev.yorumla.Models.CommentMakeupAddModel;
import com.example.forev.yorumla.Models.CommentMakeupModel;
import com.example.forev.yorumla.Models.CommentModel;
import com.example.forev.yorumla.Models.ComputerCommentModel;
import com.example.forev.yorumla.Models.ComputerModel;
import com.example.forev.yorumla.Models.GameCommentModel;
import com.example.forev.yorumla.Models.GameModel;
import com.example.forev.yorumla.Models.LoginModel;
import com.example.forev.yorumla.Models.MakeupModel;
import com.example.forev.yorumla.Models.RegisterModel;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private  static ManagerAll ourInstance = new ManagerAll();

    public  static synchronized ManagerAll getInstance()
    {
        return  ourInstance;
    }

    public Call<RegisterModel> addUser(String email , String username, String password)
    {
        Call<RegisterModel> x = getRestApi().registerUser(email,username,password);
        return  x ;
    }

    public Call<LoginModel> loginUser(String email , String password)
    {
        Call<LoginModel> x = getRestApi().loginUser(email,password);
        return  x ;
    }

    public Call<List<CarModel>> getCar(String marka, String model, String motor)
    {
        Call<List<CarModel>> x = getRestApi().getCar(marka,model,motor);
        return  x ;
    }

    public Call<List<CommentModel>> getComment(String id)
    {
        Call<List<CommentModel>> x = getRestApi().getComment(id);
        return  x ;
    }

    public Call<CommentAddModel> addComment(String userid , String carid, String comment, String ratingsize)
    {
        Call<CommentAddModel> x = getRestApi().addComment(userid,carid,comment,ratingsize);
        return  x ;
    }

    public Call<List<GameModel>> getGame(String gameName,String gameType)
    {
        Call<List<GameModel>> x = getRestApi().getGame(gameName,gameType);
        return  x ;
    }

    public Call<List<GameCommentModel>> getGameComment(String id)
    {
        Call<List<GameCommentModel>> x = getRestApi().getGameComment(id);
        return  x ;
    }

    public Call<CommentGameAddModel> addGameComment(String gameid, String comment, String ratingsize)
    {
        Call<CommentGameAddModel> x = getRestApi().addGameComment(gameid,comment,ratingsize);
        return  x ;
    }
    public Call<List<MakeupModel>> getMakeup(String makyajName, String makyajMalzeme)
    {
        Call<List<MakeupModel>> x = getRestApi().getMakeup(makyajName,makyajMalzeme);
        return  x ;
    }
    public Call<List<CommentMakeupModel>> getMakeupComment(String id)
    {
        Call<List<CommentMakeupModel>> x = getRestApi().getMakeupComment(id);
        return  x ;
    }
    public Call<CommentMakeupAddModel> addMakeupComment(String makeupid, String comment, String ratingsize)
    {
        Call<CommentMakeupAddModel> x = getRestApi().addMakeupModel(makeupid,comment,ratingsize);
        return  x ;
    }

    public Call<List<ComputerModel>> getComputer(String hardwarename,String markaname)
    {
        Call<List<ComputerModel>> x = getRestApi().getComputer(hardwarename,markaname);
        return  x ;
    }

    public Call<List<ComputerCommentModel>> getComputerComment(String id)
    {
        Call<List<ComputerCommentModel>> x = getRestApi().getComputerComment(id);
        return  x ;
    }

    public Call<CommentComputerAddModel> addComputerModel(String computerid, String comment, String ratingsize)
    {
        Call<CommentComputerAddModel> x = getRestApi().addComputerModel(computerid,comment,ratingsize);
        return  x ;
    }

    public Call<List<CarModel>> getCarHome()
    {
        Call<List<CarModel>> x = getRestApi().getCarHome();
        return  x ;
    }

    public Call<List<GameModel>> getGameHome()
    {
        Call<List<GameModel>> x = getRestApi().getGameHome();
        return  x ;
    }
    public Call<List<MakeupModel>> getMakeupHome()
    {
        Call<List<MakeupModel>> x = getRestApi().getMakeupHome();
        return  x ;
    }

    public Call<List<ComputerModel>> getComputerHome()
    {
        Call<List<ComputerModel>> x = getRestApi().getComputerHome();
        return  x ;
    }
}
