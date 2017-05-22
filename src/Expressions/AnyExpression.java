package Expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class AnyExpression extends EntityExpression {

	public AnyExpression(SourceLocation sourceLocation){
		setSourceLocation(sourceLocation);
	}
	
	private SourceLocation sourceLocation;
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	@Override
	public Entity evaluate(){
		return this.getProgram().getShip().getWorld().getAllEntities().iterator().next();
	}
}
