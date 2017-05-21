package Statements;

import Expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Statement {
	
	public WhileStatement(Expression condition, Statement Body, SourceLocation scourceLocation){
		
	}
	
	
	public Expression getCodition() {
		return codition;
	}
	public void setCodition(Expression codition) {
		this.codition = codition;
	}
	public Statement getBody() {
		return body;
	}
	public void setBody(Statement body) {
		this.body = body;
	}
	
	private Expression codition;
	private Statement body;
}
