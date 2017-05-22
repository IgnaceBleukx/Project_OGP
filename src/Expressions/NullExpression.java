package Expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class NullExpression extends EntityExpression{

	public NullExpression(SourceLocation sourcelocation){
		setSourcelocation(sourcelocation);
	}
	
	private SourceLocation sourcelocation;
	
	@Override
	public Entity evaluate(){
		return null;
	}

	public SourceLocation getSourcelocation() {
		return sourcelocation;
	}

	public void setSourcelocation(SourceLocation sourcelocation) {
		this.sourcelocation = sourcelocation;
	}
	
}
