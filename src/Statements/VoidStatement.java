package Statements;

import asteroids.model.programs.Function;
import asteroids.util.ModelException;

public class VoidStatement extends Statement {

	public void execute() throws ModelException {
		throw new IllegalStateException("This method should be overwritten by all subclasses of 'Statement'");
	}
}
