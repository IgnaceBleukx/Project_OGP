package Expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class MultiplicationExpression extends ValueExpression {

	public MultiplicationExpression(Expression ex1, Expression ex2, SourceLocation location){
		setEx1(ex1);
		setEx2(ex2);
		setSourceLocation(location);
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
	private SourceLocation sourceLocation;
	
	@Override
	public double evaluate() throws ModelException{
		if (getEx1() instanceof ValueExpression && getEx2() instanceof ValueExpression){
			try{
				return ((ValueExpression) getEx1()).evaluate() * ((ValueExpression) getEx2()).evaluate();
			}catch (ModelException e){
				throw new ModelException("ModelException in MultiplicationExpression");
			}
		}
		else{
			throw new IllegalArgumentException("One of the 2 expressions does not evaluate to a value");
		}
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
