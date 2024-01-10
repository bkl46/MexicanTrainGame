import java.util.NoSuchElementException;

/**
 * A class to represent a linked list of nodes.  The list is iterable (i.e. we can loop over its data).
 */
public class LinkedList<T> implements Iterable<T> {
  /** the first node of the list, or null if the list is empty */
  private LLNode<T> firstNode;
  
  /**
   * Creates an initially empty linked list
   */
  public LinkedList() {
    firstNode = null;
  }
  
  /**
   * Returns the first node.
   */
  protected LLNode<T> getFirstNode() {
    return firstNode;
  }
  
  /**
   * Returns element in first node.
   */
  protected T firstElement() {
    if(firstNode != null){
      return firstNode.getElement();
    }
    return null;
  }

  /**
   * Changes the front node.
   * @param node  the node that will be the first node of the new linked list
   */
  protected void setFirstNode(LLNode<T> node) {
    this.firstNode = node;
  }

  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getFirstNode() == null);
  }
  
  /**
   * Add an element to the front of the linked list
   */
  public void addToFront(T element) {
    setFirstNode(new LLNode<T>(element, getFirstNode()));
  }
  
  /**
   * Removes and returns the element at the front of the linked list
   * @return the element removed from the front of the linked list
   * @throws NoSuchElementException if the list is empty
   */
  public T removeFromFront() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      T save = getFirstNode().getElement();
      setFirstNode(getFirstNode().getNext());
      return save;
    }
  }

  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int lengthSoFar = 0;
    LLNode<T> nodeptr = getFirstNode();
    while (nodeptr != null) {
      lengthSoFar++;
      nodeptr = nodeptr.getNext();
    }
    return lengthSoFar;
  }

  /**
   * Add an element to the end of a list.
   * @param element the element to add
   */
  public void addToEnd(T element) {
    if (isEmpty())
      addToFront(element);
    else {
      LLNode<T> nodeptr = getFirstNode();
      while (nodeptr.getNext() != null)
 nodeptr = nodeptr.getNext();
      nodeptr.setNext(new LLNode<T>(element, null));
    }
  }
  
  /**
   * The method required by the Iterable interface returns an iterator that loops over the data in the list.
   * @return an iterator that loops over the data in the list
   */
  public LinkedListIterator<T> iterator() {
    return new LinkedListIterator<T>(getFirstNode(),this);
  }
  
  /**
   * Insert a value in order into the linked list.
   * The input list is assumed to already have its elements stored in order.
   * @param element the element to add
   * @param list the list to add the element to
   */
  public static <T extends Comparable<? super T>> void insertInOrder(T element, LinkedList<T> list) {
    // if the list is empty, the element goes to the front of the list
    if (list.isEmpty()) {
      list.addToFront(element);
    }
    // if the element is smaller than the first element, it goes to the front
    else if (element.compareTo(list.getFirstNode().getElement()) < 0) {
      list.addToFront(element);
    }
    // otherwise, find where the element should go
    else {
      LLNode<T> nodeptr = list.getFirstNode();
      // stop the first time the next node is larger than the element we are adding
      while (nodeptr.getNext() != null && nodeptr.getNext().getElement().compareTo(element) < 0)
        nodeptr = nodeptr.getNext();
      nodeptr.setNext(new LLNode<T>(element, nodeptr.getNext()));
    }
  }
  
}
