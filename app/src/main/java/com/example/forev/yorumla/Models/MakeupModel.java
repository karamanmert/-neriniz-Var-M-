package com.example.forev.yorumla.Models;

public class MakeupModel{
	private String makyajMalzeme;
	private String resim;
	private String goster;
	private String makyajName;
	private String rate;
	private String id;

	public void setMakyajMalzeme(String makyajMalzeme){
		this.makyajMalzeme = makyajMalzeme;
	}

	public String getMakyajMalzeme(){
		return makyajMalzeme;
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

	public void setMakyajName(String makyajName){
		this.makyajName = makyajName;
	}

	public String getMakyajName(){
		return makyajName;
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
			"MakeupModel{" + 
			"makyajMalzeme = '" + makyajMalzeme + '\'' + 
			",resim = '" + resim + '\'' + 
			",goster = '" + goster + '\'' + 
			",makyajName = '" + makyajName + '\'' + 
			",rate = '" + rate + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
