package climate;

import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered 
 * linked list structure that contains USA communitie's Climate and Economic information.
 * 
 * @author Navya Sharma
 */

public class ClimateEconJustice {

    private StateNode firstState;
    
    /*
    * Constructor
    * 
    * **** DO NOT EDIT *****
    */
    public ClimateEconJustice() {
        firstState = null;
    }

    /*
    * Get method to retrieve instance variable firstState
    * 
    * @return firstState
    * 
    * **** DO NOT EDIT *****
    */ 
    public StateNode getFirstState () {
        // DO NOT EDIT THIS CODE
        return firstState;
    }

    /**
     * Creates 3-layered linked structure consisting of state, county, 
     * and community objects by reading in CSV file provided.
     * 
     * @param inputFile, the file read from the Driver to be used for
     * @return void
     * 
     * **** DO NOT EDIT *****
     */
    public void createLinkedStructure ( String inputFile ) {
        
        // DO NOT EDIT THIS CODE
        StdIn.setFile(inputFile); //CHANGE MAYBE
        StdIn.readLine();
        
        // Reads the file one line at a time
        while ( StdIn.hasNextLine() ) {
            // Reads a single line from input file
            String line = StdIn.readLine();
            // IMPLEMENT these methods
            addToStateLevel(line);
            addToCountyLevel(line);
            addToCommunityLevel(line);
        }
    }

    /*
    * Adds a state to the first level of the linked structure.
    * Do nothing if the state is already present in the structure.
    * 
    * @param inputLine a line from the input file
    */
    public void addToStateLevel ( String inputLine ) { //Change
        String[] splitString = inputLine.split(",");
        String newState = splitString[2].trim(); //New state name
        StateNode cullen = new StateNode(newState, null, null);
        int count = 0;
        StateNode ptr = firstState;
        while (ptr != null){
            if(ptr.getName().equals(newState)){ //Checks if the state is already there.
                count++; // Break the loop.
                break;
            }
            ptr = ptr.getNext();
        }
        if(count == 0){ //If the state isn't already in the Linked List, then
             // Make new State node
            if(firstState == null){ //If first state does not exist, add to front
                firstState = cullen;
            } else{
                ptr = firstState;
                while(ptr.getNext() != null){// Traferse through the linked list, to the end until null.
                    ptr = ptr.getNext();
                }
                ptr.setNext(cullen); //Adds new state to the end of the Linked List
            }
        }
    }

    /*
    * Adds a county to a state's list of counties.
    * 
    * Access the state's list of counties' using the down pointer from the State class.
    * Do nothing if the county is already present in the structure.
    * 
    * @param inputFile a line from the input file
    */
    public void addToCountyLevel ( String inputLine ) {
        String[] splitString = inputLine.split(",");
        String newCounty = splitString[1].trim();
        String compareState = splitString[2].trim();
        int check = 0;
        CountyNode cullen = new CountyNode(newCounty, null, null);
        StateNode ptrs = firstState;
        StateNode ptr = null;
        CountyNode h = null;
        //Check if there are any doubles 
        while(ptrs != null){ //Traversing through the States
            if(ptrs.getName().equals(compareState)){//Checks if the pointer state and the input are a match
                ptr = ptrs;
                h = ptrs.getDown();
                while(h != null){// Traveres through the Counties
                    if(h.getName().equals(newCounty)){//Checks if the pointer county and the input are a match
                        check = 1;//Double was found 
                        break; // Break beginning two loops
                    }
                    h = h.getNext();
                }
            }
            ptrs = ptrs.getNext();
        }
        if(check == 0){ //Change 
            if(ptr.getDown() == null){
                ptr.setDown(cullen);
            } else{
                h = ptr.getDown();
                while(h.getNext() != null){
                    h = h.getNext();
                }
                h.setNext(cullen);
            }
        }
    }

