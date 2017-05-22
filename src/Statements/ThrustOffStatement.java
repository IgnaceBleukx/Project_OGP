package Statements;

import asteroids.part3.programs.SourceLocation;

public class ThrustOffStatement extends VoidStatement{
	
	public ThrustOffStatement(SourceLocation location){
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
		this.getProgram().getShip().thrustOn();
	}

}
