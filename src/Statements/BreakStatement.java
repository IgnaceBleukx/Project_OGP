package Statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class BreakStatement extends VoidStatement{

	
	
	public BreakStatement(SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation scourceLocation) {
		this.sourceLocation = scourceLocation;
	}

	private SourceLocation sourceLocation; 
	
	@Override
	public void execute() throws ModelException, BreakException{
		if (! getWhileState()){
		throw new ModelException("Break used outside while");
		}
		else{
			throw new BreakException();
		}
	}
}
