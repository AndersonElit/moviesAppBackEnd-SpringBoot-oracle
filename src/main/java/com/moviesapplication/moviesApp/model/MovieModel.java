package com.moviesapplication.moviesApp.model;

public class MovieModel implements MovieInt {
	
	private int id;
	private String name;
	private String description;
	private String actor;
	private float income;
	
	public void MovieModel() {
		
	}
	
	public void MovieModel(int id, String name, String description, String actor, float income) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.actor = actor;
		this.income = income;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getActor() {
		return actor;
	}
	
	public void setActor(String actor) {
		this.actor = actor;
	}
	
	public float getIncome() {
		return income;
	}
	
	public void setIncome(float income) {
		this.income = income;
	}
	
}
