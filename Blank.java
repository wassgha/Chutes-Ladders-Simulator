/*
 * This is the class for the blank tile (no aid or obstacle)
 * 
 * When a player enters the tile, nothing happens.
 * When the player rolls the dice and leaves the tile, he leaves with
 * the amount of the dice roll.
 * 
 */

public class Blank extends Components
{
    public Blank()
    {
    }
    
    public int tryToLeaveCell(Player player, int diceRoll) {
        return diceRoll;
    }
    
    public int leaveCell(Player player, int diceRoll) {
        return diceRoll;
    }
    
    public void enterCell(Player player, int diceRoll) {
    }
}
