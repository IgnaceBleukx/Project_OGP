package Statements;

public class ActionStatement extends VoidStatement {
	
	public boolean executedState = false;
	
	public boolean getExecutedState(){
		return executedState;
	}
	
	public void setExecutedState(boolean active){
		this.executedState = active;
	}

}
