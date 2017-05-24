package Statements;

import asteroids.part3.programs.SourceLocation;

public class SkipStatement extends VoidStatement {

	public SkipStatement(SourceLocation location){
		setSourceLocation(location);
	}
	
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}


	private SourceLocation sourceLocation;
	public void execute(){
	}
}
