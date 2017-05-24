package Expressions;

import Statements.BreakException;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class ChangeSignExpression extends ValueExpression {

	public ChangeSignExpression(Expression expression, SourceLocation sourceLocation){
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
	public double evaluate() throws ModelException, BreakException{
		if (getExpression() instanceof ValueExpression){
			passInformation(getExpression());
			try {
				return -((ValueExpression) getExpression()).evaluate();
			} catch (ModelException e) {
				throw new ModelException("ModelException in ChangeSignExpression");
			}
		}
		else{
			throw new ModelException("The expression is not of the type 'ValueExpression'");
		}
	}
}
