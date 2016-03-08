
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.net.URL;
import javax.imageio.ImageIO;


/*
 * 
 * This class is deprecated.
 * 
 * This class is supposed to be a graphical implementation of tha game, however because of time
 * constraints, it was limited and did not take keyboard input therefore is not complete.
 * The class works for a small board size but does not take the "stuck in a loop" error into account
 * 
 */


public class GameGraphic extends JApplet
{
    
    // Game Elements
    private Board board;
    private Dice dice;
    private ArrayList<Player> players; 
    private Player currentPlayer;

    // Other Variables
    private int mapLength, numberOfPlayers, diceMax;
    private int cellWidth, cellHeight, canvasWidth, canvasHeight, padding;
    private int whoseTurn;
    private int turn = 0;
    private String configFile;
    private Image treasureAImage, treasureBImage, holdImage, priorityHoldImage;
    private String command;
    private Boolean gameOver;

    public void init()
    {
        setSize(600,800);
        
        // Parameters
        
        mapLength = 10;
        numberOfPlayers = 3;
        diceMax = 6;
        configFile = "config.txt";
        gameOver= false;
        
        // Initialize
        padding = 25;
        canvasWidth = getWidth() - 2*padding;
        canvasHeight = getHeight() - 2*padding;    
        cellWidth = canvasWidth/mapLength;
        cellHeight = canvasHeight/mapLength;
        whoseTurn=0;
        
        // Load Images
        treasureAImage = loadimage("treasureA.png");
        treasureBImage = loadimage("treasureB.png");
        holdImage = loadimage("hold.png");  
        priorityHoldImage = loadimage("priorityHold.png");  
        
        // Initialize dice
        dice = new Dice(diceMax);
        
        // Add Players
        players = new ArrayList<Player>();
        for(int i = 0; i<numberOfPlayers; i++) {
            players.add(new Player());
        }
        
        // Create Board
        board = new Board(configFile, mapLength, diceMax);
        
    }
    
    private boolean advancePlayer(Player player, int steps) {
        //constraints for board size
        if(steps==0) {
            System.out.println("No change");
            return false;
        } else {
            int newCell = board.convertToCellNum(player.getPosition()) + steps;
            if(newCell == (mapLength*mapLength)) {
                gameOver = true;
                return true;
            }
            newCell = (newCell<0)?0:newCell;
            newCell = (newCell>(mapLength*mapLength))?0:newCell;
            System.out.println("Player moved from " + board.convertToCellNum(player.getPosition()) + " to " + newCell);
            player.setPosition(board.convertToXY(newCell));
            return true;
        }

    }
    
    private void enterCell(Player player, int diceRoll) {
        board.getComponentAtXY(player.getPosition()[0],player.getPosition()[1]).enterCell(player, diceRoll);
    }
    
    private int newPosition(Player player, int diceRoll) {
        return board.getComponentAtXY(player.getPosition()[0],player.getPosition()[1]).leaveCell(player, diceRoll);
    }
    
    private Image loadimage(String file) {
        Image result = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        try {
            URL pic = new URL("file:" + file);
            result = ImageIO.read(pic);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void paint(Graphics g)
    {
        g.setColor(new Color(220,30,30));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.white);
        if(!gameOver) {
            for(int i=0; i<mapLength;i++) {
                for(int j=0; j<mapLength; j++) {
                    int cellNumber = board.convertToCellNum(i, j);
                    int rectx = padding + i*cellWidth;
                    int recty =  canvasHeight + padding - (j+1)*cellHeight;
                    
                     // Set color for cell
                    if (cellNumber == mapLength*mapLength) {
                        g.setColor(new Color(255,196,0));
                    } else if (cellNumber%2==0 ) {
                        g.setColor(new Color(170,170,220));
                    } else {
                        g.setColor(new Color(255,255,255));
                    }
                    g.fillRect(rectx, recty, cellWidth, cellHeight);
                    
                    // Set color for cell number
                    if (cellNumber == mapLength*mapLength) {
                        g.setColor(new Color(255,255,255));
                    } else if (cellNumber%2==0 ) {
                        g.setColor(new Color(255,255,255));
                    } else {
                        g.setColor(new Color(0,0,0));
                    }
                    
                    if(board.getComponentAtXY(i,j) instanceof TreasurePotA) {
                        TreasurePotA treasure = (TreasurePotA) board.getComponentAtXY(i,j);
                        g.drawImage(treasureAImage, rectx, recty, cellWidth, cellHeight,  this);
                        g.setColor(Color.red);
                        //g.drawString("" + treasure.getBounty(), cellWidth/3 + rectx, cellHeight/2 + recty);
                    } else if (board.getComponentAtXY(i,j) instanceof TreasurePotB) {
                        TreasurePotB treasure = (TreasurePotB) board.getComponentAtXY(i,j);
                        g.drawImage(treasureBImage, rectx+cellWidth/6, recty+cellHeight/6, 2*cellWidth/3, 2*cellHeight/3, this);
                        //g.drawString("" + treasure.getCoinsLeft(), cellWidth/3 + rectx, cellHeight/2 + recty);
                     } else if (board.getComponentAtXY(i,j) instanceof Hold) {
                        Hold hold = (Hold) board.getComponentAtXY(i,j);
                        g.drawImage(holdImage, rectx+cellWidth/6, recty+cellHeight/6, 2*cellWidth/3, 2*cellHeight/3, this);
                        //g.drawString("" + treasure.getMultiplier(), cellWidth/3 + rectx, cellHeight/2 + recty);
                     } else if (board.getComponentAtXY(i,j) instanceof PriorityHold) {
                        PriorityHold priorityHold = (PriorityHold) board.getComponentAtXY(i,j);
                        g.drawImage(priorityHoldImage, rectx+cellWidth/6, recty+cellHeight/6, 2*cellWidth/3, 2*cellHeight/3, this);
                        //g.drawString("" + treasure.getMultiplier(), cellWidth/3 + rectx, cellHeight/2 + recty);
                    } else {
                        
                        g.drawString("" + cellNumber, cellWidth/3 + rectx, cellHeight/2 + recty);
                        // g.drawImage(treasureA, 100, 100, this);
                    }
                    
                    for(int k=0; k<players.size(); k++) {
                        Player player = players.get(k);
                        if(board.convertToCellNum(player.getPosition()) == cellNumber) {
                            g.setColor(player.getColor());
                            g.fillOval(rectx+15 + 17 * (k%2), recty + 15 + 17*(k/2), 15, 15);
                            g.setColor(Color.white);
                            g.setFont(g.getFont().deriveFont(Font.PLAIN, 9)); 
                            //g.drawString("" + player.getCoins(), rectx + 20 + 17 * (k%2), recty + 25 + 17*(k/2));
                            g.setFont(g.getFont().deriveFont(Font.PLAIN, g.getFont().getSize() +4)); 
                        }
                    }
    
                }
            }
            turn++;
            int diceRoll = dice.roll();
            
            g.setColor(Color.white);
            g.drawString("( Turn " + turn + ") Player " + whoseTurn + " rolled dice : " + diceRoll, 15, 15);
          
            currentPlayer = players.get(whoseTurn);
            int steps = newPosition(currentPlayer, diceRoll);
            if (advancePlayer(currentPlayer, steps)) {
                enterCell(currentPlayer, diceRoll);
                System.out.println("Player entered with dice " + currentPlayer.enteredWithDice());
            }
            
            whoseTurn = (whoseTurn+1)%players.size();
    
            try {
            Thread.sleep(500);
            } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
            } 
            revalidate();
            repaint();
        } else {
            g.drawString("Game Over", 15, 15);
        }
    }
}


