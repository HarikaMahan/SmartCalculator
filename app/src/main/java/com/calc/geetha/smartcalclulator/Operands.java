package com.calc.geetha.smartcalclulator;

public class Operands {
    int position;
    int operand;

    public Operands(int position, int operand) {
        this.position = position;
        this.operand = operand;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getOperand() {
        return operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }

    @Override
    public String toString() {
        return "Operands{" +
                "position=" + position +
                ", operand=" + operand +
                '}';
    }
}
