package n2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 26.05.2017.
 */
public class Square {
    private static final Logger LOGGER = LoggerFactory.getLogger(Square.class);

    public static void main(String[] args) {
        LOGGER.info("Hello world!");
    }
    public static double [] solve (double a, double b, double c){
        double D =(b*b - 4*a*c);
        if(D >0 ){
            double x1 = ((-Math.sqrt(D) - b) / 2*a);
            double x2 = ((Math.sqrt(D) - b) / 2*a);
            return new double[]{x1,x2};
        }else if(D == 0){
            double x1 = ((- b) / 2*a);
            return new double[]{x1};
        }else{
            return new double[0];
        }
    }
}
