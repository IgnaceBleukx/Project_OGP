package Statements;

import Expressions.BooleanExpression;
import Expressions.Expression;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class WhileStatement extends VoidStatement {
	
	public WhileStatement(Expression condition, Statement body, SourceLocation sourceLocation){
		setCondition(condition);
		setBody(body);
		setSourceLocation(sourceLocation);
	}
	
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}


	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}


	public Expression getCondition() {
		return condition;
	}
	public void setCondition(Expression codition) {
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
	private SourceLocation sourceLocation;
	
	@Override
	public void execute() throws ModelException, BreakException{
		if (getCondition() instanceof BooleanExpression){
			passInformation(getCondition());
			while (((BooleanExpression) getCondition()).evaluate()){
				passInformation(getBody());
				getBody().setWhileState(true);
				try{
					if (getBody() instanceof ValueStatement){
						((ValueStatement) getBody()).execute();	
					}
					if (getBody() instanceof VoidStatement){
						((VoidStatement) getBody()).execute();
					}
				}catch(BreakException e){
					return;
				}
			}
		}
		else{
			throw new ModelException("The expression does not evaluate to a boolean");
		}
	}
}
