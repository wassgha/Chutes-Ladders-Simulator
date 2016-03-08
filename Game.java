
import java.util.*;
import java.io.*;

/*
 * 
 * This is the game class
 * 
 * It builds the connections between the components from board to players to
 * obstacles and aids. The game has two modes, silent mode used for data gathering
 * and normal mode where the user can simulate the game on the terminal either by 
 * advancing turn by turn or by typing "i" and letting the simulation work automatically
 * 
 */

public class Game
{
    
    // Game Elements
    private Board board;
    private Dice dice;
    private ArrayList<Player> players; 
    private Player currentPlayer;

    // Other Variables
    private int mapLength, numberOfPlayers, diceMax;
    private int whoseTurn;
    private int turn = 0;
    private int diceRoll;
    private String configFile;
    private String command;
    private Boolean gameOver;

    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * has been loaded into the system. It is always called before the first 
     * time that the start method is called.
     */
    public static void main(int length, int numberOfPlayers, int diceMax, String config)
    {
        
        Game g = new Game();
        
        // Parameters
        
        g.mapLength = length;
        g.numberOfPlayers = numberOfPlayers;
        g.diceMax = diceMax;
        g.configFile = config;
        g.gameOver = false;
        
        // Initialize
        g.whoseTurn=0;  
        g.command = "-1";
        
        // Initialize dice
        g.dice = new Dice(g.diceMax);
        
        // Add Players
        g.players = new ArrayList<Player>();
        for(int i = 0; i<g.numberOfPlayers; i++) {
            g.players.add(new Player());
        }
        
        // Create Board
        g.board = new Board(g.configFile, g.mapLength, g.diceMax);
        
        g.printMap();

        System.out.println("****************************************************");
        System.out.println("c : next player turn");
        System.out.println("p : print map and aids/obstacles");
        System.out.println("i : advance automatically");
        System.out.println("r : print players");
        System.out.println("****************************************************");
        System.out.print("Type command: ");
            
        // Read Command
        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ));
        
        while(!g.command.equals("q") && !g.gameOver) {
            if(!g.command.equals("i")) {
                try {
                 g.command = reader.readLine();
                } catch (Exception e) {
                 System.out.println( "Exception occured" );
                 e.printStackTrace();
                }
            }
            
            
            if(g.command.equals("c")) {
                 g.advanceGame();
                 g.command = "-1";
            } else if (g.command.equals("p")) {
                g.printMap();
                g.command = "-1";
            } else if (g.command.equals("i")) {
                g.advanceGameSilent();
                /*try {
                    Thread.sleep(3000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }*/
            } else if (g.command.equals("r")) {
                g.printPlayers();
                g.command = "-1";
            }
        }
    
        if(g.gameOver) {
            System.out.println("!!!!!! GAME OVER !!!!!!");
            g.printPlayers();
        }
    }

    public String headlessGame(int mapLength, int numberOfPlayers, int diceMax, boolean dumpPlayers)
    {
        
        Game g = new Game();
        
        // Parameters
        
        g.mapLength = mapLength;
        g.numberOfPlayers = numberOfPlayers;
        g.diceMax = diceMax;
        g.configFile = "config.txt";
        g.gameOver = false;
        
        // Initialize
        g.whoseTurn=0;  
        g.command = "-1";
        
        // Initialize dice
        g.dice = new Dice(g.diceMax);
        
        long timeToBuildGame = System.currentTimeMillis();
        
        // Add Players
        g.players = new ArrayList<Player>();
        for(int i = 0; i<g.numberOfPlayers; i++) {
            g.players.add(new Player());
        }
        
        // Create Board
        g.board = new Board(g.configFile, g.mapLength, g.diceMax);
        
        timeToBuildGame = System.currentTimeMillis() - timeToBuildGame;

        long timeToFinishGame = System.currentTimeMillis();

        while(!g.gameOver) {
           g.advanceGameSilent();
        }
        
        timeToFinishGame = System.currentTimeMillis() - timeToFinishGame;
        
        /*
         * Returns in CSV format the following :
         * 
         * maplength, numberOfPlayers, diceMax, timeToBuildGame, timeToFinishGame, numberOfTurns, player1id, player1Pos, player1Coins, player2id, player2Pos, player2Coins
         * 
         */
        if(g.gameOver) {
            String result = g.mapLength + ", " + g.numberOfPlayers + ", " + g.diceMax + ", " + timeToBuildGame + ", " + timeToFinishGame + ", " + (g.turn/g.numberOfPlayers) + ", ";
            if ( dumpPlayers) {
                for(int k=0; k<g.players.size(); k++) {
                    Player player = g.players.get(k);
                    result += k + ", " + g.board.convertToCellNum(player.getPosition()[0], player.getPosition()[1]) + ", " + player.getCoins() + ", ";
                }
            }
            return result;
        }
        
        // if for some reason the game didn't finish
        return "You should never get this message, if you do then something horrible happened. Call 911.";    
    }

    public String headlessGameAverage(int mapLength, int numberOfPlayers, int diceMax, int numberOfExperiments)
    {
        long sumTimeToBuildGame = 0;
        long sumTimeToFinishGame =0;
        long sumTurns =0;
        for(int i = 0; i<numberOfExperiments; i++) {
            Game g = new Game();
            
            // Parameters
            
            g.mapLength = mapLength;
            g.numberOfPlayers = numberOfPlayers;
            g.diceMax = diceMax;
            g.configFile = "config.txt";
            g.gameOver = false;
            
            // Initialize
            g.whoseTurn=0;  
            g.command = "-1";
            
            // Initialize dice
            g.dice = new Dice(g.diceMax);
            
            long timeToBuildGame = System.currentTimeMillis();
            
            // Add Players
            g.players = new ArrayList<Player>();
            for(int j = 0; j<g.numberOfPlayers; j++) {
                g.players.add(new Player());
            }
            
            // Create Board
            g.board = new Board(g.configFile, g.mapLength, g.diceMax);
            
            timeToBuildGame = System.currentTimeMillis() - timeToBuildGame;
    
            long timeToFinishGame = System.currentTimeMillis();
    
            while(!g.gameOver) {
               g.advanceGameSilent();
            }
            
            timeToFinishGame = System.currentTimeMillis() - timeToFinishGame;
            
            /*
             * Returns in CSV format the following :
             * 
             * maplength, numberOfPlayers, diceMax, timeToBuildGame, timeToFinishGame, numberOfTurns, player1id, player1Pos, player1Coins, player2id, player2Pos, player2Coins
             * 
             */
            if(g.gameOver) {
                sumTurns += g.turn/g.numberOfPlayers;
                sumTimeToBuildGame += timeToBuildGame;
                sumTimeToFinishGame += timeToFinishGame;
            }
        }
        
        String result = mapLength + ", " + numberOfPlayers + ", " + diceMax + ", " + (sumTimeToBuildGame/numberOfExperiments) + ", " + (sumTimeToFinishGame/numberOfExperiments) + ", " + (sumTurns/numberOfExperiments);
        return result;
    }
    
    public String headlessGame(int mapLength, int numberOfPlayers, int diceMax) {
        return headlessGame(mapLength, numberOfPlayers, diceMax, true);
    }
    
    private boolean advancePlayer(Player player, int steps, boolean silent) {
        int[] newPosition = calculateNewPosition(player, steps);
        //constraints for board size
        if(newPosition==player.getPosition()) {
            if(!silent)
            System.out.println("No change");
            return false;
        } else {
            if(board.convertToCellNum(newPosition) == (mapLength*mapLength)) {
                gameOver = true;
                player.setPosition(newPosition);
                return false;
            }
            if(!silent)
            System.out.println("Player moved from " + board.convertToCellNum(player.getPosition()) + " to " + board.convertToCellNum(newPosition));
            player.setPosition(newPosition);
            return true;
        }

    }

    private int[] calculateNewPosition(Player player, int steps) {
        //constraints for board size
        if(steps==0) {
            return player.getPosition();
        } else {
            int newCell = board.convertToCellNum(player.getPosition()) + steps;
            newCell = (newCell<0)?0:newCell;
            // if the player went out of the board then he stays where he is;
            newCell = (newCell>(mapLength*mapLength))?board.convertToCellNum(player.getPosition()):newCell;
            return board.convertToXY(newCell);
        }

    }
    
    private int calculateSteps(Player player) {
        // Check if the player is stuck in a loop (is in a hold cell and when he leaves it, will be in another hold cell)
        // In that case, roll the dice again.
        Components currentComponent = board.getComponentAtXY(player.getPosition()[0],player.getPosition()[1]);
        int[] newPositionXY = calculateNewPosition(player, currentComponent.tryToLeaveCell(player, diceRoll));
        Components prospectiveComponent = board.getComponentAtXY(newPositionXY[0],newPositionXY[1]);
        // In case all the cells around the player's position are hold cells, then try for 10 times and give up so that the game
        // does not get in an infinite loop
        int trials = 100;
        while (testForLoop(currentComponent, prospectiveComponent) && trials > 0) {
            diceRoll = dice.roll();
            player.enteredWithDice(diceRoll);
            newPositionXY = calculateNewPosition(player, currentComponent.tryToLeaveCell(player, diceRoll));
            prospectiveComponent = board.getComponentAtXY(newPositionXY[0],newPositionXY[1]);
            trials --;
        }
        // return the finally computed steps that the player will be advanced by without getting stuck in an infinite loop
        return currentComponent.leaveCell(player, diceRoll);
    }
    
    private boolean testForLoop(Components first, Components second) {
        //return (first instanceof HoldCells) && (second instanceof HoldCells) && ((HoldCells)first).getMultiplier() == -((HoldCells)second).getMultiplier();
        return (first instanceof HoldCells) && (second instanceof HoldCells) && (((HoldCells)first).getMultiplier()<0 || ((HoldCells)second).getMultiplier()<0);
    }

    private void enterCell(Player player) {
        board.getComponentAtXY(player.getPosition()[0],player.getPosition()[1]).enterCell(player, diceRoll);
    }
    
    public void printMap()
    {
        for(int i=mapLength-1; i>=0;i--) {
            for(int j=0; j<mapLength; j++) {
               int cellNumber = board.convertToCellNum(j, i);
                
               if(board.getComponentAtXY(j,i) instanceof TreasurePotA) {
                    System.out.print("[ A ] ");
               } else if (board.getComponentAtXY(j,i) instanceof TreasurePotB) {
                    System.out.print("[ B ] ");
               } else if (board.getComponentAtXY(j,i) instanceof Hold) {
                    System.out.print("[ H ] ");
               } else if (board.getComponentAtXY(j,i) instanceof HoldQ) {
                    System.out.print("[ Q ] ");
               } else if (board.getComponentAtXY(j,i) instanceof PriorityHold) {
                    System.out.print("[ P ] ");
               } else {
                    if(cellNumber<10) {
                        System.out.print("[ " + cellNumber + " ] ");
                    } else if ( cellNumber<100) {
                        System.out.print("[" + cellNumber + " ] ");
                    } else {
                        System.out.print("[" + cellNumber + "] ");
                    }
               }
            }
            System.out.println();
        }
        
        for(int i=mapLength-1; i>=0;i--) {
            for(int j=0; j<mapLength; j++) {
               if(board.getComponentAtXY(j,i) instanceof TreasurePotA) {
                   TreasurePotA treasure = (TreasurePotA) board.getComponentAtXY(j,i);
                   System.out.print("Treasure [ A ] at " + board.convertToCellNum(j,i) + " can give " + treasure.getmaxBountiesGiven() + " more bounties of " + treasure.getBounty() + " coins | ");
               } else if (board.getComponentAtXY(j,i) instanceof TreasurePotB) {
                   TreasurePotB treasure = (TreasurePotB) board.getComponentAtXY(j,i);
                   System.out.print("Treasure [ B ] at " + board.convertToCellNum(j,i) + " can give " + treasure.getCoinsLeft() + " more coins | ");
               } else if (board.getComponentAtXY(j,i) instanceof Hold) {
                   Hold hold = (Hold) board.getComponentAtXY(j,i);
                   System.out.print("Hold [ H ] at " + board.convertToCellNum(j,i) + " of multiplier " + hold.getMultiplier() + " | ");
               } else if (board.getComponentAtXY(j,i) instanceof HoldQ) {
                   HoldQ holdQ = (HoldQ) board.getComponentAtXY(j,i);
                   System.out.print("HoldQ [ Q ] at " + board.convertToCellNum(j,i) + " of multiplier " + holdQ.getMultiplier() + " | ");
               } else if (board.getComponentAtXY(j,i) instanceof PriorityHold) {
                   PriorityHold priorityHold = (PriorityHold) board.getComponentAtXY(j,i);
                   System.out.print("PriorityHold [ P ] at " + board.convertToCellNum(j,i) + " of multiplier " + priorityHold.getMultiplier() + " | ");
               }
            }
            System.out.println();
        }
        
        printPlayers();
    }
    
    public void printPlayers()
    {
        for(int k=0; k<players.size(); k++) {
            Player player = players.get(k);
            System.out.println("Player " + k + " is at " + board.convertToCellNum(player.getPosition()[0], player.getPosition()[1]) + " (" + player.getPosition()[0] + "," + player.getPosition()[1] + ") and has " + player.getCoins() + " coins");
        }
    }
    
    public void advanceGame()
    {
        advanceGame(false);
    }
    
    public void advanceGameSilent()
    {
        advanceGame(true);
    }
    
    public void advanceGame(boolean silent)
    {
        turn++;
        diceRoll = dice.roll();
        silent = true;
        
        if(!silent)
            System.out.println("( Turn " + turn + ") Player " + whoseTurn + " rolled dice : " + diceRoll);
      
        currentPlayer = players.get(whoseTurn);
        int steps = calculateSteps(currentPlayer);
        if (advancePlayer(currentPlayer, steps, silent)) {
            enterCell(currentPlayer);
        }
        whoseTurn = (whoseTurn+1)%players.size();
    }
}


