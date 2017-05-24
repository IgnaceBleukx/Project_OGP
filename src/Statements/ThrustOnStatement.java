package Statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class ThrustOnStatement extends VoidStatement {

	public ThrustOnStatement(SourceLocation location){
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
		if (this.getFunction() == null){
			this.getProgram().getShip().thrustOn();
		}
		else{
			throw new ModelException("ThrustOn action in function body");
		}
	}
}
