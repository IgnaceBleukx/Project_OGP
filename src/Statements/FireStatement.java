package Statements;

public class FireStatement extends VoidStatement {

	public FireStatement(){
	}
	
	public void execute(){
		this.getFunction().getProgram().getShip().fire();
	}
}
