package Expressions;

public class SqrtExpression extends ValueExpression {

	public SqrtExpression(Expression expression){
		setExpression(expression);
	}
	
	private Expression expression;
	
	public void setExpression(Expression expression){
		this.expression = expression;
	}
	
	public Expression getExpression(){
		return this.expression;
	}
	
	@Override
	public double evaluate(){
		if (getExpression() instanceof ValueExpression){
			return Math.sqrt(((ValueExpression) getExpression()).evaluate());
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to a value");
		}
	}
}
