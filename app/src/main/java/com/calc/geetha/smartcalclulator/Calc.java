package com.calc.geetha.smartcalclulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Stack;

public class Calc extends AppCompatActivity {
    static Float number=new Float(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
    }

    public static float evaluate(String expression)
    {
        char[] tokensw = expression.toCharArray();
        boolean repeatOperator=false;
        String[] tokens=expression.split(" ",-1);

        // Stack for numbers: 'values'
        Stack<Float> values = new Stack<Float>();

        // Stack for Operators: 'ops'
        Stack<String> ops = new Stack<String>();
        for (int i = 0; i < tokens.length; i++)
        {
            System.out.println("tokens["+i+"]-"+tokens[i]);
        }
        for (int i = 0; i < tokens.length; i++)
        {

            System.out.println("token["+i+"]-"+tokens[i]);
             if (tokens[i] == " "){
                System.out.println(" space token["+i+"]-"+tokens[i]);
                continue;
            }
             // Current token is a number, push it to stack for numbers

            if(isNum(tokens[i])){
                 values.push(number);
             }
            // Current token is an operator.
            else if (tokens[i].equals("+") || tokens[i].equals("-" )||
                    tokens[i].equals("X") || tokens[i].equals("/"))
            {
                if(tokens[i+1].equals("+") || tokens[i+1].equals("-") ||
                        tokens[i+1].equals("X")|| tokens[i+1].equals("/")){
                    System.out.println("second operator");
                    return -123123;
                }

                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }





        }
        System.out.println("values stack-"+values.toString());
        System.out.println("operator stack-"+ops.toString());
        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));


        // Top of 'values' contains result, return it
        return values.pop();
    }

    public  static boolean isNum(String tokenNum){
        try{
            number=Float.parseFloat(tokenNum);
        }
        catch(Exception e)
        {
            System.out.println("Exception for value-"+tokenNum);
            return false;
        }
        if(number<=0 || number >0){
            return true;}
        else return false;
    }
    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(String op1, String op2)
    {
        if (op2 == "( " || op2 == ")")
            return false;
        if ((op1 == "X" || op1 == "/") && (op2 == "+" || op2 == "-"))
            return false;
        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static float applyOp(String op, float b, float a)
    {
        switch (op)
        {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "X":
                return a * b;
            case "/":
                try {
                    if (b == 0)
                        throw new
                                UnsupportedOperationException("Cannot divide by zero");
                }catch (Exception e){
                    Log.d("CALC","divide by zero error");
                    return -123123;
                }
                return a / b;
        }
        return 0;
    }
}
