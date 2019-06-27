package com.example.forev.yorumla.Models;

public class CarModel{
	private String motor;
	private String resim;
	private String goster;
	private String rate;
	private String marka;
	private String model;
	private String id;

	public void setMotor(String motor){
		this.motor = motor;
	}

	public String getMotor(){
		return motor;
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

	public void setRate(String rate){
		this.rate = rate;
	}

	public String getRate(){
		return rate;
	}

	public void setMarka(String marka){
		this.marka = marka;
	}

	public String getMarka(){
		return marka;
	}

	public void setModel(String model){
		this.model = model;
	}

	public String getModel(){
		return model;
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
			"CarModel{" + 
			"motor = '" + motor + '\'' + 
			",resim = '" + resim + '\'' + 
			",goster = '" + goster + '\'' + 
			",rate = '" + rate + '\'' + 
			",marka = '" + marka + '\'' + 
			",model = '" + model + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
