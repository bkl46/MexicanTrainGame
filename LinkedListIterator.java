import java.util.Iterator;
import java.util.NoSuchElementException;

/** An iterator for our linked list.  The iterator loops over the data in the list from
  * the first node to the last.
  */
public class LinkedListIterator<T> implements Iterator<T> {
  
  // keeps track of which node will store the next value of the iteration
  private LLNode<T> nodeptr;
  
  private LinkedList<T> list;
  private boolean nextused;

  
  /**
   * Create an iterator that loops over the data in the list starting at the given first node
   * @param firstNode the node to start this loop over the data in the list
   */
  public LinkedListIterator(LLNode<T> firstNode, LinkedList<T> list) {
    nodeptr = firstNode;
    this.list = list;
    nextused = false;

  }
  
  /**
   * Returns true if there is more data we can loop over and false if the loop reached the end of the list.
   * @return true if there is more data to loop over
   */
  public boolean hasNext() {
    return nodeptr != null;
  }
  
  /**
   * Returns the next value from the linked list in this iterator that loops over the list data.
   * @return the next value in this loop over the linked list data
   * @throws NoSuchElementException if next() is called after the loop reaches the end of the list
   */
  public T next() {
    if (nodeptr == null)
      throw new NoSuchElementException();
    
    T save = nodeptr.getElement();
    nodeptr = nodeptr.getNext();
    nextused = true;
    return save;
  }
  
  public void remove(){
    //next has been used and the list is not empty
    if( nextused && (!list.isEmpty()) ){
      //list has only one node
      if(list.getFirstNode().getNext() == null){
        list.setFirstNode(null);
      }
      //nodeptr is null, node being removed is last
      else if(nodeptr == null){
        LLNode<T> index = list.getFirstNode();
        while(index.getNext().getNext() != null){
          index = index.getNext();
        }
        index.setNext(null);
      } 
      //nodeptr is second node, node being removed is first
      else if(nodeptr == list.getFirstNode().getNext()){
        list.setFirstNode(nodeptr);
      }
      else{
        LLNode<T> index = list.getFirstNode();
        while(index.getNext().getNext() != nodeptr){
          index = index.getNext();
        }
        index.setNext(nodeptr);
      }
    }
  }
  
  
  
}//class