package com.example.forev.yorumla.Models;

public class GameModel{
	private String gameType;
	private String resim;
	private String goster;
	private String gameName;
	private String rate;
	private String id;

	public void setGameType(String gameType){
		this.gameType = gameType;
	}

	public String getGameType(){
		return gameType;
	}

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	public void setGoster(String goster){
		this.goster = goster;
	}

	public String getGoster(){
		return goster;
	}

	public void setGameName(String gameName){
		this.gameName = gameName;
	}

	public String getGameName(){
		return gameName;
	}

	public void setRate(String rate){
		this.rate = rate;
	}

	public String getRate(){
		return rate;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"GameModel{" + 
			"gameType = '" + gameType + '\'' + 
			",resim = '" + resim + '\'' + 
			",goster = '" + goster + '\'' + 
			",gameName = '" + gameName + '\'' + 
			",rate = '" + rate + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
