package ritmath;

/**
 * The set of operations that all functions in
 * this system must support
 *
 * @author Key'Mon Jenkins
 */
public interface MathFunction {


    /**
     * Compute the value of this function
     * @param x the value of the independent variable
     * @return the evaluation of the function at the given x
     */
    double evaluate(double x);

    /**
     * Is this function a constant value? Does it always evaluate
     * to the same thing independent of x? This predicate is included
     * so that expressions can be simplified by combining or eliminating
     * constants
     * @return false
     */
    boolean isConstant();

    /**
     * create a new function that is the derivative of this one
     * @return df(x)/dx
     */
    MathFunction derivative();

    /**
     * Compute the integral of this function over its independent
     * variable and the given range
     * @param lower the starting point of the integral's interval
     * @param upper the ending point of the integral's interval
     * @param accuracy the number of sections into which the interval
     *                 should be divided for the calculation (may not
     *                 be used if the function has a closed form integral)
     * @return the integral result as a double
     */
    double integral(double lower, double upper, int accuracy);

}
