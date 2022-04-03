package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/"
			+ "sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private String user = "student";
	private String password = "student";

	
	@Override
	public Film findFilmById(int filmId) {

		Film film = null;
		

		try {
			Connection conn = DriverManager.getConnection(URL, user, password); // connects to database

			String sql = "SELECT * FROM film WHERE id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);

			ResultSet rs = ps.executeQuery();

			{
				if (rs.next()) {
					int id = rs.getInt(1);
					String title = rs.getString(2);
					String desc = rs.getString(3);
					int year = rs.getInt(4);
					int lang = rs.getInt(5);
					int rentalDuration = rs.getInt(6);
					double rentalRate = rs.getDouble(7);
					int length = rs.getInt(8);
					double replacementCost = rs.getDouble(9);
					String rating = rs.getString(10);
					String specialFeatures = rs.getString(11);
					String language = language(rs.getInt(5));
					
					
					List<Actor> actors = findActorsByFilmId(filmId);

					film = new Film(id, title, desc, year, lang, rentalDuration, rentalRate, length, replacementCost,
							rating, specialFeatures, actors, language);

					
				}
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		return film;

	}

	public Actor findActorById(int actorId) {

		Actor actor = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, password); // connects to database

			String sql = "SELECT * FROM actor WHERE id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, actorId);

			ResultSet rs = ps.executeQuery();

			{
				while (rs.next()) {
					int id = rs.getInt(1);
					String firstName = rs.getString(2);
					String lastName = rs.getString(3);
					actor = new Actor(id, firstName, lastName);

				}
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}

		return actor;

	}

	public List<Actor> findActorsByFilmId(int filmId) {

		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, password); // connects to database

			String sql = "SELECT a.id, a.first_name, a.last_name, film.id" + " FROM film_actor f"
					+ " JOIN actor a ON f.actor_id = a.id" + " JOIN film ON f.film_id = film.id" + " WHERE film.id = ?";

			// SELECT id, first_name, last_name FROM actor JOIN film_actor
			// ON actor.id = film_actor.actor_id WHERE film_actor.film_id=?;

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, filmId);

			ResultSet rs = ps.executeQuery();

			{
				while (rs.next()) {

					Actor actor = new Actor();
					int id = rs.getInt(1);
					String firstName = rs.getString(2);
					String lastName = rs.getString(3);
					actor = new Actor(id, firstName, lastName);
					actors.add(actor);

				}
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}

		return actors;

	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // loads in driver
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Driver Not Found");
			throw new RuntimeException("Unable to load MySql driver class");
		}
	}

	@Override
	public List<Film> findFilmByKeyword(String keyWord) {

		List<Film> films = new ArrayList<>();

		Film film;
		try {
			Connection conn = DriverManager.getConnection(URL, user, password);

			String sql = "SELECT* from film WHERE title LIKE ? OR description LIKE ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyWord + "%");
			ps.setString(2, "%" + keyWord + "%");

			ResultSet rs = ps.executeQuery();

			{
				while (rs.next()) {
					int id = rs.getInt(1);
					String title = rs.getString(2);
					String desc = rs.getString(3);
					int year = rs.getInt(4);
					int lang = rs.getInt(5);
					int rentalDuration = rs.getInt(6);
					double rentalRate = rs.getDouble(7);
					int length = rs.getInt(8);
					double replacementCost = rs.getDouble(9);
					String rating = rs.getString(10);
					String specialFeatures = rs.getString(11);
					String language = language(rs.getInt(5));

					List<Actor> actors = findActorsByFilmId(id);

					film = new Film(id, title, desc, year, lang, rentalDuration, rentalRate, length, replacementCost,
							rating, specialFeatures, actors, language);

					films.add(film);
					
				}
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}

		return films;
	}
	public String language(int langId) {
		
	String language = "";

		
		
		try {
			Connection conn = DriverManager.getConnection(URL, user, password); // connects to database

			String sql = "SELECT name FROM language WHERE id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, langId);

			ResultSet rs = ps.executeQuery();

			{
				if (rs.next()) {
					
				}
				language = (rs.getString(1));
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}

		return language;
	}

}
