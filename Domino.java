import java.util.Collections;
import java.util.ArrayList;

/**
 * represents a Domino
 * @author Brandon Lee
 */
public class Domino{
  
  /**first element on domino **/
  private int first;
  
  /**second element on domino**/
  private int second;
  
  /**player who owns this domino**/
  private Player player;
  
  /**
* Create a domino with first and second element
* @param first first element
* @param second second element
*/
  public Domino(int first, int second){
    this.first = first;
    this.second = second;
  }
  
  /**
* rotate this domino; swaps the first and second element
*/
  public void rotate(){
    int save = first;
    first = second;
    second = save;
  }
  
  /**
* returns string reprsentation of a domino
* first and second element enclosed in brackets
*/
  public String toString(){
    return "["+ first + "|" + second + "]";
  }
  
  /**
* returns a full set of 55 dominos in an Array list
* @return array list of 55 dominos
*/
  public static ArrayList<Domino> fullStack(){
    ArrayList<Domino> list = new ArrayList<Domino>();
    for(int i =0; i< 10; i++){
      for(int j = i; j < 10; j++){
        list.add(new Domino(i,j));
      }
    }
    return list;
  }
  
  /**
* checks whether first or second element is input number
* @param number number being checked
* @return whether the input number is on the domino
*/
  public boolean hasNumber(int number){
    if((getFirst() == number) || (getSecond() == number)){
      return true;
    }
    return false;
  }
  
  /**
* returns first element
* @return first element
*/
  public int getFirst(){
    return first;
  }
  
  /**
* returns second element
* @return second element
*/
  public int getSecond(){
    return second;
  }
  
  public void setPlayer(Player p){
    player = p;
  }
  
  public Player getPlayer(){
    return player;
  }
  
}//class