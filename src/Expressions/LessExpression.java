package Expressions;

import Statements.BreakException;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class LessExpression extends BooleanExpression {

	public LessExpression(Expression ex1, Expression ex2, SourceLocation location){
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
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}


	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	

	@Override
	public boolean evaluate() throws ModelException, BreakException{
		if (getEx1() instanceof ValueExpression && getEx2() instanceof ValueExpression){
			passInformation(getEx1());
			passInformation(getEx2());
			return ((ValueExpression) getEx1()).evaluate() < ((ValueExpression) getEx2()).evaluate();
		}
		else{
			throw new ModelException("The expression does not evaluate to a value");
		}
	}
}
