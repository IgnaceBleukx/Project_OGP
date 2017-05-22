package Statements;

import Expressions.Expression;
import Expressions.ValueExpression;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends ValueStatement {

	public ReturnStatement(Expression value, SourceLocation sourceLocation){
		setValue(value);
		setSourceLocation(sourceLocation);
	}
	
	private Expression value;
	private SourceLocation sourceLocation;
	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public double execute(){
		if (getValue() instanceof ValueExpression){
			return ((ValueExpression) getValue()).evaluate();
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to a value and thus cannot be returned");
		}
	}
}
