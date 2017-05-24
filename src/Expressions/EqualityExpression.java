package Expressions;

import Statements.BreakException;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class EqualityExpression extends BooleanExpression {

	public EqualityExpression(Expression ex1, Expression ex2, SourceLocation location){
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
		passInformation(getEx1());
		passInformation(getEx2());
		if (ex1 instanceof EntityExpression && ex2 instanceof EntityExpression){
			return ((EntityExpression) getEx1()).evaluate().equals(((EntityExpression) getEx2()).evaluate());
		}
		if (ex1 instanceof ValueExpression && getEx2() instanceof ValueExpression){
			try{
				return ((ValueExpression) getEx1()).evaluate() == (((ValueExpression) getEx2()).evaluate());
			}catch (ModelException e){
				throw new ModelException("ModelException in EqualityExpression");
			}
		}
		if (getEx1() instanceof BooleanExpression && getEx2() instanceof BooleanExpression){
			return ((BooleanExpression) getEx1()).evaluate() == ((BooleanExpression) getEx2()).evaluate();
		}
		else{
			return false;
		}
	}
}
