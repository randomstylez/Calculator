/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolec;



/**
 * Linked-list Node Class.
 * 
 * @author Monica Martinez
 * @version November 16, 2014
 *
 * @param <E>
 */
public class LLNode<E> {
	
	private LLNode<E> next;
	private E data;
	
	/**
	 * Node constructor
	 * @param data
	 */
	public LLNode(E data){
		this.data = data;
		next = null;
	}
	
	/**
	 * sets node data
	 * @param data
	 */
	public void setData(E data){
		this.data = data;
	}
	
	/**
	 * returns node data
	 * @return data
	 */
	public E getData(){
		return data;
	}
	
	/**
	 * sets next node reference
	 * @param next
	 */
	public void setNext(LLNode<E> next){
		this.next = next;
	}
	
	/**
	 * returns next node reference
	 * @return node
	 */
	public LLNode<E> getNext(){
		return next;
	}
}
