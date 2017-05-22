package asteroids.model.programs;

import Statements.Statement;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Function {

	public Function(String functionName, Statement body, SourceLocation sourceLocation){
		setFunctionName(functionName);
		setBody(body);
		setSourceLocation(sourceLocation);
	}
	
	private Program program;
	private Statement body;
	private String functionName;
	private SourceLocation sourceLocation;
	
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public Statement getBody() {
		return body;
	}
	public void setBody(Statement body) {
		this.body = body;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
