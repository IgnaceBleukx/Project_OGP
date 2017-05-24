package Statements;

import Expressions.Expression;
import Expressions.ValueExpression;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

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
	public double execute() throws ModelException, BreakException{
		if (getFunction() == null){
			throw new ModelException("The returnstatement does not occur in a functionbody");
		}
		passInformation(getValue());
		return ((ValueExpression) getValue()).evaluate();
	}
}
