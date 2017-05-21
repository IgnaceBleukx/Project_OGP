package Expressions;

public class BooleanExpression extends Expression {

	public boolean evaluate(){
		throw new IllegalStateException("The method 'evaluate' should be overwritten by all subclasses of BooleanExpression");
	}
}
