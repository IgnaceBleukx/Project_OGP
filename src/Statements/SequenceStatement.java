package Statements;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.Program;
import asteroids.model.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class SequenceStatement extends ValueStatement {

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

		
	private List<Statement> statements = new ArrayList<Statement>();
	private SourceLocation sourceLocation;
	
	@Override
	public double execute() throws ModelException{
//		for (Statement statement : getStatements()){
//			passInformation(statement);
//			if (statement instanceof ValueStatement){
//				((ValueStatement) statement).execute();
//			}
//			if (statement instanceof VoidStatement){
//				((VoidStatement) statement).execute();
//			}
//		}
		int index = 0;
		Statement statementToHandle = null;
		while (index < getStatements().size()-1){
			statementToHandle = getStatements().get(index);
			passInformation(statementToHandle);
			if (statementToHandle instanceof ValueStatement){
				((ValueStatement) statementToHandle).execute();
			}
			if (statementToHandle instanceof VoidStatement){
				((VoidStatement) statementToHandle).execute();
			}
			index++;
		}
		if (getStatements().get(getStatements().size()-1) instanceof ValueStatement){
			passInformation(getStatements().get(getStatements().size()-1));
			return ((ValueStatement) getStatements().get(getStatements().size()-1)).execute();
		}
		else{
			passInformation(getStatements().get(getStatements().size()-1));
			((VoidStatement) getStatements().get(getStatements().size()-1)).execute();
			return Double.NaN;
		}
	}
}
