package ritmath;

/**
 * The cosine function from trigonometry
 *
 * @author Key'Mon Jenkins
 */
public class Cosine extends AbstractFunction {
    MathFunction[] cos;

    /**
     * Create a cosine function
     * @param arg the argument function
     */
    public Cosine(MathFunction... arg){
        super(arg);
        cos = super.terms;
    }

    /**
     * Display the function as a human-readable string with no new-line
     * characters. (This forces subclasses to implement toString)
     * @return the textual representation of this function
     */
    public String toString() {
        String cosStr = "cos( ";
        for (int i = 0; i < super.terms.length; i++) {
            if(super.get(i).isConstant()){
                return "" + Math.cos(super.get(i).evaluate(0));
            }
            cosStr += super.get(i).toString();
        }
        return cosStr + " )";
    }

    /**
     * Compute the value of this function
     * @param x the value of the independent variable
     * @return the evaluation of the function at the given x
     */
    public double evaluate(double x) {
        return Math.cos(cos[0].evaluate(x));
    }

    /**
     * Create a new function that is the derivative of this one
     * @return df(x)/dx
     */
    public MathFunction derivative() {
        MathFunction[] temp = new MathFunction[cos.length+2];
        MathFunction[] finalDeriv = new MathFunction[cos.length];
        for(int i = 0; i < cos.length; i++){
            for(int j = 0; j < cos.length; j++){
                if(i == j){
                    finalDeriv[j] = super.get(i).derivative();
                }else{
                    finalDeriv[j] = super.get(j);
                }
            }
            temp[i+1] = (FunctionFactory.product(finalDeriv));
        }
        temp[0] = new Sine(cos);
        temp[cos.length+1] = new Constant(-1);
        return new Product(temp);
    }
}
