package Statements;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class SequenceStatement extends VoidStatement {

	public SequenceStatement(List<Statement> statements, SourceLocation sourcelocation){
		setStatements(statements);
		setSourceLocation(sourceLocation);
	}
	
	public List<Statement> getStatements() {
		return statements;
	}
	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	private List<Statement> statements;
	private SourceLocation sourceLocation;
	
	@Override
	public void execute(){
		for (Statement statement : getStatements()){
			if (statement instanceof ValueStatement){
				((ValueStatement) statement).execute();
			}
			if (statement instanceof VoidStatement){
				((VoidStatement) statement).execute();
			}
		}
	}
}
