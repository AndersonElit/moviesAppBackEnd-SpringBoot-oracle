package com.moviesapplication.moviesApp.model;

public interface MovieInt {
	
	public void MovieModel();
	
	public void MovieModel(int id, String name, String description, String actor, float income);
	
	public int getId();
	
	public void setId(int id);
	
	public String getName();
	
	public void setName(String name);
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public String getActor();
	
	public void setActor(String actor);
	
	public float getIncome();
	
	public void setIncome(float income);
	
}
