package Expressions;

public class GetVYExpression extends ValueExpression {

	public GetVYExpression(Expression expression){
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
			return ((EntityExpression) expression).evaluate().getVelocity()[1];
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to an entity");
		}
	}
}
