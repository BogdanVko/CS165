

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

public class P10 implements Interface {

    public P10(){

    }


    @Override
    public Temperature[] readTemperatures(String filename) {
        Temperature[] temps = null;
        File file = new File(filename);
        Scanner scan = null;


        try {
            scan = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scan != null;
        String title = scan.nextLine();
        title = title.substring(1);
        temps = new Temperature[Integer.parseInt(title)];


        for (int i = 0; i < temps.length; i++){
            //(String dayMonthYear, String hour, double degrees, double speed)
            String line = scan.nextLine();
            String arr[] = line.split(" ", 4);
            Temperature temp = new Temperature(arr[0], arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3]));
            temps[i] = temp;
        }

        //System.out.println("profit");
        return temps;
    }

    @Override
    public double findMinimum(Date start, Date end, Temperature[] data) {
        //find min array element
        //find end array element


        //iterate and find min from min to max
        double min = Double.MAX_VALUE;
        for (int i = 0; i < data.length; i++){
            if (data[i].inInterval(start, end) && data[i].temperature < min){
                min = data[i].temperature;
            }
        }
        return min;

    }

    @Override
    public double findMaximum(Date start, Date end, Temperature[] data) {

        //find min array element
        //find end array element


        //iterate and find min from min to max
        double max = Double.MIN_VALUE;
        for (int i = 0; i < data.length; i++){
            if (data[i].temperature > max && data[i].inInterval(start,end)){
                max = data[i].temperature;
            }
        }
        return max;


    }

    @Override
    public double findAverage(Date start, Date end, Temperature[] data) {
        int count = 0;

        double sum = 0;

        for (int i = 0; i < data.length; i++){
            if (data[i].inInterval(start, end)){
                sum += data[i].temperature;
                count++;
            }
        }
        double result = sum/count;

        return result;

    }

    @Override
    public double findHighest(Date start, Date end, Temperature[] data) {
        //find min array element
        //find end array element


        //iterate and find min from min to max
        double max = Double.MIN_VALUE;
        for (int i = 0; i < data.length; i++){
            if (data[i].windspeed > max && data[i].inInterval(start,end)){
                max = data[i].windspeed;
            }
        }
        return max;
    }

    public static void main(String args[]){
        // Instantiate student code
        P10 p10 = new P10();

        // Test readTemperatures
        Temperature[] data = p10.readTemperatures(args[0]);

        // Test findMinimum
        Date start = Temperature.createDate("04-Jul-2008", "06:00");
        Date end = Temperature.createDate("17-Aug-2010", "23:00");
        System.out.println("Verifying findMinimum method:");
        System.out.println("Start date: " + start.toString());
        System.out.println("End date: " + end.toString());
        System.out.printf("Minimum = %.1f degrees\n", p10.findMinimum(start, end, data));

        // Test findMaximum
        start = Temperature.createDate("19-Sep-2011", "07:00");
        end = Temperature.createDate("23-Mar-2015", "13:00");
        System.out.println("Verifying findMaximum method:");
        System.out.println("Start date: " + start.toString());
        System.out.println("End date: " + end.toString());
        System.out.printf("Maximum = %.1f degrees\n", p10.findMaximum(start, end, data));

        // Test findAverage
        start = Temperature.createDate("09-Apr-2006", "19:00");
        end = Temperature.createDate("31-Oct-2013", "10:00");
        System.out.println("Verifying findAverage method:");
        System.out.println("Start date: " + start.toString());
        System.out.println("End date: " + end.toString());
        System.out.printf("Average = %.1f degrees\n", p10.findAverage(start, end, data));

        // Test findHighest
        start = Temperature.createDate("01-Jan-2015", "00:00");
        end = Temperature.createDate("31-Dec-2015", "23:00");
        System.out.println("Verifying findHighest method:");
        System.out.println("Start date: " + start.toString());
        System.out.println("End date: " + end.toString());
        System.out.printf("Highest windspeed = %.1f\n", p10.findHighest(start, end, data));
    }
}
