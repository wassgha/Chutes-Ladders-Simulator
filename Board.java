import java.util.*;
import java.io.*;
import java.net.URL;

/*
 * 
 * This is the board class, includes the map for the board (with all the components and component types)
 * Methods in this class are used to generate the map, to get components and also to define the link between
 * the two dimentional container and the actual cell number (methods include conversion from XY to cell 
 * number and from cell number to XY coordinates)
 * 
 */

public class Board
{
    public ArrayList<ArrayList<Components>> map ;
    private RandomGaussian gaussianGenerator;
    private int totalCells;
    private int mapLength;
    private int maxDiceRoll;
    private String config;
    
    /**
     * Board Constructor, initializes variables and generates a map
     *
     * @param config Configuration file that has all the required parameters for the components
     * @param mapLength the length of the map (a 10 by 10 map has length 10 for example)
     * @param maxDiceRoll the configuration of the dice (a cube dice has maximum of 6 for example)
     */
    public Board(String config, int mapLength, int maxDiceRoll)
    {
        this.mapLength = mapLength;
        this.maxDiceRoll = maxDiceRoll;
        map=new ArrayList<ArrayList<Components>>(mapLength);
        totalCells=mapLength*mapLength;
        gaussianGenerator = new RandomGaussian();
        this.config=config;
        
        generateBoard();
        
    }
    
    
    /**
     * Method generateBoard used to place components at the right place on the map
     * following a Gaussian Distribution with constraints to avoid getting the players
     * stuck in a loop
     */
    private void generateBoard() {
        // filling board with blank cells
        for(int i=0; i<mapLength; i++) {
            map.add(new ArrayList<Components>(mapLength));
            for(int j=0; j<mapLength; j++) {
                map.get(i).add(new Blank());
            }
        }
        

        ArrayList<Integer> potAConfig = getConfig("treasurePotA");
        ArrayList<Integer> potBConfig = getConfig("treasurePotB");
        ArrayList<Integer> holdConfig = getConfig("Hold");
        ArrayList<Integer> holdQConfig = getConfig("HoldQ");
        ArrayList<Integer> priorityHoldConfig = getConfig("PriorityHold");
        
        // Convert percentage to a probability between 0 and 1
        double percentagePotA = (float)potAConfig.get(0)/100;
        double percentagePotB = (float)potBConfig.get(0)/100;
        double percentageHold = (float)holdConfig.get(0)/100;
        double percentageHoldQ = (float)holdQConfig.get(0)/100;
        double percentagePriorityHold = (float)priorityHoldConfig.get(0)/100;
        
        // Determine area for gaussian distribution
        InverseNormalDist gaussianCalculator = new InverseNormalDist();
        double aggregatePercentage = percentagePotA;
        double[] PotABounds = gaussianCalculator.outside(aggregatePercentage, 0, 1);
        aggregatePercentage += percentagePotB;
        double[] PotBBounds = gaussianCalculator.outside(aggregatePercentage, 0, 1);
        aggregatePercentage += percentageHold;
        double[] HoldBounds = gaussianCalculator.outside(aggregatePercentage, 0, 1);
        aggregatePercentage += percentageHoldQ;
        double[] HoldQBounds = gaussianCalculator.outside(aggregatePercentage, 0, 1);
        aggregatePercentage += percentagePriorityHold;
        double[] PriorityHoldBounds = gaussianCalculator.outside(aggregatePercentage, 0, 1);
        
        
        // Go through the cells and if the generated gaussian number is within the range of the components,
        // and the aid won't kick the player out of the map (maxDice * multiplier < map bounds) then place
        // the component
        
        for(int i=1; i<=totalCells; i++) {
            
                int thisCell = i;
                int[] thisCellXY = convertToXY(thisCell);
                int[] previousCellXY = convertToXY(thisCell-1);
                if(thisCell<totalCells && thisCell>1) {
                    double normalDist = gaussianGenerator.getGaussianDouble(0, 1);
                    if((normalDist<PotABounds[0] || normalDist>PotABounds[1])) {
                        int bounty = potAConfig.get(1);
                        int maxBountiesGiven = potAConfig.get(2);
                        map.get(thisCellXY[0]).set(thisCellXY[1], new TreasurePotA(bounty, maxBountiesGiven));
                    } else if ((normalDist<PotBBounds[0] || normalDist>PotBBounds[1])) {
                        int maxToGiveOut = potBConfig.get(1);
                        map.get(thisCellXY[0]).set(thisCellXY[1], new TreasurePotB(maxToGiveOut));
                    } else if ((normalDist<HoldBounds[0] || normalDist>HoldBounds[1]) && !(getComponentAtXY(previousCellXY[0], previousCellXY[1]) instanceof HoldCells)) {
                        if(isPossibleToPlace(holdConfig, thisCell)) {
                            int multiplier = generateMultiplier(holdConfig);
                            while(!isPossibleToPlace(multiplier, thisCell)) {
                                multiplier = generateMultiplier(holdConfig);
                            }
                            map.get(thisCellXY[0]).set(thisCellXY[1], new Hold(multiplier));
                        }
                    } else if ((normalDist<HoldQBounds[0] || normalDist>HoldQBounds[1]) && !(getComponentAtXY(previousCellXY[0], previousCellXY[1]) instanceof HoldCells)) {
                        if(isPossibleToPlace(holdQConfig, thisCell)) {
                            int multiplier = generateMultiplier(holdQConfig);
                            while(!isPossibleToPlace(multiplier, thisCell)) {
                                 multiplier = generateMultiplier(holdQConfig);
                            }
                            map.get(thisCellXY[0]).set(thisCellXY[1], new HoldQ(multiplier));
                        }
                    } else if ((normalDist<PriorityHoldBounds[0] || normalDist>PriorityHoldBounds[1]) && !(getComponentAtXY(previousCellXY[0], previousCellXY[1]) instanceof HoldCells)) {
                        if(isPossibleToPlace(priorityHoldConfig, thisCell)) {
                            int multiplier = generateMultiplier(priorityHoldConfig);
                            while(!isPossibleToPlace(multiplier, thisCell)) {
                                 multiplier = generateMultiplier(priorityHoldConfig);
                            }
                            map.get(thisCellXY[0]).set(thisCellXY[1], new PriorityHold(multiplier));
                        }
                    }
                }
        }        
    }
    
