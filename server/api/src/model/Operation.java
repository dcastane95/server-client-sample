package model;

public class Operation {
	private Integer firstNumber;
	private Integer secondNumber;
	private String operator;
	private boolean dividingByZero = false;
	
	public Operation(Integer firstNumber, Integer secondNumber, String operator) {
		this.setFirstNumber(firstNumber);
		this.setSecondNumber(secondNumber);
		this.setOperator(operator);
	}
	
	public Operation() {
	}
	
	public Integer getFirstNumber() {
		return firstNumber;
	}
	public void setFirstNumber(Integer firstNumber) {
		this.firstNumber = firstNumber;
	}
	public Integer getSecondNumber() {
		return secondNumber;
	}
	public void setSecondNumber(Integer secondNumber) {
		this.secondNumber = secondNumber;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		if((operator != null) && (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/"))) {
			this.operator = operator;
		}
		else {
			this.operator = null;
		}
	}
	
	public Double getResult() {
		Double result = null;
		if(isComplete()) {
			switch(operator) {
				case "+":
					result = new Double(getFirstNumber() + getSecondNumber());
					break;
				case "-":
					result = new Double(getFirstNumber() - getSecondNumber());
					break;
				case "*":
					result = new Double( getFirstNumber() * getSecondNumber());
					break;
				case "/":
					if (getSecondNumber() != 0)
						result = new Double(getFirstNumber()) / new Double(getSecondNumber());
					else
						dividingByZero = true;
					break;
				default:
					result = null;
					break;
			}
		}
		return result;
	}
	private boolean isComplete() {
		return (getFirstNumber() != null && getSecondNumber() != null && getOperator() != null);
	}
	
	public String getError() {
		String errorRes = null;
		
		if(!isComplete() || getResult() == null) {
			errorRes = "Error found:\n";
			if(getFirstNumber() == null) {
				errorRes = String.format("%s - Valid firstNumber missing.\n", errorRes);
			}
			if(getSecondNumber() == null) {
				errorRes = String.format("%s - Valid secondNumber missing.\n", errorRes);
			}
			if(getOperator() == null) {
				errorRes = String.format("%s - Valid operator (+,-,*,/) missing.\n", errorRes);
			}
			if(dividingByZero) {
				errorRes = String.format("%s - Trying to divide by 0!\n", errorRes);
			}
		}
		return errorRes;
	}
	
	public String toString() {
		return String.format("%d %s %d = %.2f", 
				getFirstNumber(),getOperator(), getSecondNumber(), getResult());
	}
	
}
