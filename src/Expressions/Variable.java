package Expressions;

public class Variable {

	public Variable(Expression expression, String name){
		this.setExpression(expression);
		this.setName(name);
	}
	
	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Expression expression;
	private String name;

	
}
