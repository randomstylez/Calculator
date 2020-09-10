/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolec;

/**
 *
 * @author zee
 */


@SuppressWarnings("serial")
public class PostFixException extends Exception{

	public PostFixException() {
		super();
	}
	
	public PostFixException(String message){
		super(message);
	}
	
}
