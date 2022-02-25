package ritmath;

import java.util.ArrayList;

/**
 * A function representing the product of factors
 *
 * @author Key'Mon Jenkins
 */
public class Product extends AbstractFunction{
    MathFunction[] product;

    /**
     * Create a product from any array of MathFunction instances
     * @param factors an array of MathFunction factors to be multiplied
     */
    protected Product(MathFunction... factors) {
        super(factors);
        product = super.terms;
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
            product = tempProd.toArray(new MathFunction[0]);
        }
        else if(constants == 1){
            product = tempProd.toArray(new MathFunction[0]);
        }
        else{
            tempProd.add(new Constant(constants));
            product = tempProd.toArray(new MathFunction[0]);
        }
    }

    /**
     * Return a string of the product in infix notation, with parentheses
     * @return the textual representation of this function
     */
    public String toString() {
        super.terms = product;
        String prod = "";
        prod += "( ";
        if(super.terms.length == 1){
            prod = "";
            return prod + super.get(0);
        }
        else{
            for(int i = 0; i < super.terms.length; i++){
                prod += super.get(i);
                if(i + 1 < super.terms.length){
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
        MathFunction[] tempDeriv = new MathFunction[product.length];
        MathFunction[] temp = new MathFunction[product.length];
        if(product.length == 1 && super.get(0).isConstant()){
            return FunctionFactory.constant(1);
        }
        for(int i = 0; i < product.length; i++){
            for(int j = 0; j < product.length; j++){
                if(i == j){
                    temp[j] = super.get(i).derivative();
                }else{
                    temp[j] = super.get(j);
                }
            }
            tempDeriv[i] = (FunctionFactory.product(temp));
        }
        return new Sum(tempDeriv);
    }
}
