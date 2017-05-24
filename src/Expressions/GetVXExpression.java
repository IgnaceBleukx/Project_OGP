package Expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class GetVXExpression extends ValueExpression {

	public GetVXExpression(Expression expression, SourceLocation location){
		setExpression(expression);
		setSourceLocation(location);
		
	}
	
	private SourceLocation sourceLocation;
	
	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	private Expression expression;

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public double evaluate() throws ModelException{
		passInformation(getExpression());
			if (getExpression() instanceof EntityExpression){
				if (((EntityExpression) getExpression()).evaluate() != null){
				return ((EntityExpression) getExpression()).evaluate().getVelocity()[0];
				}
				else{
					throw new ModelException("The expression evaluates to null");
				}
			}
			throw new ModelException("The expression does not evaluate to an entity");
	}
	
}
