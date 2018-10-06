package com.calc.geetha.smartcalclulator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    TextView resultTextView;
    TextView bufferTextView;
    int obcode=0;
    float mValueOne=0 , mValueTwo=0 ;
    Context context;
    boolean mAddition , mSubtract ,mMultiplication ,mDivision ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView=findViewById(R.id.resul_et);
        bufferTextView=findViewById(R.id.bufferText);
        context=this;
    }

    public void getValue(View view) {

        String newval=((Button) view).getText().toString().trim();
        String result="";
        String addedvalue=  resultTextView.getText().toString();
        //.makeText(this, "val-"+addedvalue, Toast.LENGTH_SHORT).show();
        try {
            if(addedvalue.equals("Error")){
                addedvalue =  newval;
            }
            else {
                int initval = Integer.parseInt(addedvalue);
                if (initval == 0) {
                    addedvalue = newval;
                    // Toast.makeText(this, "val0-"+addedvalue, Toast.LENGTH_SHORT).show();

                } else {
                    // Toast.makeText(this, "valelse-"+addedvalue, Toast.LENGTH_SHORT).show();

                    addedvalue = resultTextView.getText().toString() + newval;
                }
            }

        }catch (Exception e)
        {
            addedvalue = resultTextView.getText().toString() + newval;
        }
        resultTextView.setText(addedvalue);
    }

    public void addition(View view) {

            String actvalue=resultTextView.getText().toString();
            resultTextView.setText(actvalue+" + ");
            mAddition = true;
            {
            /*if(mValueOne!=0 && mAddition){
                  String newvalue=resultTextView.getText().toString().trim();
                int indexOfOp=newvalue.indexOf("+");
                String secondoperand=newvalue.substring(indexOfOp+1,newvalue.length()).trim();
                Toast.makeText(this, "sencond operand is "+secondoperand, Toast.LENGTH_SHORT).show();

                if(secondoperand!="") {
                    Toast.makeText(this, "mval1-"+mValueOne+"sencond operand is not empty--- "+secondoperand, Toast.LENGTH_SHORT).show();
                    try {
                        mValueOne = mValueOne + Float.parseFloat(secondoperand);
                        resultTextView.setText(mValueOne+"+");
                    }catch (Exception e){
                        Log.d("CAL",e.getMessage());
                    }
                    }
                else{
                    Toast.makeText(this, "sencond operand is empty"+newvalue, Toast.LENGTH_SHORT).show();
                    mValueOne = Float.parseFloat(newvalue.substring(0,newvalue.length()-1).trim());
                    resultTextView.setText(newvalue+"+");

                }

                //resultTextView.setText(newvalue.substring(0,newvalue.length()-1));

            }
            else {
                mValueOne = Float.parseFloat(resultTextView.getText().toString());
                resultTextView.setText(actvalue+"+");
            }*/
            }



    }

    public void clearAll(View view) {
        resultTextView.setText("0");
        bufferTextView.setText("");
    }

    public void multiplication(View view) {


        String actvalue=resultTextView.getText().toString();
        resultTextView.setText(actvalue+" X ");
        mMultiplication = true;
        //resultTextView.setText("0");
    }

    public void subtraction(View view) {

        String actvalue=resultTextView.getText().toString().trim();
        resultTextView.setText(actvalue+" - ");
        mSubtract = true;
        //resultTextView.setText("0");
    }

    public void division(View view) {
        String actvalue=resultTextView.getText().toString().trim();
        resultTextView.setText(actvalue+" / ");
        mDivision = true;

    }

    public void getFinalValue(View view) {
        String str=resultTextView.getText().toString().trim();
        new AnswerAsyc().execute(str);


    }
    public class AnswerAsyc extends AsyncTask<String,Integer,Float>{
        float  answer=0;
        String queryStr="";
        @Override
        protected Float doInBackground(String... strings) {
            Log.d("SCAL","query string-"+strings[0]);
            queryStr=strings[0];
            try {
                answer = Calc.evaluate(queryStr);
            }catch (Exception e){
                Log.d("CAL","doinback-"+e.getMessage());
                answer=-123123;
            }
            return answer;
        }

        @Override
        protected void onPostExecute(Float s) {
            super.onPostExecute(s);
            if(s==-123123)
            resultTextView.setText("Error");
            else if (s == Math.round(s)) {
                resultTextView.setText((int)s.floatValue()+"");
            }
            else{
                resultTextView.setText(s+"");
            }
            bufferTextView.setText(queryStr);
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            bufferTextView.startAnimation(anim);


        }
    }

}
