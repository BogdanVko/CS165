import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Created by garethhalladay on 8/26/17
 */
public class FileParser {

    private HashMap<Integer, String> movies = new HashMap<>();
    private HashMap<Integer, String> tags = new HashMap<>();

    private HashMap<Integer, Rating> movieRatings = new HashMap<>();
    private HashMap<Integer, List<KeyWord>> movieTags = new HashMap<>();
    private HashMap<Integer, List<String>> actors = new HashMap<>();
    private HashMap<Integer, List<String>> genres = new HashMap<>();
    private HashMap<Integer, Integer> years = new HashMap<>();

    private void indexMovieTitles(String filename) throws FileNotFoundException {
        createIndex(filename, movies);
    }

    private void indexKeywords(String filename) throws FileNotFoundException {
        createIndex(filename, tags);
    }

    private void createIndex(String filename, HashMap<Integer, String> index) throws FileNotFoundException {
        File file = new File(filename);
        try(Scanner scan = new Scanner(file)){
            scan.nextLine(); // throwing away header
            while (scan.hasNext()) {
                String line = scan.nextLine();

                String[] data = line.split("\t");
                index.put(parseInt(data[0]), data[1]);
            }
        }
    }

    private void storeTags(String filename) throws FileNotFoundException {
        try(Scanner scan = new Scanner(new File(filename))){
            scan.nextLine(); // throwing away header
            while (scan.hasNext()) {
                int index = scan.nextInt();
                String tag = tags.get(scan.nextInt());
                int frequency = scan.nextInt();
                if (!movieTags.containsKey(index)){
                    List<KeyWord> newKeyWord = new ArrayList<>();
                    newKeyWord.add(new KeyWord(tag, frequency));
                    movieTags.put(index, newKeyWord);
                }
                else {
                    movieTags.get(index).add(new KeyWord(tag, frequency));
                }
            }
        }
    }

    private void storeRatings(String filename) throws FileNotFoundException {
        try(Scanner scan = new Scanner(new File(filename))){
            scan.nextLine(); // throwing away header
            while (scan.hasNext()) {
                String[] data = scan.nextLine().split("\t");
                if(data[1].equals("\\N")){
                    movieRatings.put(parseInt(data[0]), new Rating());
                }
                else {
                    // Rating(double criticScore, int numCritics, double audienceScore, int numAudience)
                    // *id	rtAllCriticsRating	*rtAllCriticsNumReviews	rtAllCriticsNumFresh	*rtAllCriticsScore	rtAudienceRating	*rtAudienceNumRatings	*rtAudienceScore
                    movieRatings.put(parseInt(data[0]), new Rating(parseInt(data[4]), parseInt(data[2]),
                                                                   parseInt(data[7]), parseInt(data[6])));
                }
            }
        }
    }

    private void storeYears(String filename) throws FileNotFoundException {
        try(Scanner scan = new Scanner(new File(filename))){
            scan.nextLine(); // throwing away header
            while (scan.hasNext()) {
                years.put(scan.nextInt(), scan.nextInt());

            }
        }
    }

    private void storeInformation(String filename, HashMap<Integer, List<String>> list) throws FileNotFoundException {
        try(Scanner scan = new Scanner(new File(filename))){
            scan.nextLine(); // throwing away header
            while (scan.hasNext()) {
                String[] data = scan.nextLine().split("\t");
                int index = parseInt(data[0]);
                if (!list.containsKey(index)) {
                    List<String> t = new ArrayList<>();
                    t.add(data[1]);
                    list.put(index, t);
                } else {
                    list.get(index).add(data[1]);
                }
            }
        }
    }

    private void storeActors(String filename) throws FileNotFoundException {
        storeInformation(filename, this.actors);
    }

    private void storeGenres(String filename) throws FileNotFoundException {
        storeInformation(filename, this.genres);
    }



    public MovieLibrary getAllMovies(String movieIndexFile, String yearFile, String actorsFile, String genreFile,
                                     String keywordIndexFile, String tagsFile, String ratingsFile) throws FileNotFoundException {
        MovieLibrary list = new MovieLibrary();
        indexMovieTitles(movieIndexFile); // index the movie names
        indexKeywords(keywordIndexFile); // index the tags
        storeTags(tagsFile);
        storeRatings(ratingsFile);
        storeGenres(genreFile);
        storeYears(yearFile);
        storeActors(actorsFile);
        for(int index : movies.keySet()){
            list.addMovie(new Movie(movies.get(index), years.get(index),
                                    dealWithMissingData(actors.get(index)),
                                    dealWithMissingData(genres.get(index)),
                                    movieRatings.get(index), dealWithMissingTags(movieTags.get(index))));
        }
        return list;
    }

    private List<String> dealWithMissingData(List<String> list){
        return (Objects.isNull(list)) ? new ArrayList<>() : list;
    }

    private List<KeyWord> dealWithMissingTags(List<KeyWord> tags) {
        return (Objects.isNull(tags)) ? new ArrayList<>() : tags;
    }

}
