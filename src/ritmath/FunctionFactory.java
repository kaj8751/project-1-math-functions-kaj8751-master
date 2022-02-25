package ritmath;

/**
 *Creation methods for MathFunction classes. Note: Many of these factory methods
 * will check if what they have built is actually a constant — a function with no
 * references to x. If that is the case the function will be evaluated and that
 * value will be returned as an instance of Constant.
 *
 * @author Key'Mon Jenkins
 */
public class FunctionFactory {

    private static Variable var;

    public FunctionFactory(){

    }

    /**
     * Makes a constant object
     * @param value the constant value
     * @return a new instance of constant representing the given value
     */
    public static MathFunction constant(double value){
        return new Constant(value);
    }

    /**
     * The object representing all functions' independent variable
     * @return the single instance of the variable class
     */
    public static MathFunction x(){
        if(var == null){
            var = new Variable();
        }
        return var;
    }

    /**
     * Makes a Sum object
     * @param args the terms that will be added together
     * @return a Sum instance, or something equivalent if constants are
     * involved. If no terms, return the constant 0
     */
    public static MathFunction sum(MathFunction... args){
        MathFunction sum  = new Sum(args);
        if(sum.isConstant()){
            return new Constant(sum.evaluate(0));
        }
        return new Sum(args);
    }

    /**
     * Makes a Product object
     * @param args the terms that will be multiplied together
     * @return a Product instance, or something equivalent if constants
     * are involved. If no terms, return the constant 1.
     */
    public static MathFunction product(MathFunction... args){
        MathFunction product = new Product(args);
        if(product.isConstant()){
            return new Constant(product.evaluate(0));
        }
        return new Product(args);
    }

    /**
     * Makes a Sine object
     * @param args the function that will be the sine's argument
     * @return a Sine instance, or something equivalent if constants are involved
     */
    public static MathFunction sine(MathFunction... args){
        return new Sine(args);
    }

    /**
     * Makes a Cosine object
     * @param args the function that will be the cosine's argument
     * @return a Cosine instance, or something equivalent if constants are involved
     */
    public static MathFunction cosine(MathFunction... args){
        return new Cosine(args);
    }
}