    private int generateMultiplier(ArrayList<Integer> config) {
        return gaussianGenerator.getRandomBetween(config.get(1), config.get(2));
    }
    
    private boolean isPossibleToPlace(ArrayList<Integer> config, int cellNum) {
        return (config.get(1)*maxDiceRoll)<=(totalCells - cellNum);
    }
  
    /**
     * Method isPossibleToPlace checks if it possible to place a Hold cell by checking whether
     * there is a possibility for the user to get out of the board or not.
     *
     * @param multiplier the multiplier of the hold cell
     * @param x the location of the cell
     * @param y the location of the cell
     * @return true if possible, false if not
     */
    private boolean isPossibleToPlace(int multiplier, int cellNum) {
        return multiplier!=0 && ((multiplier*maxDiceRoll)<=(totalCells - cellNum));
    }
 
    /**
     * Method convertToXY this method defines the relationship between the cell number (ex: 1 to 100)
     * and its coordinates on the map (x and y coordinates)
     *
     * @param cellNum the cell number
     * @return returns an array of x and y coordinate (2 values)
     */
    public int[] convertToXY(int cellNum) {
        int [] result = new int [2];
        result[1] = ((cellNum-1)/mapLength);
        if(result[1]%2==0) {
            result[0] = (cellNum%mapLength==0)?(mapLength-1):(cellNum%mapLength - 1);

        } else {
           result[0] = (cellNum%mapLength==0)?0:(mapLength - cellNum%mapLength);            
        }
        return result;
    }

    /**
     * Method convertToCellNum used to convert from coordinates (x and y) to a cell number
     *
     * @param pos array of 2 values (x and y)
     * @return the cell number
     */
    public int convertToCellNum(int[] pos) {
        return (pos[1]%2==0)?(pos[1]*mapLength + pos[0] + 1):(pos[1]*mapLength + mapLength - pos[0]);
    }
    
    /**
     * Method convertToCellNum used to convert from coordinates (x and y) to a cell number
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the cell number
     */
    public int convertToCellNum(int x, int y) {
        return (y%2==0)?(y*mapLength + x + 1):(y*mapLength + mapLength - x);
    }
    
    private ArrayList<Integer> getConfig(String component)
    {
        ArrayList<Integer> output = new ArrayList<Integer>();
        try {
            Scanner configFile = new Scanner(new URL("file:" + this.config).openStream());
            while (configFile.hasNext())
                if (configFile.next().equals(component)) break;
           
            while (configFile.hasNextInt()) {
                output.add(configFile.nextInt());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
    
    private int constrain(int number, int min, int max) {
        return number<min?min:(number>max?max:number);
    }
    
    public Components getComponentAtXY(int x, int y) {
        return this.map.get(x).get(y);
    }
}
