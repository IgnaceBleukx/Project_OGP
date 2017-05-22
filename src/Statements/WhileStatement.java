package Statements;

import Expressions.BooleanExpression;
import Expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends VoidStatement {
	
	public WhileStatement(Expression condition, Statement Body, SourceLocation scourceLocation){
		
	}
	
	
	public Expression getCondition() {
		return condition;
	}
	public void setCodition(Expression codition) {
		this.condition = codition;
	}
	public Statement getBody() {
		return body;
	}
	public void setBody(Statement body) {
		this.body = body;
	}
	
	private Expression condition;
	private Statement body;
	
	@Override
	public void execute(){
		if (getCondition() instanceof BooleanExpression){
			while (((BooleanExpression) getCondition()).evaluate()){
				if (getBody() instanceof ValueStatement){
					((ValueStatement) getBody()).execute();	
				}
				if (getBody() instanceof VoidStatement){
					((VoidStatement) getBody()).execute();
				}
			}
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to a boolean");
		}
	}
}
