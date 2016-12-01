/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.princessBride.view;

import byui.cit260.princessBride.control.BackpackControl;
import byui.cit260.princessBride.control.GameControl;
import byui.cit260.princessBride.control.MovementControl;
import byui.cit260.princessBride.exceptions.LoseException;
import byui.cit260.princessBride.exceptions.MovementControlException;
import byui.cit260.princessBride.exceptions.WinException;
import byui.cit260.princessBride.model.Item;
import byui.cit260.princessBride.model.Location;
import byui.cit260.princessBride.model.LocationType;
import byui.cit260.princessBride.model.Map;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import princessBride.PrincessBride;

/**
 *
 * @author Lisa Walker
 */
public class GameMenuView extends View {

    public GameMenuView() {

        super("\n"
                + "\n================================="
                + "\n           GAME MENU"
                + "\n================================="
                + "\n    V - View map                 "
                + "\n    B - Backpack inventory       "
                + "\n    I - Inventory report         "
                + "\n    T - Take item                "
                + "\n    U - Use item                 "
                + "\n    N - move North               "
                + "\n    E - move East                "
                + "\n    S - move South               "
                + "\n    W - move West                "
                + "\n    G - save current Game        "
                + "\n    H - Help menu                "
                + "\n    L - Look around              "
                + "\n    X - eXit to main menu        "
                + "\n=================================");
    }

    @Override
    public boolean doAction(String selection) {

        char charSel = selection.toUpperCase().charAt(0);
        try {
            switch (charSel) {
                case 'V': //View Map
                    this.viewMap();
                    break;
                case 'B': // List Backpack Inventory
                    this.showBackpack();
                    break;
                case 'I': // Inventory report
                    this.showCurrentInventory();
                    break;
                case 'T': // Take Item
                    this.takeItemFromLocation();
                    break;
                case 'U': //Use Item
                    this.useItemInBackpack();
                    break;
                case 'N':
                    this.moveNorth();
                    break;
                case 'E':
                    this: moveEast();
                    break;
                case 'S':
                    this.moveSouth();
                    break;
                case 'W':
                    this.moveWest();
                    break;
                case 'L': // Look Around
                    this.lookAround();
                    break;
                case 'H':// Help Menu
                    this.HelpMenuView();
                    break;
                case 'X':// Exit Game
                    return true;
                case 'G':// Save Current Game
                    this.saveCurrentGame();
                    break;
                default:
                    ErrorView.display(this.getClass().getName(), "\n*** Invalid Selection *** Please Try Again ***");
            }
        } catch (LoseException le) {
            this.console.println("You lost to the dreaded Fire Swamp.  Try again!");
        } catch (WinException we) {
            this.console.println("Wow!  You bested the Fire Swamp.  Congratulations!");
        }
        return false;
    }

    private void viewMap() {
        Map map = PrincessBride.getCurrentGame().getMap();

        this.console.println("\n"
                + "\n================================================="
                + "\n                 FIRE SWAMP MAP                  "
                + "\n================================================="
                + "\n");

        for (int row = 0; row < Map.ROWS; row++) {
            for (int col = 0; col < Map.COLUMNS; col++) {
                char locationType = map.getLocationAt(row, col).getLocationType().toString().charAt(0);
                this.console.print("\t" + locationType);
                if (map.getLocationAt(row, col).getItem() != null) {
                    this.console.print(map.getLocationAt(row, col).getItem().getItemDescription().charAt(0));
                }

                this.console.print("  ");

            }

            this.console.println("");
        }

        this.console.println("\n=================================================");

        Location currentLocation = PrincessBride.getCurrentGame().getPlayer().getLocation();
        this.console.println("\n             YOU ARE AT LOCATION " + currentLocation.getRow() + "." + currentLocation.getCol());
    }

    // Show the player what they have in their backpack
    private void showBackpack() {

        List<Item> currentBackpack = PrincessBride.getCurrentGame().getPlayer().getBackpack();

        try {
            BackpackControl bc = new BackpackControl();
            bc.displayBackpack();
            this.console.println("\nYou have " + "currentBackpackQuantity"
                    + " items in your backpack."
                    + "\nThe items are: "
                    + currentBackpack
                    + ".");
        } catch (Exception e) {
            ErrorView.display(this.getClass().getName(), "Your backpack is empty.");
        }
    }
    
    
// showCurrentInventory needs some help-------------------------------------------------------------------------------- 

