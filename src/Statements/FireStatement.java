package Statements;

import asteroids.part3.programs.SourceLocation;

public class FireStatement extends VoidStatement {

	public FireStatement(SourceLocation location){
		setSourceLocation(location);
	}
	
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}


	private SourceLocation sourceLocation;
	
	@Override
	public void execute(){
		this.getFunction().getProgram().getShip().fire();
	}
}
