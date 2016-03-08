/*
 * Inverse of the Normal Distribution
 * 
 * Adapted from onlinestatbook.com/2/calculators/inverse_normal_dist.html
 * (Converted from Javascript to Java)
 * 
 * This class calculates the range for which the Normal Distribution function (Gaussian
 * Distribution) returns values with a specified probability. For example, for a
 * probability of .5 (50%) the inverse normal distribution is somewhere between
 * -.67 and .67. This means that if you use a mean of 0 and a standard deviation of 1
 * then the values between -.67 and .67 will account of 50% of all values generated.
 * 
 */

public class InverseNormalDist
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class InverseNormalDist
     */
    public InverseNormalDist()
    {

    }
    
    /**
     * Method between, returns the range corresponding the probability given For example, for a
     * probability of .5 (50%) the inverse normal distribution is somewhere between
     * -.67 and .67. This means that if you use a mean of 0 and a standard deviation of 1
     * then the values between -.67 and .67 will account of 50% of all values generated.
     * 
     *
     * @param prob the probability
     * @param mean the mean
     * @param sd the standard deviation
     * @return the range
     */
    public double[] between(double prob, double mean, double sd) {
        double p2=prob/2;
		double x1=inv(.5-p2);
		double ll=x1;
		double ul=-x1;
		ll=mean+sd*ll;
		ul=mean+sd*ul;
		double[] result = new double[2];
		result[0] = ll;
		result[1] = ul;
		return result;
    }
    
    /**
     * Method outside, returns the range outside of the curve corresponding the probability given.
     * For example, for a probability of .1 (10%) the inverse normal distribution is somewhere between
     * -1.645 and . This means that if you use a mean of 0 and a standard deviation of 1
     * then the values below -1.645 and above 1.645 will account of 10% of all values generated.
     * 
     *
     * @param prob the probability
     * @param mean the mean
     * @param sd the standard deviation
     * @return the range
     */
    public double[] outside(double prob, double mean, double sd) {
        double p2=prob/2;
		double x1=inv(p2);
		double ll=x1;
		double ul=-x1;
		ll=mean+sd*ll;
		ul=mean+sd*ul;
		double[] result = new double[2];
		result[0] = ll;
		result[1] = ul;
		return result;
    }
    
    private double inv (double p) {
    	double t,v,theSign;
    	if (p>=1){
    		return 7;
    	}
    	else if (p<=0) {
    		return -7;
    	}
    	if (p<.5) {
    		t=p;
    		theSign=-1;
    	}
    	else
    	{
    		t=1-p;
    		theSign=1;
    	}
    	
    	v = Math.sqrt(-2.0 * Math.log(t));
    	double x = 2.515517 + (v * ( 0.802853 + v * 0.010328));
    	double y = 1 + (v * (1.432788 + v * (0.189269  + v * 0.001308)));
    	double Q = theSign *(v - (x /y));
    	return Q;
    }

    public double prob(double z){
        boolean flag = false;
    	if (z<-7) {return 0.0;}
    	if (z>7) {return 1.0;}
    
    
    	if (z<0.0) {flag= true;}
    	else
    		{flag = false;}
    
    	z = Math.abs(z);
    	double b=0.0;
    	double s=Math.sqrt(2)/3*z;
    	double HH=.5;
    	for (int i=0;i<12;i++) {
    		double a = Math.exp(-HH*HH/9)*Math.sin(HH*s)/HH;
    		b=b+a;
    		HH=HH+1.0;
    	}
    	double p= .5-b/Math.PI;
    	if (!flag) {p=1.0-p;}
    	return p;
    }

}
