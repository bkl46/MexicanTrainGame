   
/**
 * represents a Domino train
 * @author Brandon Lee
 */
public class DominoTrain extends LinkedList<Domino>{
  
  /** value to start this train**/
  private int startingDouble;
  
  /**whether this train is open**/
  private boolean open;
  
  /**player owning this train**/
  private Player player;
  
  /**
   * Create a domino train with a starting double owned by player
   * @param startingdouble starting double
   * @param player player
   */
  public DominoTrain(int startingdouble, Player player){
    super();
    this.startingDouble = startingdouble;
    open = false;
    this.player = player;
  }
  
  /**
   * overrides the addToFront method from LinkedList
   * only adds a domino if the input domino has the element that
   * matches the end value of this train
   * @param domino input domino
   */
  @Override
  public void addToFront(Domino domino){
    //has element that matches end value
    if((canAdd(domino)) && (isOpen() || (this.player == domino.getPlayer()))){
      //first value matches endvalue
      if(domino.getFirst() == getEndValue()){
        super.addToFront(domino);
      }
      //second value matches endvalue
      else{
        domino.rotate();
        super.addToFront(domino);
      }
    }
  }
  
  //getendvalue method
  public int getEndValue(){
    if(isEmpty()){
      return startingDouble;
    }
    else {
      return getFirstNode().getElement().getSecond();
    }
  }
  
  //canadd method
  public boolean canAdd(Domino domino){
    return domino.hasNumber(getEndValue());
  }
  
  //check whether train isopen
  public boolean isOpen(){
    return open;
  }
  
  public void setOpen(boolean open){
    this.open = open;
  }
  
  //returns player for this train
  public Player getPlayer(){
    return player;
  }
  
  
}//end class