/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolec;



/**
 * Linked-list implementation of a Stack.
 * 
 * @author Monica Martinez	
 * @version November 16,2014
 *
 * @param <E>
 */
public class LLStack<E>{
	
	protected LLNode<E> top;

	/**
	 * Stack constructor
	 */
	public LLStack(){
		top = null;
	}
	
	/**
	 * adds item to top of stack
	 * @param item
	 */
	public void push(E item){
		LLNode<E> newNode = new LLNode<E>(item);
		newNode.setNext(top);
		top = newNode;
	}
	
	/**
	 * determines wheter stack is empty
	 * @return true if stack is empty
	 */
	public boolean isEmpty(){
		return top == null;
	}
	
	/**
	 * Removes and returns top of stack
	 * @return top of stack
	 */
	public LLNode<E> pop(){
		if(!isEmpty())
			top = top.getNext();
		return top;
	}
	
	/**
	 * Returns top of stack
	 * @return top of stack
	 */
	public E peek(){
		if (!isEmpty())
			return top.getData();
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer s = new StringBuffer();
		LLNode<E>  current = top;
		while (current != null ) {
			s.append( current.getData().toString() + ", ");
			current = current.getNext();
		}
		return "Stack: " + s;
	}
}
