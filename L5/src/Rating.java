/**
 * Created by garethhalladay on 8/27/17
 */
public class Rating {
	private double criticScore;
	private int numCritics;
	private double audienceScore;
	private int numAudience;
    /**
     * ReviewType enum that allows users to distinguish between CRITIC, AUDIENCE, or an average of both
     * 

Declare the following fields:

    A private double to store the average critic score

    A private int to store the number of critics in the critic score

    A private double to store the average audience score

    A private int to store the number of people included in the audience s
     */
	public Rating(){
		this.criticScore = 0 ;
		this.numCritics = 0;
		this.audienceScore = 0;
		this.numAudience = 0;
		
	}
	public Rating(double criticScore, int numCritics, double audienceScore, int numAudience) {
		this.criticScore = criticScore ;
		this.numCritics = numCritics;
		this.audienceScore = audienceScore;
		this.numAudience = numAudience;
		
		
	}
    public enum ReviewType{CRITIC, AUDIENCE, BOTH}

    public static void main(String [] args){
        Rating noargs = new Rating();
        Rating rating1 = new Rating(30, 10, 45, 100);
        System.out.printf("Critic Score: %.0f\nNumber of Critics: %d\nAudience Score: %.0f\nNumber of people: %d\n",
                           rating1.getCriticScore(), rating1.getNumCritics(), rating1.getAudienceScore(), rating1.getNumCritics());
        System.out.println(noargs);
        System.out.println(rating1);

    }
	public double getCriticScore() {
		// TODO Auto-generated method stub
		return this.criticScore;
	}
	public int getNumCritics() {
		// TODO Auto-generated method stub
		return this.numCritics;
	}
	public double getAudienceScore() {
		// TODO Auto-generated method stub
		return this.audienceScore;
	
	}
	public int getNumAudience() {
		// TODO Auto-generated method stub
		return this.numAudience;
	
	}
	@Override
	public String toString() {
		//Critics Score: #, Critics Count: #, Audience Score: #, Audience Count: # 
		return "Critics Score: " + this.criticScore + " Critics Count: " + this.numCritics + " Audience Score: " + this. audienceScore+ 
				" Audience Count: " + this.numAudience;
	}
	public double getAverageRating() {
		return (this.criticScore + this.audienceScore )/ 2;
	}
	
	
}
