# java-movie-recommender
# Movie Recommender (Java) – MovieLens Small

## Overview
A simple movie recommendation program written in Java.  
It reads the MovieLens “small” dataset (`ratings.csv`, `movies.csv`) and recommends movies a target user has not rated yet.

This version is intentionally student-friendly (basic Java + ArrayList, no external libraries).

## How it works
1. Loads all ratings from `ratings.csv`
2. Loads movie titles from `movies.csv`
3. For each movie the target user has not rated:
   - Computes the movie’s average rating across all users
   - Ignores movies with very small sample size (threshold: > 20 ratings)
4. Sorts movies by average rating (descending) and prints Top-N recommendations

## Dataset
- MovieLens “small” dataset (GroupLens)
- Files used:
  - `ratings.csv`
  - `movies.csv`

## Build & Run
Place `ratings.csv` and `movies.csv` in the same folder as the program, then:

```bash
javac MovieRecommender.java
java MovieRecommender
