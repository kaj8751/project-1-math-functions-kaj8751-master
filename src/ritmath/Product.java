package ritmath;

import java.util.ArrayList;

/**
 * A function representing the product of factors
 *
 * @author Key'Mon Jenkins
 */
public class Product extends AbstractFunction{


    /**
     * Create a product from any array of MathFunction instances
     * @param factors an array of MathFunction factors to be multiplied
     */
    protected Product(MathFunction... factors) {
        super(factors);
        this.normalize();
    }

    /**
     * Combine the constant factors to one constant
     */
    public void normalize(){
        double constants = 1;
        ArrayList<MathFunction> tempProd = new ArrayList<>();
        ArrayList<MathFunction> tempTemp = new ArrayList<>();
        for(MathFunction term : super.terms){
            if (term.isConstant()){
                constants = constants * term.evaluate(0);
            }
            else{
                tempProd.add(term);
                tempTemp.add(term);
            }
        }
        if(constants == 0){
            tempProd.removeAll(tempTemp);
            tempProd.add(new Constant(0));
        }
        else if(constants != 1){
            tempProd.add(new Constant(constants));
        }
        MathFunction[] temp = new MathFunction[tempProd.size()];
        for(int i = 0; i < tempProd.size(); i++){
            temp[i] = tempProd.get(i);
        }
        super.terms = temp;
    }

    /**
     * Return a string of the product in infix notation, with parentheses
     * @return the textual representation of this function
     */
    public String toString() {
        this.normalize();
        String prod = "";
        prod += "( ";
        if(super.numChildren() == 1){
            prod = "";
            return prod + super.get(0);
        }
        else{
            for(int i = 0; i < super.numChildren(); i++){
                prod += super.get(i);
                if(i + 1 < super.numChildren()){
                    prod += " * ";
                }
            }
        }
        return prod + " )";
    }

    /**
     * Computes the value of this function
     * @param x the value of the independent variable
     * @return the evaluation of the function at the given x
     */
    public double evaluate(double x) {
        double evaluatedProd = 1;
        for(MathFunction temp: super.terms){
            evaluatedProd = evaluatedProd * temp.evaluate(x);
        }
        return evaluatedProd;
    }

    /**
     *Create a new function that is the derivative of this one. The derivative
     * of a product of TWO factors is the sum of the first factor times the
     * derivative of the second, plus the derivative of the first factor times
     * the second. This is done recursively to handle more than two factors.
     * @return d(fx)/dx
     */
    public MathFunction derivative() {
        MathFunction[] temp = new MathFunction[super.numChildren() - 1];
        if(super.numChildren() == 1){
            return super.get(0).derivative();
        }
        for(int i = 1; i < super.numChildren(); i++){
            temp[i-1] = super.get(i);
        }
        return new Sum(new Product(super.get(0), new Product(temp).derivative()),
                new Product(new Product(temp), super.get(0).derivative()));
    }
}