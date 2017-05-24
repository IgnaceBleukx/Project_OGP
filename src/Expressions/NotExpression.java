package Expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class NotExpression extends BooleanExpression {

	public NotExpression(Expression expression, SourceLocation sourceLocation){
		setExpression(expression);
		setSourceLocation(sourceLocation);
	}
	
	private Expression expression;
	private SourceLocation sourceLocation;
	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	@Override
	public boolean evaluate() throws ModelException{
		if (getExpression() instanceof BooleanExpression){
			try {
				passInformation(getExpression());
				return !((BooleanExpression) getExpression()).evaluate();
			} catch (ModelException e) {
				throw new ModelException("ModelException in NotExpression");
			}
		}
		else{
			throw new ModelException("The expression does not evaluate to a boolean");
		}
	}
}
