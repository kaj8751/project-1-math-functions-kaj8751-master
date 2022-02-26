package ritmath;

/**
 * A function that always returns the same value
 *
 * @author Key'Mon Jenkins
 */
public class Constant extends AbstractFunction{
    /** value of the constant */
    double constantValue;

    /**
     * Create a constant function
     * @param value the value to which the function always evaluates
     */
    public Constant(double value){
        super();
        constantValue = value;
    }

    /**
     * Is the function just a constant value; yes
     * @return true iff all of its children are constant
     */
    @Override
    public boolean isConstant(){
        return true;
    }

    /**
     * Create a new function that is the derivative of this one
     * @return 0
     */
    public MathFunction derivative(){
        return new Constant(0);
    }

    /**
     * Compute the value of this function. Return the value of this constant
     * @param x the value of the dependent variable
     * @return the evaluation of the function at the given x
     */
    public double evaluate(double x){
        return constantValue;
    }

    /**
     * Compute the integral of this function over its independent variable and
     * the given range using the sum-of-trapezoids technique. Subclasses
     * should override this method if a closed-form solution is available.
     * Basically the area of a rectangle
     * @param lower the starting point of the integral's interval
     * @param upper the ending point of the integral's interval
     * @param accuracy the number of sections into which the interval
     *                 should be divided for the calculation (may not
     *                 be used if the function has a closed form integral)
     * @return the integral result as a double
     */
    @Override
    public double integral(double lower, double upper, int accuracy){
        double intergCount = 0;
        double increment = (upper - lower) / accuracy;
        for(double i = lower; i < upper; i += increment) {
            intergCount += 2 * constantValue;
        }
        if(intergCount < 0){
            intergCount = Math.ceil(increment/2 * intergCount);
        }else {
            intergCount = Math.floor(increment / 2 * intergCount);
        }
        return intergCount;
    }

    /**
     * Return the string value of the constant
     * @return the textual representation of this function
     */
    public String toString(){
        return "" + constantValue;
    }


}
