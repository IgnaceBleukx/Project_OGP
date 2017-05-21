package Expressions;

public class GetRadiusExpression extends ValueExpression {

	
	
	public GetRadiusExpression(Expression expression) {
		setExpression(expression);
	}

	private Expression expression;

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public double evaluate(){
		if (expression instanceof EntityExpression){
			return ((EntityExpression)getExpression()).evaluate().getRadius();
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to an entity");
		}
	}
}
