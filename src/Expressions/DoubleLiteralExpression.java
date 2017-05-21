package Expressions;

public class DoubleLiteralExpression extends ValueExpression {

	public DoubleLiteralExpression(double value){
		setValue(value);
	}
	
	double value;
	
	public void setValue(double value){
		this.value = value;
	}
	
	public double getValue(){
		return this.value;
	}
	
	@Override
	public double evaluate(){
		return getValue();
	}
}
