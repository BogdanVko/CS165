import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by garethhalladay on 8/24/17
 */
public class MovieLibrary {

	
	
	
    private List<Movie> movies;

    /**
     * A constructor that allocates memory for the instance/member variable
     */
    public MovieLibrary(){
        movies = new ArrayList<>();
    }

    /**
     * Adds a movie to the instance/member variable
     * @param movie the Movie to add to the MovieLibrary object
     */
    public void addMovie(Movie movie){
        movies.add(movie);
    }

    /**
     * Getter method for the corresponding member variable
     * @return a list of movies
     */
    public List<Movie> getMovies(){
        return movies;
    }

    /**
     * A method that creates a new list of movies in the range of the lowestRating and highestRating (inclusive).
     * Filter the movies by {@link Rating.ReviewType} then whether or not the movie is within the range of lowestRating
     * and highestRating, inclusive. {@link Rating.ReviewType} can either be CRITIC, AUDIENCE, or BOTH.
     * @param lowestRating the lowest rating a movie can be
     * @param highestRating the highest rating a movie can be
     * @param reviewer can be CRITIC, AUDIENCE, or BOTH
     * @return a list of movies that are filtered by rating
     */
    public List<Movie> filterMoviesByRating(int lowestRating, int highestRating, Rating.ReviewType reviewer){
        List<Movie> results = new ArrayList<>();
        for (Movie movie : movies){
            double rating = 0;
            switch(reviewer){
                case CRITIC:
                    rating = movie.getRating().getCriticScore();
                    break;
                case AUDIENCE:
                    rating = movie.getRating().getAudienceScore();
                    break;
                case BOTH:
                    rating = movie.getRating().getAverageRating();
            }
            if (lowestRating <= rating && rating <= highestRating){
                results.add(movie);
            }
        }
        return results;
    }


    /**
     * Similar to {@link MovieLibrary#filterMoviesByRating(int, int, Rating.ReviewType)} but only
     * returns movies that have a rating greater than or equal to the lowestRating.
     * Consider calling {@link MovieLibrary#filterMoviesByRating(int, int, Rating.ReviewType)}
     * so that you don't repeat work.
     * @param lowestRating the lowest rating a movie can be
     * @param reviewer true if filtering by critic reviews, false if audience
     * @return A list of Movies that have filtered by rating */
    public List<Movie> filterMoviesByRating(int lowestRating, Rating.ReviewType reviewer){
        return filterMoviesByRating(lowestRating, 100, reviewer);
    }

    /**
     * A movie description is defined as:
     * <ul>
     *     <li> Title (one line)
     *     <li> genres (one line)
     *     <li> keywords (5 per line, one or more lines)
     * </ul>
     * Use the provided helper method <tt>formatKeyWords</tt> to format the keywords. <br>
     *
     * Example: <br>
     * <p>
     * Late Night Shopping <br>
     * Comedy <br>
     * less than 300 ratings <br>
     * <p>
     * Futurama: The Beast with a Billion Backs <br>
     * Animation Comedy Sci-Fi <br>
     * movie to see <br>
     * <p>
     * Dagon <br>
     * Fantasy Horror Mystery Thriller <br>
     * monsters, hp lovecraft, lovecraftian mythology <br>
     *
     * @param movies a list of movies
     * @return A String of movie descriptions
     */
    public static String getDescriptions(List<Movie> movies){

        String result = String.format("Total movies: %d\n\n", movies.size());

        for (Movie m : movies){
            result += String.format("%s\n%s\n%s\n", m.getTitle(),
                                    String.join(" ", m.getGenres()),
                                    formatKeyWords(m.getKeyWords()));
        }
        return result;
    }


    /**
     * Takes a list of movies and returns a String with each movies toString separated by a newline.
     * @param movies The movies to convert
     * @return A String of Movies
     */
    public static String formatMovies(List<Movie> movies){

        String result = String.format("Number of movies: %d\n\n", movies.size());
        for (Movie m : movies){
            result += String.format("%s\n", m);
        }
        return result;
    }

    /**
     * A Helper method to format KeyWords, 5 per line.
     * @param keywords a list of keywords describing a movie
     * @return A formatted String with 5 keywords per line.
     */
    public static String formatKeyWords(List<KeyWord> keywords){
        if(Objects.isNull(keywords))
            return "";
        String result = keywords.get(0).toString() + ((keywords.size() == 1) ?  "\n" : ", ");

        int count = 1;
        for(KeyWord k : keywords.subList(1,keywords.size())){
            if (count % 5 == 0 || count == keywords.size()-1){
                result += k.toString() + "\n";
            }
            else{
                result += k.toString() + ", ";
            }
            count++;
        }
        return result;
    }

    public static void main(String [] args) throws FileNotFoundException {
        
        FileParser fp = new FileParser();
        MovieLibrary library = fp.getAllMovies("resources/movie_titles.dat", "resources/movie_years.dat",
                                              "resources/movie_actors.dat", "resources/movie_genres.dat",
                                              "resources/keywords.dat","resources/movie_keywords.dat",
                                              "resources/movie_ratings.dat");
        

        System.out.println("The MovieLibrary class:");
        // 1. How many movies are there in total?
        System.out.println(library.filterMoviesByRating(0, 100,  Rating.ReviewType.BOTH).size());
        // 2. How many movies did the audience rate 95% or above?
        System.out.println(library.filterMoviesByRating(95,  Rating.ReviewType.AUDIENCE).size());
        // 3. How many movies did the critics rate 0% to 5%?
        System.out.println(library.filterMoviesByRating(0, 5, Rating.ReviewType.CRITIC).size());


        // 4. Some movies don't have KeyWords. Explain how you could change
        //   the getDescription method to only print movies that have keywords.
        // Iterate over each movie
       
        // 5. How would you sort the KeyWords by frequency? What class/classes would you modify?
        // How would you deal with keywords that have the same frequency?
        



    }
}
