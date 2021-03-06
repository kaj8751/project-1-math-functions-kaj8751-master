package ritmath;

import java.util.ArrayList;

/**
 * A function representing the sum of terms
 *
 * @author Key'Mon Jenkins
 */
public class Sum extends AbstractFunction{


    /**
     * Create a sum from any array of MathFunction instances
     * @param terms an array of MathFunction terms to be summed
     */
    public Sum(MathFunction... terms){
        super(terms);
        this.normalize();
    }

    /**
     * Combine the constant terms to be one constant
     */
    protected void normalize(){
        int constants = 0;
        ArrayList<MathFunction> tempSum = new ArrayList<>();
        for (MathFunction term : super.terms) {
            if(term.isConstant()){
                constants+=term.evaluate(0);
            }
            else{
                tempSum.add(term);
            }
        }
        if (constants != 0) {
            tempSum.add(new Constant(constants));
        }
        MathFunction[] temp = new MathFunction[tempSum.size()];
        for(int i = 0; i < tempSum.size(); i++){
            temp[i] = tempSum.get(i);
        }
        super.setChildren(temp);
    }

    /**
     * Create a new function that is the derivative of this one.
     * The derivative of a sum of terms is the sum of the
     * derivatives of the terms
     * @return df(x)/dx
     */
    public MathFunction derivative(){
        MathFunction deriv;
        int addConst = 0;
        ArrayList<MathFunction> tempSums = new ArrayList<>();
        for(MathFunction temp: super.terms) {
            deriv = temp.derivative();
            if (deriv.isConstant()) {
                addConst += deriv.evaluate(0);
            } else {
                tempSums.add(deriv);
            }
        }
        tempSums.add(new Constant(addConst));
        MathFunction[] finalDeriv = tempSums.toArray(new MathFunction[0]);
        return FunctionFactory.sum(finalDeriv);
    }

    /**
     * Compute the value of this function
     * @param x the value of the independent variable
     * @return the evaluation of the function at the given x
     */
    public double evaluate(double x){
        double evaluatedSums = 0;
        for(MathFunction temp: super.terms){
            evaluatedSums += temp.evaluate(x);
        }
        return evaluatedSums;
    }

    /**
     * The integral of a sum of terms is the sum of integrals of the terms
     * @param lower the starting point of the integral's interval
     * @param upper the ending point of the integral's interval
     * @param accuracy the number of sections into which the interval
     *                 should be divided for the calculation (may not
     *                 be used if the function has a closed form integral)
     * @return the computed integral over the given bounds
     */
    @Override
    public double integral(double lower, double upper, int accuracy){
        double intergCount = 0;
        for(MathFunction term : super.terms) {
            intergCount += term.integral(lower, upper, accuracy);
        }
        return intergCount;
    }

    /**
     * Return a string of the sum in infix notation, with parentheses
     * @return the textual representation of this function
     */
    public String toString(){
        String add = "";
        add += "( ";
        int i = 0;
        if(super.numChildren() == 1){
            add = "";
            return add + super.get(0);
        }
        else {
            for (MathFunction term : super.terms) {
                add += super.get(i);
                i += 1;
                if(i  < super.numChildren()){
                    add += " + ";
                }
            }
        }
        return add + " )";
    }

}
