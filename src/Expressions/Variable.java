package Expressions;

public class Variable extends ValueExpression {

	public Variable (Expression expression, String name){
		setName(name);
		setExpression(expression);
	}
	
	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	private Expression expression;
	private String Name;
}
