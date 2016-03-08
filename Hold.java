import java.util.*;

/*
 * This is the class for the Hold tile
 * 
 * When a player enters the tile, he gets added to a container and his dice
 * roll is saved.
 * When the player rolls the dice he leaves if he rolled the same dice value
 * that he entered the cell with and when he leaves, he advances by a number
 * of steps proportional to his dice roll and a multiplier
 * 
 */

public class Hold extends HoldCells
{
    private int multiplier;
    private ArrayList<Player> heldPlayers;
    
    public Hold(int multiplier)
    {
        this.heldPlayers = new ArrayList<Player>();
        this.multiplier = multiplier;
    }
    
    /**
     * If the player is inside the hold array and he rolled the same dice he entered with then let him leave
     *
     * @param player the current player to check
     * @param diceRoll the dice roll he rolled in the current turn
     * @return the steps to advance with
     */
    public int leaveCell(Player player, int diceRoll) {
        if(heldPlayers.contains(player) && player.enteredWithDice() == diceRoll){
            player.enteredWithDice(-1);
            heldPlayers.remove(player);
            return diceRoll*multiplier;
        }
        return 0;
    }

    public int tryToLeaveCell(Player player, int diceRoll) {
        if(heldPlayers.contains(player) && player.enteredWithDice() == diceRoll){
            return diceRoll*multiplier;
        }
        return 0;
    }
    
    public void enterCell(Player player, int diceRoll) {
        if(!heldPlayers.contains(player)) {
            player.enteredWithDice(diceRoll);
            heldPlayers.add(player);
        }
    }
    
    public int getMultiplier() {
        return this.multiplier;
    }
}
