/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princessbride;

import byui.cit260.princessBride.model.Game;
import byui.cit260.princessBride.model.Item;
import byui.cit260.princessBride.model.LightningSand;
import byui.cit260.princessBride.model.Player;

/**
 *
 * @author Gina Udy
 */
public class PrincessBride {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Game gameOne = new Game(); 
        
        gameOne.setTotalTime(3.0); 
        gameOne.setGetItem("Item"); 
        gameOne.setGetMap(0); 
        gameOne.setSaveGame(0);
        gameOne.setLoadGame(0); 
        
        String gameInfo = gameOne.toString(); 
        System.out.println(gameInfo);
        
        Player playerOne = new Player(); 
        
        playerOne.setName("John Doe"); 
        playerOne.setCoordinates(3.0); 
        playerOne.setItem("Item");
        
        String playerInfo = playerOne.toString(); 
        System.out.println(playerInfo);
        
        Item itemOne = new Item(); 
        
        itemOne.setName("John Doe"); 
        itemOne.setType("Item"); 
        itemOne.setQuantity(4);
        
        String itemInfo = itemOne.toString(); 
        System.out.println(itemInfo);
        
        LightningSand guessOne = new LightningSand(); 
        
        guessOne.setDistance(4); 
        guessOne.setCalculation(4);
        guessOne.setResponse("Wrong!  You are dead.");
        
        String lightningSandInfo = guessOne.toString(); 
        System.out.println(lightningSandInfo);
    }
    
}