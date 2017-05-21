package Expressions;

public class GetVXExpression extends ValueExpression {

	public GetVXExpression(Expression expression){
		setExpression(expression);
	}
	
	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	private Expression expression;
	
	@Override
	public double evaluate(){
		if (expression instanceof EntityExpression){
			return ((EntityExpression) expression).evaluate().getVelocity()[0];
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to an entity");
		}
	}
	
}