    // Create report to show the player which items are in their inventory
    public void showCurrentInventory(ArrayList<Item> currentBackpack, String inventoryList) {
        //create BufferedReader object for input file
        try 
            (PrintWriter out = new PrintWriter(inventoryList)) {
        out.println("\n\n Inventory List");
        //print the name, description and quantity of each item
        out.printf("%n%20s%-30s%5s", "Name", "Description", "Quantity");
        out.printf("%n%20s%-30s%5s", "----", "-----------", "--------");
        for
            (Item item : currentBackpack) {
            out.printf("%n%20s%-30s%5d" // 5d means output as an integer
                    , item.getItemName()
                    , item.getItemDescription()
                    , item.getItemQuantity());
        }
        out.println("\n\n Now get out there and save the princess!");
        out.flush();
                
    }   catch (Exception e) {
    ErrorView.display(this.getClass().getName(), "You've gotta pick something up first silly!");
        }
    }
//------------------------------------------------------------------------------------------------------------------
    
    // Pick up an item and put it in backpack
    private void takeItemFromLocation() {

        Location currentLocation = PrincessBride.getPlayer().getLocation();
        
        try {
            BackpackControl bc = new BackpackControl();
            bc.addItemToBackpack();
            this.console.println("You found a "
                    + currentLocation.getItem().getItemDescription()
                    + ".  It will be added to your backpack.");
            currentLocation.setItem(null);
        } catch (Exception e) {
            ErrorView.display(this.getClass().getName(), "There is nothing here.");
        }
    }
    
    // Pick up an item and put it in backpack
    private void useItemInBackpack() {

        Location currentLocation = PrincessBride.getPlayer().getLocation();
        
        try {
            BackpackControl bc = new BackpackControl();
            bc.addItemToBackpack();
            this.console.println("You found a "
                    + currentLocation.getItem().getItemDescription()
                    + ".  It will be added to your backpack.");
            currentLocation.setItem(null);
        } catch (Exception e) {
            ErrorView.display(this.getClass().getName(), "There is nothing here.");
        }
    }

    private void moveNorth() {
        MovementControl mc = new MovementControl();
        try {
            mc.moveNorth();
        } catch (MovementControlException mce) {
            ErrorView.display(this.getClass().getName(), "You cannot move further North!");
        }
        determineNextView();
    }

    private void moveEast() {
        MovementControl mc = new MovementControl();
        try {
            mc.moveEast();
        } catch (MovementControlException mce) {
            ErrorView.display(this.getClass().getName(), "You cannot move further East!");
        }
        determineNextView();
    }

    private void moveSouth() {
        MovementControl mc = new MovementControl();
        try {
            mc.moveSouth();
        } catch (MovementControlException mce) {
            ErrorView.display(this.getClass().getName(), "You cannot move further South!");
        }
        determineNextView();
    }

    private void moveWest() {
        MovementControl mc = new MovementControl();
        try {
            mc.moveWest();
        } catch (MovementControlException mce) {
            ErrorView.display(this.getClass().getName(), "You cannot move further West!");
        }
        determineNextView();
    }

    // Determines the next view based on the player's location
    private void determineNextView() {
        Location currentLocation = PrincessBride.getCurrentGame().getPlayer().getLocation();

        try {

            if (currentLocation.getLocationType() == LocationType.FLAMESPURT && !currentLocation.getVisited()) {
                FlameSpurtView fsv = new FlameSpurtView();
                fsv.display();
                currentLocation.setVisited(true);
            } else if (currentLocation.getLocationType() == LocationType.ROUS && !currentLocation.getVisited()) {
                RodentSizeView rsv = new RodentSizeView();
                rsv.display();
                currentLocation.setVisited(true);
            } else if (currentLocation.getLocationType() == LocationType.LIGHTNINGSAND && !currentLocation.getVisited()) {
                LightningSandView lsv = new LightningSandView();
                lsv.display();
                currentLocation.setVisited(true);
            } else if (currentLocation.getLocationType() == LocationType.DART && !currentLocation.getVisited()) {
                BackpackView bpv = new BackpackView();
                bpv.display();
                currentLocation.setVisited(true);
            } else if (currentLocation.getLocationType() == LocationType.WATER && !currentLocation.getVisited()) {
                BackpackView bpv = new BackpackView();
                bpv.display();
                currentLocation.setVisited(true);
            } else if (currentLocation.getLocationType() == LocationType.POTION && !currentLocation.getVisited()) {
                BackpackView bpv = new BackpackView();
                bpv.display();
                currentLocation.setVisited(true);
            } else if (currentLocation.getLocationType() == LocationType.NONE && !currentLocation.getVisited()) {
                this.console.println("\nThere is nothing here.");
                currentLocation.setVisited(true);
            }
        } catch (Exception e) {
            ErrorView.display(this.getClass().getName(), "Error on input");
        }

    }

    private void lookAround() {
        this.console.println("***lookAround function called****");
    }

    private void saveCurrentGame() {
        this.console.println("\nSave game as: ");

        try {
            String filePath = this.getInput();
            GameControl.keepCurrentGame(filePath);

        } catch (Exception e) {
            ErrorView.display(this.getClass().getName(), "Error on input");
        }
    }

    private void HelpMenuView() {
        HelpMenuView helpMenu = new HelpMenuView();
        helpMenu.display();
    }
}
