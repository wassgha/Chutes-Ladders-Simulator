/*
 * 
 * Player class
 * Holds the player's info (position, last rolled dice, coins collected
 * and token color for the graphical interface)
 * 
 */

import java.awt.*;

public class Player
{
    private int[] position;
    private Color color;
    private RandomGaussian random;
    private int coins;
    private int entredWithDice;
    
    public Player()
    {
        this.random = new RandomGaussian();
        this.position = new int[2];
        this.position[0] = 0;
        this.position[1] = 0;
        this.color = new Color(random.getRandomBetween(0,255), random.getRandomBetween(0,255), random.getRandomBetween(0,255));
        this.coins = 0;
        this.entredWithDice = -1;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    
    /**
     * Method setPosition
     *
     * @param position An array of two integers that define the x and y coordinates of the player
     */
    public void setPosition(int[] position) {
        this.position=position;
    }
    
    public int[] getPosition()
    {
        return this.position;
    }
    
    public int getCoins()
    {
        return this.coins;
    }
    
    public void addCoins(int amount) {
        this.coins += amount;
    }
    
    /**
     * Method enteredWithDice
     * 
     * Saves the dice roll that the player entered the cell with (useful for Hold cells)
     *
     * @param diceRoll the dice roll that the player entered the cell with
     */
    public void enteredWithDice(int diceRoll) {
        entredWithDice = diceRoll;
    }
    
    public int enteredWithDice() {
        return entredWithDice;
    }
}
