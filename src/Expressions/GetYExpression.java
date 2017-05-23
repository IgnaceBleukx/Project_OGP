package Expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class GetYExpression extends ValueExpression {

	public GetYExpression(Expression expression, SourceLocation sourceLocation){
		setExpression(expression);
		setSourceLocation(sourceLocation);
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
		if (getExpression() instanceof EntityExpression){
			passInformation(getExpression());
			if (((EntityExpression) getExpression()).evaluate() != null){
			return ((EntityExpression) getExpression()).evaluate().getPosition()[1];
			}
			else{
				throw new ModelException("The expression evaluates nu null");
			}
		}
		else{
			throw new ModelException("The expression does not evaluate to an entity");
		}
	}
}
