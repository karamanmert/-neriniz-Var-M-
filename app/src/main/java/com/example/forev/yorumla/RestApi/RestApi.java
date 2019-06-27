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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @FormUrlEncoded
    @POST("/yorumfolder/register.php")
    Call<RegisterModel> registerUser(@Field("email") String email, @Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/yorumfolder/login.php")
    Call<LoginModel> loginUser(@Field("email") String email, @Field("password") String username);

    @FormUrlEncoded
    @POST("/yorumfolder/listcar.php")
    Call<List<CarModel>> getCar(@Field("marka") String marka, @Field("model") String model, @Field("motor") String motor);

    @FormUrlEncoded
    @POST("/yorumfolder/listcomment.php")
    Call<List<CommentModel>> getComment(@Field("id") String id);

    @FormUrlEncoded
    @POST("/yorumfolder/addcomment.php")
    Call<CommentAddModel> addComment(@Field("userid") String userid, @Field("carid") String carid,
                                     @Field("comment") String comment,@Field("ratingsize") String ratingsize);

    @FormUrlEncoded
    @POST("/yorumfolder/listgame.php")
    Call<List<GameModel>> getGame(@Field("gameName") String gameName,@Field("gameType") String gameType);

    @FormUrlEncoded
    @POST("/yorumfolder/listgamecomment.php")
    Call<List<GameCommentModel>> getGameComment(@Field("id") String id);

    @FormUrlEncoded
    @POST("/yorumfolder/addgamecomment.php")
    Call<CommentGameAddModel> addGameComment(@Field("gameid") String gameid,
                                         @Field("comment") String comment, @Field("ratingsize") String ratingsize);
    @FormUrlEncoded
    @POST("/yorumfolder/listmakeup.php")
    Call<List<MakeupModel>> getMakeup(@Field("makyajName") String makyajName, @Field("makyajMalzeme") String makyajMalzeme);

    @FormUrlEncoded
    @POST("/yorumfolder/listmakeupcomment.php")
    Call<List<CommentMakeupModel>> getMakeupComment(@Field("id") String id);

    @FormUrlEncoded
    @POST("/yorumfolder/addmakeupcomment.php")
    Call<CommentMakeupAddModel> addMakeupModel(@Field("makeupid") String makeupid,
                                               @Field("comment") String comment, @Field("ratingsize") String ratingsize);

    @FormUrlEncoded
    @POST("/yorumfolder/listcomputer.php")
    Call<List<ComputerModel>> getComputer(@Field("hardwarename") String hardwarename, @Field("markaname") String markaname);

    @FormUrlEncoded
    @POST("/yorumfolder/listcomputercomment.php")
    Call<List<ComputerCommentModel>> getComputerComment(@Field("id") String id);

    @FormUrlEncoded
    @POST("/yorumfolder/addcomputercomment.php")
    Call<CommentComputerAddModel> addComputerModel(@Field("computerid") String computerid,
                                                 @Field("comment") String comment, @Field("ratingsize") String ratingsize);

    @GET("/yorumfolder/listcarhome.php")
    Call<List<CarModel>> getCarHome();

    @GET("/yorumfolder/listgamehome.php")
    Call<List<GameModel>> getGameHome();

    @GET("/yorumfolder/listmakeuphome.php")
    Call<List<MakeupModel>> getMakeupHome();

    @GET("/yorumfolder/listcomputerhome.php")
    Call<List<ComputerModel>> getComputerHome();
}
