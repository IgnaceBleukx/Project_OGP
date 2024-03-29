package Expressions;

import Statements.BreakException;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class SqrtExpression extends ValueExpression {

	public SqrtExpression(Expression expression, SourceLocation location){
		setExpression(expression);
		setSourceLocation(location);
	}
	
	private Expression expression;
	private SourceLocation sourceLocation;
	
	public void setExpression(Expression expression){
		this.expression = expression;
	}
	
	public Expression getExpression(){
		return this.expression;
	}
	
	@Override
	public double evaluate() throws ModelException, BreakException{
		if (getExpression() instanceof ValueExpression){
			passInformation(getExpression());
			return Math.sqrt(((ValueExpression) getExpression()).evaluate());
		}
		else{
			throw new ModelException ("The expression does not evaluate to a value");
		}
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
