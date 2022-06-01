package com.hepta.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hepta.jdbc.dao.DAO;
import com.hepta.jdbc.model.Game;

@SpringBootApplication
public class JdbcApplication {
	
	private static DAO<Game> dao;
	
	public JdbcApplication(DAO<Game> dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
		// Add Game
		System.out.println("\n Add Game.....\n");
		Game ml = new Game("COC","Clan Games");
		dao.create(ml);
		
		// Get Game
		System.out.println("\nGet Game.....\n");
		Optional<Game> getGame = dao.get(1);
		System.out.println(getGame.get());
		
		// Update Game
		ml.setDescription("Clan Game");
		dao.update(ml, 0);
		
		// Delete Game
		dao.delete(3);
		
		// List All Game
		System.out.println("\n All Games.....\n");
		List<Game> games = dao.list();
		games.forEach(System.out::println);
	}
}
