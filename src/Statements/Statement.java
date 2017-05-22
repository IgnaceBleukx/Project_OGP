package Statements;

import asteroids.model.Program;
import asteroids.model.programs.Function;

public class Statement {

	private Function function;
	private Program program;

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}
	
}
