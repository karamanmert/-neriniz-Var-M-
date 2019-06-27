package com.example.forev.yorumla.Models;

public class ComputerModel{
	private String resim;
	private String markaname;
	private String goster;
	private String hardwarename;
	private String rate;
	private String id;

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	public void setMarkaname(String markaname){
		this.markaname = markaname;
	}

	public String getMarkaname(){
		return markaname;
	}

	public void setGoster(String goster){
		this.goster = goster;
	}

	public String getGoster(){
		return goster;
	}

	public void setHardwarename(String hardwarename){
		this.hardwarename = hardwarename;
	}

	public String getHardwarename(){
		return hardwarename;
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
			"ComputerModel{" + 
			"resim = '" + resim + '\'' + 
			",markaname = '" + markaname + '\'' + 
			",goster = '" + goster + '\'' + 
			",hardwarename = '" + hardwarename + '\'' + 
			",rate = '" + rate + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
