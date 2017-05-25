package Statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class ThrustOffStatement extends ActionStatement{
	
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
	public void execute() throws ModelException{
		if (this.getFunction() != null){
			throw new ModelException("ThrustOff action in function body");
		}
		else{
			this.getProgram().getShip().thrustOff();
		}
	}

}
