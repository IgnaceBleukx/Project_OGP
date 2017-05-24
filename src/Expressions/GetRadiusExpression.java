package Expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class GetRadiusExpression extends ValueExpression {
	
	public GetRadiusExpression(Expression expression, SourceLocation location) {
		setExpression(expression);
		setSourceLocation(sourceLocation);
		
	}
	
	private SourceLocation sourceLocation;

	private Expression expression;

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
	public double evaluate() throws ModelException{
		passInformation(getExpression());
		try{
			if (getExpression() instanceof EntityExpression){
					return ((EntityExpression)getExpression()).evaluate().getRadius();
			}	
			else{
				throw new ModelException("The expression does not evaluate to an entity");
			}
		}catch(NullPointerException e){
			throw new ModelException("The expression evaluates to null");
		}
	}
}
