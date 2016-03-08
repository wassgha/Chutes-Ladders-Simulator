import java.io.*;
/**
 * This is the Experiment Controller class, it is used to run experiments
 * and get data in a CSV format for it to be analyzed and plotted later on.
 * 
 * See Project report for more info about the experiments designed here.
 * 
 */
public class ExperimentController
{
    public ExperimentController()
    {

    }
    
    public static void main (String[] args) {
        ExperimentController experiment = new ExperimentController();
        experiment.runIsWinnerHighestCoins();
    }
    
    public void runIsWinnerHighestCoins() {
        
        for(int j = 0; j<5000; j++) {
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("IsWinnerHighestCoins.csv", true)))) {
                Game game = new Game();
                out.println(game.headlessGame(10, 6, 6));
            }catch (IOException e) {
            }
        }
    }
    
    public void runBoardSize() {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("outputComplexityBoardSize.csv", true)))) {
            out.println("Board, Players, Dice, Average Build Time, Average Game Time, Average Game Turns");
        }catch (IOException e) {
        }
            
        for(int j = 10; j<9000; j+=(placeOfNumber(j)/4)) {
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("outputComplexityBoardSize.csv", true)))) {
                Game game = new Game();
                out.println(game.headlessGameAverage(j, 10, 6, 5));
            }catch (IOException e) {
            }
        }
    }
    
    public void runProbabilities10x10 () {
        for(int j = 0; j<1000; j++) {
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("outputProbability10By10.csv", true)))) {
                Game game = new Game();
                out.println(game.headlessGame(10, 5, 6));
            }catch (IOException e) {
            }
        }
    }
    
    public void runNumberOfPlayers (int maxPlayers) {
        for(int i = 1; i< maxPlayers; i+=placeOfNumber(i))
        {
            for(int j = 0; j<5; j++) {
                try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("outputNumberOfPlayers.csv", true)))) {
                    Game game = new Game();
                    out.println(game.headlessGame(100, i, 6));
                }catch (IOException e) {
                }
            }
        }
    }
    
    public void runDiceMax(int diceMax) {
        for(int i = 10; i< diceMax; i+=placeOfNumber(i))
        {
            for(int j = 0; j<5; j++) {
                try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("outputDice.csv", true)))) {
                    Game game = new Game();
                    out.println(game.headlessGame(10, 5, i));
                }catch (IOException e) {
                }
            }
        }
    }
    
    public void runBoardSize (int maxSize) {
        for(int i = 10; i< maxSize; i+=placeOfNumber(i))
        {
            for(int j = 0; j<5; j++) {
                try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("outputBoardSize.csv", true)))) {
                    Game game = new Game();
                    out.println(game.headlessGame(i, 10, 6));
                }catch (IOException e) {
                }
            }
        }
    }
    
    public double placeOfNumber(int number) {
        return Math.pow(10, Math.floor(Math.log10(number)));
    }    
}
