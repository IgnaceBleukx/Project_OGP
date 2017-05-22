package Expressions;

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
	public double evaluate() throws ModelException{
		if (getExpression() instanceof ValueExpression){
			try {
				return Math.sqrt(((ValueExpression) getExpression()).evaluate());
			} catch (ModelException e) {
				throw new ModelException("ModelException in SqrtExpression");
			}
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to a value");
		}
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
