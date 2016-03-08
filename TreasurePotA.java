/*
 * This is the class for the treasure pot type A tile
 * 
 * When a player enters the tile, he earns a number of coins
 * When the player rolls the dice and leaves the tile, he leaves with
 * the amount of the dice roll.
 * 
 */

public class TreasurePotA extends Components
{
    public int bounty, maxBountiesGiven;
    public TreasurePotA(int bounty, int maxBountiesGiven)
    {
        this.bounty = bounty;
        this.maxBountiesGiven = maxBountiesGiven;        
    }
    
    public int getBounty() {
        return this.bounty;
    }

    public int getmaxBountiesGiven() {
        return this.maxBountiesGiven;
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
        if(maxBountiesGiven > 0) {
            maxBountiesGiven--;
            return bounty;
        }
        return 0;
    }
}
