package com.hepta.jdbc.model;

public class Game {
	private int gameId;
	private String title;
	private String description;
	public Game() {
	}
	public Game(String title, String description) {
		this.title = title;
		this.description = description;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", title=" + title + ", description=" + description + "]";
	}
}
