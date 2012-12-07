package edu.purdue.cs.cs180.server;

import java.util.LinkedList;
import edu.purdue.cs.cs180.common.*;
/**
 * Project 8 -- SafeWalk 3.0
 * holds the lists of responders and receivers and allows the Listener and Matcher threads to access and modify the lists
 * 
 * @author Ben Wencke
 * 
 * @recitation RM5 (Julian Stephen)
 * 
 * @date December 6, 2012
 *
 */
public class Data implements DataFeeder {
  private LinkedList<Message> responderMessages; // list of responders
  private LinkedList<Message> requesterMessages; // list of requesters
  
  public Data(){
    // initialize lists (as empty)
    responderMessages = new LinkedList<Message>(); 
    requesterMessages = new LinkedList<Message>();
  }
  
  /**
   * adds a requester to the requester list
   * 
   * @param newM the Message object from the requester
   */
  public void addRequest(Message newM){
    requesterMessages.add(newM);
  }
  
  /**
   * adds a response to the responder list
   * 
   * @param newM the Message object from the responder
   */
  public void addResponse(Message newM){
    responderMessages.add(newM);
  }
  
  /**
   * retrieves the oldest requester in the list and returns it
   * 
   * @return the Message object of first requester in the list
   */
  public Message getFirstRequest(){
    return requesterMessages.getFirst();
  }
  
  /**
   * retreives the oldest responder in the list and returns it
   * 
   * @return the Message object of last responder in the list
   */
  public Message getFirstResponse(){
    return responderMessages.getFirst();
  }
  
  /**
   * retreives the newest requester in the list and returns it
   * 
   * @return the Message object of the last requester
   */
  public Message getLastRequest(){
    return requesterMessages.getLast();
  }
  
  /**
   * retreives the newest responder in the list and returns it
   * 
   * @return the Message object of the last responder
   */
  public Message getLastResponse(){
    return responderMessages.getLast();
  }
  
  /**
   * checks if there are requesters waiting
   * 
   * @return a boolean that represents if there are requesters waiting
   */
  public boolean hasNextRequest(){
    return requesterMessages.size() > 0;
  }
  
  /**
   * checks if there are responders waiting
   * 
   * @return a boolean the represents if there are responders waiting
   */
  public boolean hasNextResponse(){
    return responderMessages.size() > 0;
  }
  
  /**
   * removes the oldest requester in the list
   */
  public void removeFirstRequest(){
    requesterMessages.removeFirst();
  }
  
  /**
   * removes the oldest responder in the list
   */
  public void removeFirstResponse(){
    responderMessages.removeFirst();
  }
  
  /**
   * removes the newest requester in the list
   */
  public void removeLastRequest(){
    requesterMessages.removeLast();
  }
  
  /**
   * removes the newest responder in the list
   */
  public void removeLastResponse(){
    responderMessages.removeLast();
  }
}