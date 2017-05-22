package Statements;

import Expressions.BooleanExpression;
import Expressions.Expression;
import Expressions.ValueExpression;
import asteroids.part3.programs.SourceLocation;;

public class IfStatement extends ValueStatement{

	public IfStatement(Expression condition, Statement body, Statement elseBody, SourceLocation scourceLocation){
		
	}
		
	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public Statement getElseBody() {
		return elseBody;
	}

	public void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}

	public Statement getIfBody() {
		return IfBody;
	}

	public void setIfBody(Statement ifBody) {
		IfBody = ifBody;
	}

	private Expression condition;
	private Statement IfBody;
	private Statement elseBody;
	
	@Override
	public double execute(){
		if (getCondition() instanceof BooleanExpression && ((BooleanExpression) getCondition()).evaluate()){
			if (getIfBody() instanceof ValueStatement){
				return ((ValueStatement) getIfBody()).execute();
			}
			else{
				((VoidStatement) getIfBody()).execute();
				return (Double) null;
			}
		}
		else{
			if (getElseBody() != null && getElseBody() instanceof ValueStatement){
				return ((ValueStatement) getElseBody()).execute();
			}
			else{
				((VoidStatement) getElseBody()).execute();
				return (Double) null;
			}
		}
	}
	
	
}
