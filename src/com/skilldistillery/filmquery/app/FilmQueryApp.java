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

//		".... WHERE x LIKE ? OR y LIKE ? ....."
//		stmt.setString(1, "%" + keyword + "%");
//		stmt.setString(2, "%" + keyword + "%");
//sql statement SELECT title, description FROM film WHERE description like %MON% OR title like %MON%;

		startUserInterface();
	}

	private void startUserInterface() {
		boolean menu = true;
		while (menu) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Welcome to our film data base.");
			System.out.println("");
			System.out.println("Please choose from the following options \n1. Search film by ID"
					+ " \n2. Search film by keyword " + "\n3. Quit");
			
			int input = sc.nextInt();

			Scanner kb = new Scanner(System.in);
			if (input == 1) {
				System.out.println("You have chosen to look up a film by ID\nPlease input a film ID: ");
				int filmId = kb.nextInt();
				Film film = db.findFilmById(filmId);
				System.out.println(film);
				if(film == null) {
					System.out.println("Film not found, try again");
				}
				
			}

			if (input == 2) {
				System.out.println("You have chosen to look up films by keyword\nPlease input a keyword: ");
				String keyword = kb.next();
				List<Film> filmList = db.findFilmByKeyword(keyword);
				System.out.println(filmList);

			}

			if (input == 3) {
				System.out.println("Goodbye.");
				menu =false;
				sc.close();
				
			}
		

		}
	}
}