    /*
    * Adds a community to a county's list of communities.
    * 
    * Access the county through its state
    *      - search for the state first, 
    *      - then search for the county.
    * Use the state name and the county name from the inputLine to search.
    * 
    * Access the state's list of counties using the down pointer from the StateNode class.
    * Access the county's list of communities using the down pointer from the CountyNode class.
    * Do nothing if the community is already present in the structure.
    * 
    * @param inputFile a line from the input file
    */
    public void addToCommunityLevel ( String inputLine ) {
        String[] splitStrings = inputLine.split(",");
        String newCommunity = splitStrings[0].trim(); //Community name
        String compareState = splitStrings[2].trim(); //Checking this state to the community
        String compareCounty = splitStrings[1].trim();
        int check = 0;
        Double paa = Double.parseDouble(splitStrings[3]);
        Double pn = Double.parseDouble(splitStrings[4]);
        Double pa = Double.parseDouble(splitStrings[5]);
        Double pw = Double.parseDouble(splitStrings[8]);
        Double ph = Double.parseDouble(splitStrings[9]);
        String da = splitStrings[19].trim();
        Double pl = Double.parseDouble(splitStrings[49]);
        Double cof = Double.parseDouble(splitStrings[37]);
        Double pol = Double.parseDouble(splitStrings[121]);
        Data stuff = new Data(paa,pn,pa,pw,ph,da,pl,cof,pol); //Check these parenthesis
        CommunityNode cullen = new CommunityNode(newCommunity, null, stuff);
        StateNode ptrs = firstState;
        CountyNode c = null;
        CountyNode hc = null;
        CommunityNode ho = null;
        // First checking to see if the community already exists
        while(ptrs != null){ //Traversed through states to find compare state
            if(ptrs.getName().equals(compareState)){
                c = ptrs.getDown();
                while(c != null){ //Traversing through the counties to find compare county
                    if(c.getName().equals(compareCounty)){
                        hc = c;
                        ho = c.getDown();
                        while(ho != null){
                            if(ho.getName().equals(newCommunity)){
                                check = 1;
                                break;
                            }
                            ho = ho.getNext();
                        }
                    }
                    c = c.getNext(); //Goes to next County in the Linked List
                }
            }
            ptrs = ptrs.getNext();//Goes to next state in the Linked List
        }
         //Add the community with the data to the county.
        if(check == 0){
            if(hc.getDown() == null){ // If the first one is null then change the value to my community.
                hc.setDown(cullen);
            } else{
              ho = hc.getDown();
              while(ho.getNext() != null){
                ho = ho.getNext();
              } 
              ho.setNext(cullen);
         }
    }
}


    /**
     * Given a certain percentage and racial group inputted by user, returns
     * the number of communities that have that said percentage or more of racial group  
     * and are identified as disadvantaged
     * 
     * Percentages should be passed in as integers for this method.
     * 
     * @param userPrcntage the percentage which will be compared with the racial groups
     * @param race the race which will be returned
     * @return the amount of communities that contain the same or higher percentage of the given race
     */
    public int disadvantagedCommunities ( double userPrcntage, String race ) {
        int numc = 0;
        StateNode s = firstState;
        CountyNode c = null;
        CommunityNode co = null;
        Data d = null;
        while(s != null){
            c = s.getDown();
            while(c != null){
                co = c.getDown();
                while(co != null){
                    d = co.getInfo();
                    if((race.equalsIgnoreCase("White American"))){
                        if((userPrcntage <= (d.getPrcntWhite() * 100)) && (d.getAdvantageStatus().equalsIgnoreCase("True"))){
                            numc ++;
                        } 
                    }
                    else if((race.equalsIgnoreCase("African American"))){
                        if((userPrcntage <= (d.getPrcntAfricanAmerican()*100)) && (d.getAdvantageStatus().equalsIgnoreCase("True"))){
                            numc++;
                        }
                    }
                    else if((race.equalsIgnoreCase("Native American"))){
                        if((userPrcntage <= (d.getPrcntNative()*100)) && (d.getAdvantageStatus().equalsIgnoreCase("True"))){
                            numc++;
                        }
                    }
                    else if((race.equalsIgnoreCase("Asian American"))){
                        if((userPrcntage <= (d.getPrcntAsian()*100)) && (d.getAdvantageStatus().equalsIgnoreCase("True"))){
                            numc++;
                        }
                    }
                    else if((race.equalsIgnoreCase("Hispanic American"))){
                        if((userPrcntage <= (d.getPrcntHispanic()*100)) && (d.getAdvantageStatus().equalsIgnoreCase("True"))){
                            numc++;
                        }
                    }
                    co = co.getNext();
                }
                c = c.getNext();
            }
            s = s.getNext();
        }
        return numc; // replace this line
    }

    /**
     * Given a certain percentage and racial group inputted by user, returns
     * the number of communities that have that said percentage or more of racial group  
     * and are identified as non disadvantaged
     * 
     * Percentages should be passed in as integers for this method.
     * 
     * @param userPrcntage the percentage which will be compared with the racial groups
     * @param race the race which will be returned
     * @return the amount of communities that contain the same or higher percentage of the given race
     */
    public int nonDisadvantagedCommunities ( double userPrcntage, String race ) {
        int numc = 0;
        StateNode s = firstState;
        CountyNode c = null;
        CommunityNode co = null;
        Data d = null;
        while(s != null){
            c = s.getDown();
            while(c != null){
                co = c.getDown();
                while(co != null){
                    d = co.getInfo();
                    if((race.equalsIgnoreCase("White American"))){
                        if((userPrcntage <= (d.getPrcntWhite() * 100)) && (d.getAdvantageStatus().equalsIgnoreCase("False"))){
                            numc ++;
                        } 
                    }
                    else if((race.equalsIgnoreCase("African American"))){
                        if((userPrcntage <= (d.getPrcntAfricanAmerican()*100)) && (d.getAdvantageStatus().equalsIgnoreCase("False"))){
                            numc++;
                        }
                    }
                    else if((race.equalsIgnoreCase("Native American"))){
                        if((userPrcntage <= (d.getPrcntNative()*100)) && (d.getAdvantageStatus().equalsIgnoreCase("False"))){
                            numc++;
                        }
                    }
                    else if((race.equalsIgnoreCase("Asian American"))){
                        if((userPrcntage <= (d.getPrcntAsian()*100)) && (d.getAdvantageStatus().equalsIgnoreCase("False"))){
                            numc++;
                        }
                    }
                    else if((race.equalsIgnoreCase("Hispanic American"))){
                        if((userPrcntage <= (d.getPrcntHispanic()*100)) && (d.getAdvantageStatus().equalsIgnoreCase("False"))){
                            numc++;
                        }
                    }
                    co = co.getNext();
                }
                c = c.getNext();
            }
            s = s.getNext();
        }
        return numc; // replace this line
    }
    
