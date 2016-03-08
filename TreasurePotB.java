/*
 * This is the class for the treasure pot type B tile
 * 
 * When a player enters the tile, he earns a number of coins
 * When the player rolls the dice and leaves the tile, he leaves with
 * the amount of the dice roll.
 * 
 */

public class TreasurePotB extends Components
{
    int maxToGiveOut;
    public TreasurePotB(int maxToGiveOut)
    {
        this.maxToGiveOut = maxToGiveOut;
    }
    
    public int getCoinsLeft() {
        return this.maxToGiveOut;
    }

    public int leaveCell(Player player, int diceRoll) {
        return diceRoll;
    }

    public int tryToLeaveCell(Player player, int diceRoll) {
        return diceRoll;
    }
    
    public void enterCell(Player player, int diceRoll) {
        player.addCoins(affectPlayerCoins(player, diceRoll));
    }
    
    public int affectPlayerCoins(Player player, int diceRoll) {
        if(maxToGiveOut > 0) {
            int giveout = (maxToGiveOut>=diceRoll)?diceRoll:maxToGiveOut;
            maxToGiveOut -= giveout;
            return giveout;
        }
        return 0;
    }
}
