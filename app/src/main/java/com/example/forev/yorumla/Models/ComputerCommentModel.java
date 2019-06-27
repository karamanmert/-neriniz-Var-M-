package com.example.forev.yorumla.Models;

public class ComputerCommentModel{
	private String ratingsize;
	private String comment;

	public void setRatingsize(String ratingsize){
		this.ratingsize = ratingsize;
	}

	public String getRatingsize(){
		return ratingsize;
	}

	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}

	@Override
 	public String toString(){
		return 
			"ComputerCommentModel{" + 
			"ratingsize = '" + ratingsize + '\'' + 
			",comment = '" + comment + '\'' + 
			"}";
		}
}