    /** 
     * Returns a list of states that have a PM (particulate matter) level
     * equal to or higher than value inputted by user.
     * 
     * @param PMlevel the level of particulate matter
     * @return the States which have or exceed that level
     */ 
    public ArrayList<StateNode> statesPMLevels ( double PMlevel ) {
        ArrayList<StateNode> cullen = new ArrayList<>();
        StateNode s = firstState;
        StateNode h = null;
        CountyNode c = null;
        CommunityNode co = null;
        Data d = null;
        while(s != null){
            h = s;
            c = s.getDown();
            while(c != null){
                co = c.getDown();
                while(co != null){
                    d = co.getInfo();
                    if((d.getPMlevel() >= PMlevel)){
                        cullen.add(h);
                    }
                    co = co.getNext();
                }
                c = c.getNext();
            }
            s = s.getNext();
        }
       ArrayList<StateNode> newcullen = new ArrayList<>();
       for(int i = 0; i < cullen.size(); i++){
        StateNode currentElement = cullen.get(i);
        if(!newcullen.contains(currentElement)){
            newcullen.add(currentElement);
        }
       }
        return newcullen; // replace this line
    }

    /**
     * Given a percentage inputted by user, returns the number of communities 
     * that have a chance equal to or higher than said percentage of
     * experiencing a flood in the next 30 years.
     * 
     * @param userPercntage the percentage of interest/comparison
     * @return the amount of communities at risk of flooding
     */
    public int chanceOfFlood ( double userPercntage ) {
        int count = 0;
        StateNode s = firstState;
        CountyNode c = null;
        CommunityNode co = null;
        Data d = null;
        while(s != null){
            c = s.getDown();
            while(c != null){
                co = c.getDown();
                while(co != null){
                    d = co.getInfo();
                    if(userPercntage <= (d.getChanceOfFlood())){
                        count++;
                    }
                    co = co.getNext();
                }
                c = c.getNext();
            }
            s = s.getNext();
        }

        return count; // replace this line
    }

    /** 
     * Given a state inputted by user, returns the communities with 
     * the 10 lowest incomes within said state.
     * 
     *  @param stateName the State to be analyzed
     *  @return the top 10 lowest income communities in the State, with no particular order
    */
    public ArrayList<CommunityNode> lowestIncomeCommunities ( String stateName ) {
        ArrayList<CommunityNode> cullen = new ArrayList<>();
        StateNode s = firstState;
        int index = 0;
        CountyNode c = null;
        CommunityNode co = null;
        Data d = null;
        while(s.getName().equals(stateName)){
            c = s.getDown();
            while(c != null){
                co = c.getDown();
                while(co != null){
                    d = co.getInfo();
                    if((cullen.size() < 10)){ // First 10 communites are added
                        cullen.add(co);
                    } else {
                        double holder = cullen.get(0).getInfo().getPrcntAfricanAmerican();
                        for(int i = 1; i < 10; i++){
                            double curr = cullen.get(i).getInfo().getPercentPovertyLine();
                            if(curr > holder){
                                index = i;
                                holder = curr;
                            }
                        }
                        if(d.getPercentPovertyLine() < holder){
                            cullen.set(index, co);
                        }
                    } //If there are 10 communities then we are going to compare with the next one and then either add or do nothing.

                    co = co.getNext();
                }
                c = c.getNext();
            }
            s = s.getNext();
        }
       
        return cullen; // replace this line
    }
}
    
/*while(index <= cullen.size()-1){ //Loop through the arraylist
    holder = cullen.get(index); //Moving this one throught the arraylist
    g = holder.getInfo(); //Data of the one moving
    if(d.getPercentPovertyLine() > g.getPercentPovertyLine()){ //if the new povertyline is higher than the one in the array we switch the communitys in the arraylist.
        cullen.set(index,holder);// The switch
    }
    index++; // IF it doesnt switch we to the next one.
*/
 /*low = newcullen.get(0);
        for(int i = 1; i < newcullen.size(); i++){
            co = newcullen.get(i);
            d = low.getInfo();
            g = co.getInfo();
            if(g.getPercentPovertyLine() < d.getPercentPovertyLine()){
                low = co;
            }
        }
        low = cullen.get(0);
        CommunityNode bruh = null;
        for(int i = 1; i < cullen.size(); i++){
            co = cullen.get(i);
            d = low.getInfo();
            g = co.getInfo();
            if(g.getPercentPovertyLine() < d.getPercentPovertyLine()){
                low = co;
            }
        }
*/