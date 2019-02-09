package utilities;

import java.util.Random;

//to be implemented properly...
public class PseudoRandomGenerator {

    private Random random;

    public PseudoRandomGenerator(){
        this.random = new Random();
    }

    public double normalDouble(){
        return this.random.nextGaussian();
    }
    public double normalDouble(double mean, double deviation){
        return this.random.nextGaussian() * deviation + mean;
    }
    public int normalInt(double mean, double deviation){
        return (int)normalDouble(mean, deviation);
    }

    public double nextDouble(){
        return this.random.nextDouble();
    }
    public double nextDouble(double limit){
        return this.random.nextDouble() * limit;
    }
}
