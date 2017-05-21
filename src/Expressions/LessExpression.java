package Expressions;

public class LessExpression extends BooleanExpression {

	public LessExpression(Expression ex1, Expression ex2){
		setEx1(ex1);
		setEx2(ex2);
	}
	
	public Expression getEx1() {
		return ex1;
	}
	public void setEx1(Expression ex1) {
		this.ex1 = ex1;
	}
	public Expression getEx2() {
		return ex2;
	}
	public void setEx2(Expression ex2) {
		this.ex2 = ex2;
	}
	
	private Expression ex1;
	private Expression ex2;
	
	@Override
	public boolean evaluate(){
		if (getEx1() instanceof ValueExpression && getEx2() instanceof ValueExpression){
			return ((ValueExpression) getEx1()).evaluate() < ((ValueExpression) getEx2()).evaluate();
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to a value");
		}
	}
}
