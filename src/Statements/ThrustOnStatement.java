package Statements;

public class ThrustOnStatement extends VoidStatement {

	public ThrustOnStatement(){
	}
	
	@Override
	public void execute(){
		this.getFunction().getProgram().getShip().thrustOn();
	}
}
