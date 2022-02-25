package ritmath;

/**
 * A singleton class used to represent the independent variable "x"
 *
 * @author Key'Mon Jenkins
 */
public class Variable extends AbstractFunction{

    private static final String x = "x";

    /**
     * Create the basic independent variable for all functions
     */
    protected Variable() {
        super();

    }

    /**
     * The variable x is not constant
     * @return false
     */
    @Override
    public boolean isConstant(){
        return false;
    }

    /**
     * Create a new function that is the derivative of this one.
     * The derivative of x wrt x is 1.
     * @return df(x)/dx
     */
    public MathFunction derivative() {
        return new Constant(1);
    }

    /**
     * Compute the value of this function. Variable evaluate their own values
     * @param x the value of the independent variable
     * @return the evaluation of the function at the given x
     */
    public double evaluate(double x) {
        return x;
    }

    /**
     * The integral of xdx is x^2/2 (special case of polynomial)
     * @param lower the starting point of the integral's interval
     * @param upper the ending point of the integral's interval
     * @param accuracy the number of sections into which the interval
     *                 should be divided for the calculation (may not
     *                 be used if the function has a closed form integral)
     * @return the value of the integral over the given bounds
     */
    public double integral(double lower, double upper, int accuracy){
        return 0;
    }

    /**
     * Return the variable's name
     * @return the textual representation of this function
     */
    public String toString() {
        return x;
    }

}
