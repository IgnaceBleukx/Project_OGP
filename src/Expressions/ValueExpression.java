package Expressions;

public class ValueExpression extends Expression {

	public double evaluate(){
		throw new IllegalStateException("The method 'evaluate' should be overwritten by all subclasses of ValueExpression");
	}
}
