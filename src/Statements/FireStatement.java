package Statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class FireStatement extends ActionStatement {

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
	public void execute() throws ModelException{
		if (this.getFunction() != null){
			throw new ModelException("Fire action preformed in funtionbody");
		}
		else{
			this.getProgram().getShip().fire();
		}
	}
}
