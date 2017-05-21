package Statements;

import Expressions.Expression;
import asteroids.part3.programs.SourceLocation;;

public class IfStatement extends Statement{

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
	
	
	
	
}
