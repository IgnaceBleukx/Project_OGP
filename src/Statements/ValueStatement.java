package Statements;

import asteroids.model.programs.Function;
import asteroids.util.ModelException;

public class ValueStatement extends Statement {

	public double execute() throws ModelException, BreakException{
		throw new IllegalStateException("This method should be overrided by all subclasses");
	}	
}
