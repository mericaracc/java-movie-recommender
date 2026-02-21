package com.mycompany.movierecommender;

import java.io.*;
import java.util.*;

class Rating {
    int userID;
    int movieID;
    double rating;

    Rating(int u, int m, double r) {
        userID = u;
        movieID = m;
        rating = r;
    }
}

class Movie {
    int movieID;
    String title;

    Movie(int id, String t) {
        movieID = id;
        title = t;
    }
}

class MovieScore {
    int movieID;
    double score;

    MovieScore(int m, double s) {
        movieID = m;
        score = s;
    }
}

public class MovieRecommender {

    static ArrayList<Rating> ratings = new ArrayList<>();
    static ArrayList<Movie> movies = new ArrayList<>();

    public static void main(String[] args) {

        readRatings("ratings.csv");
        readMovies("movies.csv");

        int targetUser = 50;
        System.out.println("Recommendations for user " + targetUser);

        ArrayList<MovieScore> recs = recommendMovies(targetUser);

        System.out.println("Found " + recs.size() + " recommendations\n");

        int limit = Math.min(10, recs.size());
        for (int i = 0; i < limit; i++) {
            MovieScore ms = recs.get(i);
            System.out.println(
                (i + 1) + ") " + getMovieTitle(ms.movieID) +
                " | average rating = " + String.format("%.3f", ms.score)
            );
        }
    }

    static void readRatings(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");

                int user = Integer.parseInt(p[0]);
                int movie = Integer.parseInt(p[1]);
                double rate = Double.parseDouble(p[2]);

                ratings.add(new Rating(user, movie, rate));
            }

            br.close();
            System.out.println("Ratings loaded: " + ratings.size());

        } catch (Exception e) {
            System.out.println("Error reading ratings.csv");
            e.printStackTrace();
        }
    }

    static void readMovies(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {

                int firstComma = line.indexOf(',');
                int lastComma = line.lastIndexOf(',');

                int movieId = Integer.parseInt(line.substring(0, firstComma));
                String title = line.substring(firstComma + 1, lastComma);

                if (title.startsWith("\"") && title.endsWith("\""))
                    title = title.substring(1, title.length() - 1);

                movies.add(new Movie(movieId, title));
            }

            br.close();
            System.out.println("Movies loaded: " + movies.size());

        } catch (Exception e) {
            System.out.println("Error reading movies.csv");
            e.printStackTrace();
        }
    }

    static String getMovieTitle(int movieID) {
        for (Movie m : movies)
            if (m.movieID == movieID)
                return m.title;
        return "Movie " + movieID;
    }

    static boolean userRatedMovie(int userID, int movieID) {
        for (Rating r : ratings)
            if (r.userID == userID && r.movieID == movieID)
                return true;
        return false;
    }

    static ArrayList<Integer> getAllMovies() {
        ArrayList<Integer> list = new ArrayList<>();
        for (Rating r : ratings)
            if (!list.contains(r.movieID))
                list.add(r.movieID);
        return list;
    }

    static ArrayList<MovieScore> recommendMovies(int targetUser) {

        ArrayList<MovieScore> results = new ArrayList<>();
        ArrayList<Integer> allMovies = getAllMovies();

        for (int movie : allMovies) {

            if (userRatedMovie(targetUser, movie))
                continue;

            double sum = 0;
            int count = 0;

            for (Rating r : ratings) {
                if (r.movieID == movie) {
                    sum += r.rating;
                    count++;
                }
            }

            if (count > 20) { // ignore very low rated sample size
                double avg = sum / count;
                results.add(new MovieScore(movie, avg));
            }
        }

        results.sort(new Comparator<MovieScore>() {
            @Override
            public int compare(MovieScore a, MovieScore b) {
                return Double.compare(b.score, a.score);
            }
        });

        return results;
    }
}