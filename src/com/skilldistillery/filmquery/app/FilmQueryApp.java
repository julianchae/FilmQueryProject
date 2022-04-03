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
		Actor actor = db.findActorById(1);

		System.out.println(film);
		System.out.println(actor);

	}

	private void launch() {


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
				
				if (film == null) {
					
					System.out.println("Film not found, try again.");
					
				}
				
				else {
					
					System.out.println(film);
				}

			}

			if (input == 2) {
				System.out.println("You have chosen to look up films by keyword\nPlease input a keyword: ");
				
				String keyword = kb.next();
				
				List<Film> filmList = db.findFilmByKeyword(keyword);
				
				if(filmList.size()
						<1) {
					
					System.out.println("Film not found, try again.");
					
				}else {
					
					System.out.println(filmList);
				}

			}

			if (input == 3) {
				
				System.out.println("Goodbye.");
				menu = false;
				sc.close();
				kb.close();

			}

		}
	}
}
