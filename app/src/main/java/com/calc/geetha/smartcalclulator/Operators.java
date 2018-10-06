package com.calc.geetha.smartcalclulator;

public class Operators {
    int position;
    char operator;
    Operators(int p,char op){
        position=p;
        operator=op;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Operators{" +
                "position=" + position +
                ", operator=" + operator +
                '}';
    }
}
