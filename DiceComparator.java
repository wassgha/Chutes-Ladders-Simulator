/*
 * 
 * Comparator Class for the PriorityHold cell
 * 
 * Used to sort players according to the diceRoll they entered the cell
 * with.
 * 
 */

import java.util.Comparator;
public class DiceComparator implements Comparator<Player>
{
    @Override
    public int compare(Player x, Player y)
    {
        if (x.enteredWithDice() < y.enteredWithDice())
        {
            return 1;
        }
        if (x.enteredWithDice() > y.enteredWithDice())
        {
            return -1;
        }
        return 0;
    }
}