package Expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class GetVYExpression extends ValueExpression {

	public GetVYExpression(Expression expression, SourceLocation sourcelocation){
		setExpression(expression);
		setSourceLocation(sourceLocation);
	}
	
	private SourceLocation sourceLocation;
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	private Expression expression;
	
	@Override
	public double evaluate() throws ModelException{
		getExpression().setProgram(getProgram());
		if (expression instanceof EntityExpression){
			return ((EntityExpression) expression).evaluate().getVelocity()[1];
		}
		else{
			throw new ModelException("The expression does not evaluate to an entity");
		}
	}
}
