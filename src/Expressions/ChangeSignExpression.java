package Expressions;

public class ChangeSignExpression extends BooleanExpression {

	public ChangeSignExpression(Expression expression){
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
	public boolean evaluate(){
		if (getExpression() instanceof BooleanExpression){
			return !((BooleanExpression) getExpression()).evaluate();
		}
		else{
			throw new IllegalArgumentException("The expression is not of the type 'BooleanExpression'");
		}
	}
}
