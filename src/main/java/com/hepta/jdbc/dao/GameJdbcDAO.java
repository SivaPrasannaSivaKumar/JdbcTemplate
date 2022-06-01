package com.hepta.jdbc.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.hepta.jdbc.model.Game;

@Component
public class GameJdbcDAO implements DAO<Game> {

	private static final Logger log = LoggerFactory.getLogger(GameJdbcDAO.class); 
	
	//JDBC Template
	private JdbcTemplate jdbcTemplate;
	
	public GameJdbcDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//Maps a row in the database  to a Game
	RowMapper<Game> rowMapper = (rs,rowNum) -> {
		Game game =  new Game();
		game.setGameId(rs.getInt("game_id"));
		game.setTitle(rs.getString("title"));
		game.setDescription(rs.getString("description"));
		return game;
	};

	@Override
	public List<Game> list() {
		// TODO Auto-generated method stub
		String sql = "SELECT game_id, title, description from game";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	//CRUD Method
	@Override
	public void create(Game game) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO game(game_id,title,description) values (?,?,?)";
		int insert = jdbcTemplate.update(sql, game.getGameId(), game.getTitle(), game.getDescription());
		if(insert == 1) {
			log.info("New Game added " + game.getTitle() + ".");
		}
	}

	@Override
	public Optional<Game> get(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT game_id, title, description from game where game_id = ?";
		Game game = null;
		try {
			game = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
		} catch (DataAccessException ex) {
			log.info("Game not found " + id + ".");
		}
		return Optional.ofNullable(game);
	}

	@Override
	public void update(Game game, int id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE game set title = ?, description = ? where game_id = ?";
		int update = jdbcTemplate.update(sql, game.getTitle(), game.getDescription(),id);
		if(update == 1) {
			log.info("Game "+ game.getTitle()+" got updated.");
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub\
		String sql = "DELETE from game where game_id = ?";
		int delete = jdbcTemplate.update(sql, id);
		if(delete == 1) {
			log.info("Game deleted " + id + ".");
		}
	}

}
