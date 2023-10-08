//import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;

public class CTATracker {
    static final String RED_COLOR = "\u001B[31m";
    static final String YELLOW_COLOR = "\u001B[33m";
    static final String GREEN_COLOR = "\033[0;32m";
    static final String COLOR_RESET = "\u001B[0m";
    public static Element timeList;
    public static int dir;
    public static ArrayList<Integer> directions = new ArrayList<Integer>();
    public static ArrayList<Integer> arrivalTime = new ArrayList<Integer>();
    public static double[] timeToLeave = new double[2];
    public static ArrayList<String> arrivalLoc = new ArrayList<String>();
    public static ArrayList<String> arrivalLine = new ArrayList<String>();
    public static int dirTwo;
    
    public static void main(String[] args) throws Exception {

        Scanner scn = new Scanner(System.in);
        String url;
        //final String url = "https://www.transitchicago.com/station/sprt/";
        //final String url = "https://www.transitchicago.com/station/belm/";
        System.out.print("Enter URL of station:");
        url = scn.nextLine();

        
        try{
            //go the the URL and create a new document of that URL (I think???)
            Document document = Jsoup.connect(url).get();
            
            for(Element timeData: document.select("span.ea-line-time-big")){

                int bracketFirstPosition, bracketSecondPosition;
                String strData;
                String justNumber;
                int justInt;

                //convert the element to a string to later perform string manipulation
                strData = timeData.toString();

                //find the position of ">" as the next character is the data we want.
                bracketFirstPosition = strData.indexOf(">");

                //create a new substring that deletes all of the giberish BEFORE the position of >
                justNumber = strData.substring(bracketFirstPosition + 1);

                //using that new string, find the position of <
                bracketSecondPosition = justNumber.indexOf("<");

                //reassign justNumber to its final format, deleting all the data that comes AFTER <
                justNumber = justNumber.substring(0, bracketSecondPosition);

                if (justNumber.equals(" Due ") || justNumber.equals("Delayed")) {
                 justInt = 0;   
                }else{
                    justInt = Integer.valueOf(justNumber);
                }

                arrivalTime.add(justInt);
                arrivalTime.get(0);

            
            }

            for(Element lineData: document.select("span.ea-line-title-bottom")){

                int bracketFirstPosition, bracketSecondPosition;
                String strData;
                String location;

                //convert the element to a string to later perform string manipulation
                strData = lineData.toString();

                //find the position of ">" as the next character is the data we want.
                bracketFirstPosition = strData.indexOf(">");

                //create a new substring that deletes all of the giberish BEFORE the position of >
                location = strData.substring(bracketFirstPosition + 1);

                //using that new string, find the position of <
                bracketSecondPosition = location.indexOf("<");

                //reassign location to its final format, deleting all the data that comes AFTER <
                location = location.substring(0, bracketSecondPosition);


                
                arrivalLoc.add(location);
                
            }

            for(Element locationData: document.select("span.ea-line-title-top")){

                int bracketFirstPosition, bracketSecondPosition;
                String strData;
                String line;

                //convert the element to a string to later perform string manipulation
                strData = locationData.toString();

                //find the position of ">" as the next character is the data we want.
                bracketFirstPosition = strData.indexOf(">");

                //create a new substring that deletes all of the giberish BEFORE the position of >
                line = strData.substring(bracketFirstPosition + 1);

                //using that new string, find the position of <
                bracketSecondPosition = line.indexOf("<");

                //reassign line to its final format, deleting all the data that comes AFTER <
                line = line.substring(0, bracketSecondPosition-4);


                arrivalLine.add(line);
            }

            for (int i = 0; i < arrivalLoc.size(); i++) {
                if (directions.contains(arrivalLoc.indexOf(arrivalLoc.get(i)))) {
                    //System.out.println("blah");
                }else{
                directions.add(arrivalLoc.indexOf(arrivalLoc.get(i)));
                }
            }
            
            System.out.println("Enter # for Destination");
            for (int i = 0; i < directions.size(); i++) {
                System.out.println(directions.get(i) + " for " + arrivalLoc.get(directions.get(i)));
            }

            System.out.println();
            
            dir = Integer.parseInt(scn.nextLine());



        }

        
        catch (Exception ex){
            ex.printStackTrace();
        }

        finally{
            printOut();
        }

        timePrinter();
        scn.close();
    }

public static void printOut(){

        for (int i = 0; i < arrivalLoc.size(); i++) {
            if (arrivalLoc.get(i).equals(arrivalLoc.get(dir))) {
            System.out.println(arrivalLine.get(i) + " To:" + arrivalLoc.get(i) + "arrives in " + arrivalTime.get(i) + " min");
            }
        }
    }

public static void timePrinter(){
    
    final int timeToWalk = 3;   
       
    timeToLeave[0] = arrivalTime.get(dir) - timeToWalk;  

        if(timeToLeave[0] > timeToWalk){
            
            System.out.println("You have " + (timeToLeave[0]) + " minutes till you must leave!");
    
        }

        else{

            System.out.println(RED_COLOR + "You don't have enough time to catch the next train! : ( " + COLOR_RESET + "\n");
            System.out.println(GREEN_COLOR + "There is another train coming!" + COLOR_RESET + "\n");

            for(int j = 0; j < arrivalLoc.size(); j++){
                if (arrivalLoc.get(j) == arrivalLoc.get(dir)) {
                    j++;
                    if (arrivalLoc.get(j).equals(arrivalLoc.get(dir))){
                        dirTwo = j;
                        break;
                    }else{
                        j++;
                    }
                }
            }


    timeToLeave[1] = arrivalTime.get(dirTwo) - timeToWalk;
    System.out.println(GREEN_COLOR + "You have " + timeToLeave[1] + " minutes till you must leave!" + COLOR_RESET);

    
    }

}

}


