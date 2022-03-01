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
        MathFunction[] temp = new MathFunction[sin.length+2];
        MathFunction[] finalDeriv = new MathFunction[sin.length];
        for(int i = 0; i < sin.length; i++){
            for(int j = 0; j < sin.length; j++){
                if(i == j){
                    finalDeriv[j] = super.get(i).derivative();
                }else{
                    finalDeriv[j] = super.get(j);
                }
            }
            temp[i+1] = (FunctionFactory.product(finalDeriv));
        }
        temp[0] = new Cosine(sin);
        temp[sin.length+1] = new Constant(1);
        return new Product(temp);
    }
}
