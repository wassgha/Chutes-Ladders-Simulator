import java.util.Random;

/** 
 Generate pseudo-random floating point values, with an 
 approximately Gaussian (normal) distribution.

 Many physical measurements have an approximately Gaussian 
 distribution; this provides a way of simulating such values. 
 
 Source :
 http://javapractices.com/topic/TopicAction.do?Id=62
*/
public final class RandomGaussian {
  private Random fRandom;
  
  public RandomGaussian() {
    // Sleep for 1 millisecond to get a new random seed
    try {
        Thread.sleep(1);
    } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    } 
    // Initialize the generator
    fRandom = new Random(System.currentTimeMillis());
  }
  
  public int getGaussian(double aMean, double aVariance){
    return (int)Math.floor(aMean + fRandom.nextGaussian() * aVariance);
  }
  
  public double getGaussianDouble(double aMean, double aVariance){
    return aMean + fRandom.nextGaussian() * aVariance;
  }
  
  public int getRandomBetween(int min, int max){
    return (int)(fRandom.nextInt(max - min + 1) + min);
  }
} 
