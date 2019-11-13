

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;

/* Bogdan Vasilchenko*/

public class Cloud {
	private ArrayList<Point> points;	
    private boolean debug = false;
	

	/**
	 * Given Constructor
	 */
	public Cloud(){
		points = new ArrayList<Point>();
	}

	public void setDebug(Boolean debug){
		this.debug = debug;	
	}
	

	//TODO Implement Cloud.isEmpty
	/**
	 * @return whether cloud is empty or not
	 */
	public boolean isEmpty(){
		return points.isEmpty();
	}


	//TODO Implement Cloud.size
	/**
	 * @return number of points in the cloud
	 */
	public int size(){
		return points.size();
	}

	
	//TODO Implement Cloud.hasPoint
	/**
	 * 
	 * @param p a Point
	 * @return whether p in the cloud
	 */
	public boolean hasPoint(Point p){
		for (Point point : points) {

			if (p.equals(point)) {

				return true;
			}
		}
		return false;

	}

	//TODO Implement Cloud.addPoint
	/**
	 * 
	 * @param p
	 * if p not in points, add p ***at the end*** of points (to keep same order)

	 */
	public void addPoint(Point p){
		if (!hasPoint(p)){
			points.add(p);

		}
	}

	//Given Cloud.toString
	public String toString(){
		return points.toString();
	}

	//TODO Implement Cloud.extremes
	/**
	 * 
	 * @return an array of doubles: left, right, top, and bottom of points, 
	 *         null for empty cloud
	 */
	public double[] extremes(){
		if (this.isEmpty()) {
			return null;

		}
		double leftmost = points.get(0).getX(); // smallest x coordinate
		double rightmost = points.get(0).getX(); // highest x coordinate
		double top = points.get(0).getY(); // highest y coordinate;
		double bot = points.get(0).getY(); // lowest t coordinate.

		for (Point point : points) {
			if (point.getX() < leftmost) {
				leftmost = point.getX();
			}
			if (point.getX() > rightmost) {
				rightmost = point.getX();
			}
			if (point.getY() < bot) {
				bot = point.getY();
			}
			if (point.getY() > top) {
				top = point.getY();
			}
		}

		return new double[]{leftmost, rightmost, top, bot};
	}
	
	//TODO Implement Cloud.centerP
	/**
	 * 
	 * @return: if (cloud not empty) create and return the center point
	 *          else null;
	 */
	public Point centerP(){
		if (this.isEmpty()){
			return null;
		}
		double sumOfY = 0;
		double sumOfX = 0;

		for (Point point : points) {
			sumOfX += point.getX();
			sumOfY += point.getY();

		}

		double pointX = sumOfX/points.size();
		double pointY = sumOfY/points.size();
		return new Point(pointX, pointY);
	}


	//TODO Implement Cloud.minDist
	/**
	 * 
	 * @return minimal distance between 2 points in the cloud
	 *         0.0 for a cloud with less than 2 points
	 */
	public double minDist(){
		if (this.isEmpty() || points.size() == 1){
			return 0.0;

		}
		double minDist = Double.MAX_VALUE;

		for (Point point : points){
			for (Point p: points) {
				if (!(point.equals(p)) && (point.euclidDist(p) < minDist) ){
					minDist = point.euclidDist(p);
				}

			}


		}
		return minDist;
	}

	//TODO Implement Cloud.crop
	/**
	 * 
	 * @param p1 
	 * @param p2
	 * 
	 * all Points outside the rectangle, line or point spanned
	 * by p1 and p2 are removed
	 *  
	 */
	public void crop(Point p1, Point p2){
		//any point whose x value is smaller than min
		//any point whose y value is smaller or bigger than min
		//get cut off
		double minimaX = 0;
		double minimaY = 0;
		double maximaX = 0;
		double maximaY = 0;
		if (p1.getX() <= p2.getX()){
			minimaX = p1.getX();
			maximaX = p2.getX();
		}
		if (p1.getX() > p2.getX()){
			minimaX = p2.getX();
			maximaX = p1.getX();
		}

		if (p1.getY() <= p2.getY()){
			minimaY = p1.getY();
			maximaY = p2.getY();
		}
		if (p1.getY() > p2.getY()){
			minimaY = p2.getY();
			maximaY = p1.getY();
		}

		for (Point point : points){
			if ((point.getX() < minimaX) || (point.getX() > maximaX) || (point.getY() < minimaY) || (point.getY() > maximaY)){
				points.remove(point);
			}

		}
 	}
 
	

	/**
	 * @param args:no args
	 */
	public static void main(String[] args) {

		Cloud cloud = new Cloud();
		
		cloud.setDebug(false);
		System.out.println("cloud.debug OFF");
		System.out.println("cloud: " + cloud);
		
		if(!cloud.isEmpty())
			System.out.println("Error: cloud should be empty!");
		
		if(cloud.extremes()!=null)
			System.out.println("Error: extremes should be null!");
		
		if(cloud.minDist()!= 0.0)
			System.out.println("Error: minDist should return 0.0!");

			
		Point p31 = new Point(3.0,1.0);
		cloud.addPoint(p31);
		
		Point p22 = new Point(2.0,2.0);
		cloud.addPoint(p22);
			
		Point p42 = new Point(4.0,2.0);
		cloud.addPoint(p42);
		
		Point p33 = new Point(3.0,3.0);
		cloud.addPoint(p33);

		System.out.println("cloud 1: " + cloud);
		
		System.out.println("center point in cloud: " + cloud.centerP());

		System.out.println("cloud: " + cloud);
		System.out.println("cloud 2: " + cloud);
		

		Point p77 = new Point(7,7);
		if (cloud.hasPoint(p77))
			System.out.println("Error: point " + p77 + " should not be in cloud!");
		else
			System.out.println("OK: point " + p77 + " is not in cloud");

		double[] extrs = cloud.extremes();
		if(extrs!=null){
			System.out.println("left: " + extrs[0]);
		    System.out.println("right: " + extrs[1]);
		    System.out.println("top: " + extrs[2]);
		    System.out.println("bottom: " + extrs[3]);
		}
		double minD = cloud.minDist();			
		System.out.printf("min dist in cloud: %5.3f \n", minD);				
		
		System.out.println("Test cloud with 1 point");
		Cloud cloud1 = new Cloud();
		Point p = new Point();
		cloud1.addPoint(p);
	    minD = cloud1.minDist();
		System.out.printf("min dist in cloud1: %5.3f \n",  minD);

	}

}
