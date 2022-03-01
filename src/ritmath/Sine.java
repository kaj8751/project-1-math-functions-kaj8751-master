package ritmath;

/**
 * The sine function from trigonometry
 *
 * @author Key'Mon Jenkins
 */
public class Sine extends AbstractFunction{
    MathFunction[] sin;

    /**
     * Create a sine function
     * @param arg the argument function
     */
    public Sine(MathFunction... arg){
        super(arg);
        sin = super.terms;
    }

    /**
     * Display the function as a human-readable string with no new-line
     * characters. (This forces subclasses to implement toString)
     * @return the textual representation of this function
     */
    public String toString() {
        String sinStr = "sin( ";
        for (int i = 0; i < super.terms.length; i++) {
            if(super.get(i).isConstant()){
                return "" + Math.sin(super.get(i).evaluate(0));
            }
            sinStr += super.get(i).toString();
        }
        return sinStr + " )";
    }

    /**
     * Compute the value of this function
     * @param x the value of the independent variable
     * @return the evaluation of the function at the given x
     */
    public double evaluate(double x) {
        return Math.sin(sin[0].evaluate(x));
    }

    /**
     * Create a new function that is the derivative of this one
     * @return df(x)/dx
     */
    public MathFunction derivative() {
        return FunctionFactory.product(new Cosine(sin), sin[0].derivative());
    }
}
