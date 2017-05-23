package Statements;

import Expressions.BooleanExpression;
import Expressions.EntityExpression;
import Expressions.Expression;
import Expressions.ValueExpression;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class PrintStatement extends VoidStatement {

	public PrintStatement(Expression value, SourceLocation sourceLocation){
		setValue(value);
		setSourceLocation(sourceLocation);
	}
	
	private Expression value;
	private SourceLocation sourceLocation;
	
	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	@Override
	public void execute() throws ModelException{
		if (getFunction() != null){
			throw new ModelException("Printstament in function body");
		}
		else{
			passInformation(getValue());
			if (getValue() instanceof ValueExpression){
//				try{
					System.out.println(((ValueExpression) getValue()).evaluate());
					this.getProgram().addPrintedObject(((ValueExpression) getValue()).evaluate());
//				}catch (ModelException e){
//					throw new ModelException("ModelException in PrintStatement");
//				}
			}
			else if (getValue() instanceof EntityExpression){
				if(((EntityExpression) getValue()).evaluate() != null){
					System.out.println(((EntityExpression) getValue()).evaluate().toString());
				}	
				this.getProgram().addPrintedObject(((EntityExpression) getValue()).evaluate());
			}
			else if (getValue() instanceof BooleanExpression){
				this.getProgram().addPrintedObject(((BooleanExpression) getValue()).evaluate());
				System.out.println(((BooleanExpression) getValue()).evaluate());
			}
		}
	}
}
