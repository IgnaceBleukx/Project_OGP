package Statements;

import asteroids.part3.programs.SourceLocation;

public class BreakStatement extends Statement{

	
	
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
}
