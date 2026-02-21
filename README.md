# Movie Recommender (Java) — MovieLens Small

A student-friendly movie recommendation project in **Java** using the **MovieLens (small)** dataset.  
The program reads `ratings.csv` and `movies.csv`, then recommends movies a target user has not rated yet by ranking movies with a **baseline score** (average rating with a minimum-rating-count filter).

## Features
- Parses real-world CSV data (`ratings.csv`, `movies.csv`)
- Filters out movies with too few ratings (to reduce noise)
- Produces Top-N recommendations with movie titles
- Uses only basic Java + `ArrayList` (no external libraries)

## Dataset (MovieLens)
Download **MovieLens Latest Small** from GroupLens:
- Dataset: MovieLens Small
- Files used: `ratings.csv`, `movies.csv`

> Note: The dataset is not included in this repository. Please download it separately.

## How to Run

### Option A — NetBeans (recommended)
1. Download MovieLens Small and extract it.
2. Copy these files into the project root (same folder as `pom.xml`):
   - `ratings.csv`
   - `movies.csv`
3. Run the project.

### Option B — Terminal (Maven)
From the project folder:
```bash
mvn -q exec:java -Dexec.mainClass="com.mycompany.movierecommender.MovieRecommender"
