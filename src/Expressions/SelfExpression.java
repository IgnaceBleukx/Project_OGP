package Expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class SelfExpression extends EntityExpression {

	public SelfExpression(SourceLocation sourceLocation){
		setSourceLocation(sourceLocation);
	}
	
	private SourceLocation sourceLocation;
	@Override
	public Ship evaluate(){
		return this.getFunction().getProgram().getShip();
	}
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
