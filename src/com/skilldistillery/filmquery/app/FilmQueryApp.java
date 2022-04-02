package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(34);
//		Actor actor = db.findActorById(1);

		System.out.println(film);
//		System.out.println(actor);

	}

	private void launch() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to our film data base.");
		System.out.println("");
		System.out.println("Please choose from the following options \n1. Search film by ID"
				+ " \n2. Search film by keyword " + "\n3. Quit");

		int input = sc.nextInt();

//		".... WHERE x LIKE ? OR y LIKE ? ....."
//		stmt.setString(1, "%" + keyword + "%");
//		stmt.setString(2, "%" + keyword + "%");
//sql statement SELECT title, description FROM film WHERE description like %MON% OR title like %MON%;


		startUserInterface(input);
	}

	private void startUserInterface(int input) {
		Scanner kb = new Scanner(System.in);
		switch (input) {

		case 1:
			System.out.println("You have chosen to look up a film by ID\nPlease input a film ID");
			int filmId = kb.nextInt();
			Film film = db.findFilmById(filmId);
			System.out.println(film);
			
			break;
		case 2:
			System.out.println("You have chosen to look up a film by keyword ");
			break;
		case 3:
			System.out.println("Goodbye.");
			break;

		}

	}
}
