package Expressions;

public class EqualityExpression extends BooleanExpression {

	public EqualityExpression(Expression ex1, Expression ex2){
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
		if (ex1 instanceof EntityExpression && ex2 instanceof EntityExpression){
			return ((EntityExpression) ex1).evaluate().equals(((EntityExpression) ex2).evaluate());
		}
		else{
			throw new IllegalArgumentException("One of the given expressions does not evaluate to an entity");
		}
	}
}
