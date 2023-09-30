//import java.text.SimpleDateFormat;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;

public class CTATracker {

    public static Element timeList;

    public static String[] arrivalTime = new String[7];
    public static int arrPos = 0;
    public static String[] arrivalLoc = new String[7];
    public static int arrPosTwo = 0;
    public static void main(String[] args) throws Exception {

final String url = "https://www.transitchicago.com/station/sprt/";



try{
    //go the the URL and create a new document of that URL (I think???)
    Document document = Jsoup.connect(url).get();

    //System.out.println(Element.getElementsByAttributeValueContaining(document, "estimated-arrivals-line-wrapper"));

    /* after inspecting element and finding the code that pertains to the data we want (span.ea-line-time-big), for each time that element occurs in document, 
     * create an element that contains the data stored in span.ea-line.time.big
     * 
    */
    
      for(Element timeData: document.select("span.ea-line-time-big")){

        int bracketFirstPosition, bracketSecondPosition;
        String strData;
        String justNumber;

        //convert the element to a string to later perform string manipulation
        strData = timeData.toString();

        //at this point the string strData will print out in this format (below)

        /*  <span class="ea-line-time-big">Due</span>
        <span class="ea-line-time-big">19</span>
        <span class="ea-line-time-big">29</span>
        <span class="ea-line-time-big">51</span>
        <span class="ea-line-time-big">11</span>
        <span class="ea-line-time-big">22</span>     */

        //The data we want is the numbers and any instance of the word "Due" as those represent the next train times.
        //We now need to modify the string strData to remove all of the giberish, leaving just the numbers or the word due

        //find the position of ">" as the next character is the data we want.
        bracketFirstPosition = strData.indexOf(">");

        //create a new substring that deletes all of the giberish BEFORE the position of >
        justNumber = strData.substring(bracketFirstPosition + 1);

        //using that new string, find the position of <
        bracketSecondPosition = justNumber.indexOf("<");

        //reassign justNumber to its final format, deleting all the data that comes AFTER <
        justNumber = justNumber.substring(0, bracketSecondPosition);


        //System.out.println(justNumber);
        arrivalTime[arrPos] = justNumber;
        System.out.println(arrivalTime[arrPos]);
        arrPos++;

      
    }

    for(Element locationData: document.select("span.ea-line-title-bottom")){

        int bracketFirstPosition, bracketSecondPosition;
        String strData;
        String location;

        //convert the element to a string to later perform string manipulation
        strData = locationData.toString();

        //at this point the string strData will print out in this format (below)

        /*  <span class="ea-line-time-big">Due</span>
        <span class="ea-line-time-big">19</span>
        <span class="ea-line-time-big">29</span>
        <span class="ea-line-time-big">51</span>
        <span class="ea-line-time-big">11</span>
        <span class="ea-line-time-big">22</span>     */

        //The data we want is the numbers and any instance of the word "Due" as those represent the next train times.
        //We now need to modify the string strData to remove all of the giberish, leaving just the numbers or the word due

        //find the position of ">" as the next character is the data we want.
        bracketFirstPosition = strData.indexOf(">");

        //create a new substring that deletes all of the giberish BEFORE the position of >
        location = strData.substring(bracketFirstPosition + 1);

        //using that new string, find the position of <
        bracketSecondPosition = location.indexOf("<");

        //reassign location to its final format, deleting all the data that comes AFTER <
        location = location.substring(0, bracketSecondPosition);


        //System.out.println(location);
        arrivalLoc[arrPosTwo] = location;
        System.out.println(arrivalLoc[arrPosTwo]);
        arrPosTwo++;

        
    }
    
    
   
printOut();
    
}



catch (Exception ex){
    ex.printStackTrace();
}



 

}

public static void printOut(){
for(int i = 0; i > arrivalTime.length-1; i++){
    System.out.println(arrivalLoc[i] + " ");
    System.out.print(arrivalTime[i]);
    }
}
}

