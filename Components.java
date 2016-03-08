/*
 * 
 * This is an abstract class that defines the behaviour of the aids and obstacles
 * 
 * Each aid and obstacle must have at least four methods defined below :
 * 
 */

public abstract class Components
{
    
    /**
     * Method enterCell, called when a player enters the current cell. Does the specified action,
     * for example it can add the player to a hold list.
     *
     * @param player The current player who entered
     * @param diceRoll the dice roll they entered with
     */
    public abstract void enterCell(Player player, int diceRoll);
    
    /**
     * Method leaveCell called when a player leaves his cell. Does the consequent action and
     * returns the number of steps the player will leave the cell with.
     * 
     * @param player The current player who is trying to leave
     * @param diceRoll the dice roll they are trying to leave with
     * @return the number of steps (positive or negative)
     */
    public abstract int leaveCell(Player player, int diceRoll);

     /**
     * Method tryToLeaveCell simulates what will happen if leaveCell is called (returns the number
     * of steps to advance with if the player leaves the cell). Used to estimate whether the player
     * will be stuck in a loop or not.
     * 
     * @param player The current player who is trying to leave
     * @param diceRoll the dice roll they are trying to leave with
     * @return the number of steps (positive or negative)
     */
    public abstract int tryToLeaveCell(Player player, int diceRoll);
    
    /**
     * Method type returns the class name of the component (treasurePotA, HoldQ, etc.
     *
     * @return The class name of the component
     */
    public String type() {
        return this.getClass().getSimpleName();
    }

}
