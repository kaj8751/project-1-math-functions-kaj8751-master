package ritmath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * A set of implementations for methods defined in MathFunction
 *
 * @author Key'Mon Jenkins
 */
public abstract class AbstractFunction implements MathFunction, Iterable<MathFunction> {

    protected MathFunction[] terms;

    /**
     * Create the MathFunction node in the function tree
     *
     * @param children the "parameters" of the function - its parts
     */
    protected AbstractFunction(MathFunction... children) {
        terms = children;
    }

    /**
     * Get an iterator for the child terms of this function.
     * Providing this function means that methods in the
     * subclass can write a simpler for loop
     *
     * @return the internal private child collection's iterator
     */
    @Override
    public Iterator<MathFunction> iterator() {
//        for(term : terms){
        return null;
    }

    /**
     * (Re)set the child terms of this function. This method throws
     * away all existing children and puts in the ones given
     *
     * @param children the new child terms of this function
     */
    protected void setChildren(MathFunction[] children) {

    }

    /**
     * How many child terms does this function have?
     *
     * @return the size of the child collection set by the call
     * to AbstractFunction(MathFunction...) or setChildren(MathFunction[])
     */
    protected int numChildren() {
        return terms.length;
    }

    /**
     * Fetch one of the children of this function
     *
     * @param c index of desired child
     * @return child #c from the list of children provided in the constructor
     * @rit.pre c is at least 0 and less than the number of children
     */
    protected MathFunction get(int c) {
        ArrayList<MathFunction> tempTerms = new ArrayList<>(Arrays.asList(terms));
        return tempTerms.get(c);
    }

    /**
     * Is this function just a constant value?
     *
     * @return true iff all of its children are constant
     */
    @Override
    public boolean isConstant() {
        for (MathFunction term : terms) {
            if (!term.isConstant()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Display the function as a human-readable string with no new-line
     * characters. (This forces subclasses to implement toString)
     *
     * @return the textual representation of this function
     */
    @Override
    public abstract String toString();

    /**
     * Compute the integral of this function over its independent
     * variable and the given range using the sum-of-trapezoids
     * technique. Subclasses should override this method if a
     * closed-form solution is available
     *
     * @param lower    the starting point of the integral's interval
     * @param upper    the ending point of the integral's interval
     * @param accuracy the number of sections into which the interval
     *                 should be divided for the calculation (may not
     *                 be used if the function has a closed form integral)
     * @return the integral result as a double
     */
    public double integral(double lower, double upper, int accuracy) {
        double intergCount = 1;
        double increment = (upper - lower) / accuracy;
        boolean first = true;
        if(terms.length == 1){
            intergCount = this.get(0).integral(lower, upper, accuracy);
        }else {
            for (double i = lower; i <= upper; i += increment) {
                if (first) {
                    intergCount += this.evaluate(i);
                    first = false;
                } else if (i == upper) {
                    intergCount += this.evaluate(i);
                } else {
                    intergCount += 2 * this.evaluate(i);
                }
            }
            intergCount = increment/2 * intergCount;
        }
        return intergCount;
    }
}

