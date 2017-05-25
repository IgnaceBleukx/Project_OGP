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
			throw new ModelException("ThrustOff action in functio body");
		}
		else{
			if(this.getProgram().getFirstRun()){
				this.getProgram().setTimeNeeded(this.getProgram().getTimeNeeded()+0.2);
			}
			if(!getExecutedState()){
				if(this.getProgram().getTime() >= 0.2){
					this.getProgram().getShip().thrustOff();
					this.getProgram().setTime(this.getProgram().getTime()-0.2);
					setExecutedState(true);
				}
			}
		}
	}
}
