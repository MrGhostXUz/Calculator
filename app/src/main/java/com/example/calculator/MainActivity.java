package com.example.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
    private TextView exercise;
    private TextView bufersolve;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button add;
    private Button subtract;
    private Button multiply;
    private Button divide;
    private Button equal;
    private Button clear;
    private Button MR;
    private Button Mplus;
    private Button Mminus;
    private Button plus_minus;
    private Button brackets;
    private Button backspace;
    private Button point;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exercise = findViewById(R.id.main);
        bufersolve = findViewById(R.id.bufer);
        btn0 = findViewById(R.id.zero);
        btn1 = findViewById(R.id.one);
        btn2 = findViewById(R.id.two);
        btn3 = findViewById(R.id.three);
        btn4 = findViewById(R.id.four);
        btn5 = findViewById(R.id.five);
        btn6 = findViewById(R.id.six);
        btn7 = findViewById(R.id.seven);
        btn8 = findViewById(R.id.eight);
        btn9 = findViewById(R.id.nine);
        add = findViewById(R.id.add);
        subtract = findViewById(R.id.subtract);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        equal = findViewById(R.id.equal);
        clear = findViewById(R.id.clear);
        MR = findViewById(R.id.MR);
        Mplus = findViewById(R.id.Mplus);
        Mminus = findViewById(R.id.Mminus);
        plus_minus = findViewById(R.id.plus_minus);
        brackets = findViewById(R.id.brackets);
        backspace = findViewById(R.id.backspace);
        point = findViewById(R.id.point);


        final StringBuffer[] bufer = {new StringBuffer()};
        final boolean[] may = {true};
        final StringBuffer str=new StringBuffer("0");
        final StringBuffer strToShow=new StringBuffer();
        final int[] sana = {0};
        final boolean [] plusminusSanagich={true};

        exercise.setText(str);

        Mplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str.append("+").append(bufer[0]);
                exercise.setText(str);
            }
        });
        Mminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str.append("-").append(bufer[0]);
                exercise.setText(str);
            }
        });

        plus_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!String.valueOf(str).equals("0"))
                {
                    if(str.indexOf("+")==-1&&
                            str.indexOf("-")==-1&&
                            str.indexOf("*")==-1&&
                            str.indexOf("/")==-1&&
                            str.indexOf("(")==-1&&
                            str.indexOf(")")==-1)
                    {
                        str.insert(0, "-");
                        exercise.setText(str);
                    }
                    else if(str.indexOf("+")==-1&&
                            str.indexOf("-")==0&&
                            str.indexOf("*")==-1&&
                            str.indexOf("/")==-1&&
                            str.indexOf("(")==-1&&
                            str.indexOf(")")==-1)
                    {
                        str.delete(0, 1);
                        exercise.setText(str);
                    }else if(str.lastIndexOf("+")>0||
                            str.lastIndexOf("-")>0||
                            str.lastIndexOf("*")>0||
                            str.lastIndexOf("/")>0||
                            str.lastIndexOf("(")>0||
                            str.lastIndexOf(")")>=0)
                    {
                        if(str.substring(str.length()-1, str.length()).equals("0")||
                                str.substring(str.length()-1, str.length()).equals("1")||
                                str.substring(str.length()-1, str.length()).equals("2")||
                                str.substring(str.length()-1, str.length()).equals("3")||
                                str.substring(str.length()-1, str.length()).equals("4")||
                                str.substring(str.length()-1, str.length()).equals("5")||
                                str.substring(str.length()-1, str.length()).equals("6")||
                                str.substring(str.length()-1, str.length()).equals("7")||
                                str.substring(str.length()-1, str.length()).equals("8")||
                                str.substring(str.length()-1, str.length()).equals("9")
                        )
                        {
                            int a=0;
                            for(int i=str.length()-1; i>0; i--)
                            {
                                if(str.substring(i, i+1).equals("+")||
                                        str.substring(i, i+1).equals("-")||
                                        str.substring(i, i+1).equals("*")||
                                        str.substring(i, i+1).equals("/"))
                                {
                                    a=i;
                                    break;
                                }
                            }
                            str.insert(a+1, "(-");
                            str.append(")");
                            exercise.setText(str);
                        }
                        else if(str.substring(str.length()-1, str.length()).equals(")"))
                        {
                            str.delete(str.lastIndexOf("(-"), str.lastIndexOf("(-")+2);
                            str.delete(str.length()-1, str.length());
                            exercise.setText(str);
                        }
                    }
                }
            }
        });

        MR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    bufer[0].delete(0, bufer[0].length());
                    bufer[0].append(String.valueOf(bufersolve.getText().toString()));

            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Core(str, bufersolve);
                } catch (Exception e) {
                    Log.d("Xatolik", "****");
                }
                str.delete(0, str.length());
                str.append(bufersolve.getText().toString());
                exercise.setText(str);
                bufersolve.setText("");
                may[0]=true;

            }});
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str.delete(0, str.length());
                str.append("0");
                may[0]=true;
                exercise.setText(str);

            }
        });
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.substring(str.length()-1, str.length()).equals(")"))
                {
                    sana[0]++;
                }
                else  if(str.substring(str.length()-1, str.length()).equals("("))
                {
                    sana[0]--;
                }
                str.deleteCharAt(str.length()-1);

                if(str.length()==0)
                {
                    str.append("0");
                }
                exercise.setText(str);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.length()==1&&str.substring(0, 1).equals("0")){
                str.delete(str.length()-1, str.length());}
                str.append("1");
                exercise.setText(str);
                may[0]=true;
                if(sana[0]==0){
                    try {
                        Core(str, bufersolve);
                    } catch (Exception e) {
                        Log.d("Xatolik", "****");
                    }}
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.length()==1&&str.substring(0, 1).equals("0")){
                    str.delete(str.length()-1, str.length());}
                str.append("2");
                exercise.setText(str);
                if(sana[0]==0){
                    try {
                        Core(str, bufersolve);
                    } catch (Exception e) {
                        Log.d("Xatolik", "****");
                    }}
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.length()==1&&str.substring(0, 1).equals("0")){
                    str.delete(str.length()-1, str.length());}
                str.append("3");
                exercise.setText(str);
                if(sana[0]==0){
                    try {
                        Core(str, bufersolve);
                    } catch (Exception e) {
                        Log.d("Xatolik", "****");
                    }}
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.length()==1&&str.substring(0, 1).equals("0")){
                    str.delete(str.length()-1, str.length());}
                str.append("4");
                exercise.setText(str);
                if(sana[0]==0){
                    try {
                        Core(str, bufersolve);
                    } catch (Exception e) {
                        Log.d("Xatolik", "****");
                    }}
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.length()==1&&str.substring(0, 1).equals("0")){
                    str.delete(str.length()-1, str.length());}
                str.append("5");
                exercise.setText(str);
                if(sana[0]==0){
                    try {
                        Core(str, bufersolve);
                    } catch (Exception e) {
                        Log.d("Xatolik", "****");
                    }}
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.length()==1&&str.substring(0, 1).equals("0")){
                    str.delete(str.length()-1, str.length());}
                str.append("6");
                exercise.setText(str);
                if(sana[0]==0){
                    try {
                        Core(str, bufersolve);
                    } catch (Exception e) {
                        Log.d("Xatolik", "****");
                    }}
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.length()==1&&str.substring(0, 1).equals("0")){
                    str.delete(str.length()-1, str.length());}
                str.append("7");
                exercise.setText(str);
                if(sana[0]==0){
                    try {
                        Core(str, bufersolve);
                    } catch (Exception e) {
                        Log.d("Xatolik", "****");
                    }}
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.length()==1&&str.substring(0, 1).equals("0")){
                    str.delete(str.length()-1, str.length());}
                str.append("8");
                exercise.setText(str);
                if(sana[0]==0){
                    try {
                        Core(str, bufersolve);
                    } catch (Exception e) {
                        Log.d("Xatolik", "****");
                    }}
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.length()==1&&str.substring(0, 1).equals("0")){
                    str.delete(str.length()-1, str.length());}
                str.append("9");
                exercise.setText(str);
                if(sana[0]==0){
                    try {
                        Core(str, bufersolve);
                    } catch (Exception e) {
                        Log.d("Xatolik", "****");
                    }}
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(str.length()==1&&!str.substring(0, 1).equals("0")){
                    str.append("0");
                exercise.setText(str);}
                    else if(str.length()>1) {
                    str.append("0");
                    exercise.setText(str);
                }
                if(sana[0]==0){
                    try {
                        Core(str, bufersolve);
                    } catch (Exception e) {
                        Log.d("Xatolik", "****");
                    }}
            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (may[0] &&(str.substring(str.length() - 1).equals("0") ||
                        str.substring(str.length() - 1).equals("1") ||
                        str.substring(str.length() - 1).equals("2") ||
                        str.substring(str.length() - 1).equals("3") ||
                        str.substring(str.length() - 1).equals("4") ||
                        str.substring(str.length() - 1).equals("5") ||
                        str.substring(str.length() - 1).equals("6") ||
                        str.substring(str.length() - 1).equals("7") ||
                        str.substring(str.length() - 1).equals("8") ||
                        str.substring(str.length() - 1).equals("9"))) {
                    str.append(".");
                    may[0] =false;
                    exercise.setText(str);
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(str.length()==0&&str.substring(str.length() - 1).equals("0"))
                {
                    str.append("");
                    exercise.setText(str);
                }
                else if(str.length()!=0&&str.substring(str.length() - 1).equals("0"))
                {
                    str.append("+");
                    exercise.setText(str);
                }
                else if (str.substring(str.length() - 1).equals("1") ||
                        str.substring(str.length() - 1).equals("2") ||
                        str.substring(str.length() - 1).equals("3") ||
                        str.substring(str.length() - 1).equals("4") ||
                        str.substring(str.length() - 1).equals("5") ||
                        str.substring(str.length() - 1).equals("6") ||
                        str.substring(str.length() - 1).equals("7") ||
                        str.substring(str.length() - 1).equals("8") ||
                        str.substring(str.length() - 1).equals("9")||
                        str.substring(str.length() - 1).equals(")")){
                    str.append("+");
                    may[0]=true;
                exercise.setText(str);}
                else if(!String.valueOf(str).equals("0")&&str.substring(str.length()-1).equals("-")){
                    str.delete(str.length()-1, str.length());
                    str.append("+");
                    may[0]=true;
                    exercise.setText(str);
                }
        }});
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(str.length()==0&&str.substring(str.length() - 1).equals("0"))
                {
                    str.append("");
                    exercise.setText(str);
                } else if(str.length()!=0&&str.substring(str.length() - 1).equals("0"))
                {
                    str.append("-");
                    may[0]=true;
                    exercise.setText(str);
                }
                else if (str.substring(str.length() - 1).equals("1") ||
                        str.substring(str.length() - 1).equals("2") ||
                        str.substring(str.length() - 1).equals("3") ||
                        str.substring(str.length() - 1).equals("4") ||
                        str.substring(str.length() - 1).equals("5") ||
                        str.substring(str.length() - 1).equals("6") ||
                        str.substring(str.length() - 1).equals("7") ||
                        str.substring(str.length() - 1).equals("8") ||
                        str.substring(str.length() - 1).equals("9")||
                        str.substring(str.length() - 1).equals(")")){
                        str.append("-");
                    may[0]=true;
                        exercise.setText(str);}
                else if(str.substring(str.length()-1).equals("+")){
                    str.delete(str.length()-1, str.length());
                    str.append("-");
                    may[0]=true;
                    exercise.setText(str);
                }
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (
                        str.substring(str.length() - 1).equals("0") ||
                        str.substring(str.length() - 1).equals("1") ||
                        str.substring(str.length() - 1).equals("2") ||
                        str.substring(str.length() - 1).equals("3") ||
                        str.substring(str.length() - 1).equals("4") ||
                        str.substring(str.length() - 1).equals("5") ||
                        str.substring(str.length() - 1).equals("6") ||
                        str.substring(str.length() - 1).equals("7") ||
                        str.substring(str.length() - 1).equals("8") ||
                        str.substring(str.length() - 1).equals("9")||
                        str.substring(str.length() - 1).equals(")")){
                    str.append("*");
                   may[0]=true;
                    exercise.setText(str);
                }
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (
                                str.substring(str.length() - 1).equals("0") ||
                                str.substring(str.length() - 1).equals("1") ||
                                str.substring(str.length() - 1).equals("2") ||
                                str.substring(str.length() - 1).equals("3") ||
                                str.substring(str.length() - 1).equals("4") ||
                                str.substring(str.length() - 1).equals("5") ||
                                str.substring(str.length() - 1).equals("6") ||
                                str.substring(str.length() - 1).equals("7") ||
                                str.substring(str.length() - 1).equals("8") ||
                                str.substring(str.length() - 1).equals("9") ||
                                str.substring(str.length() - 1).equals(")")){
                    str.append("/");
                    may[0]=true;
                    exercise.setText(str);
                }
            }
        });

        brackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(
                        str.substring(str.length()-1, str.length()).equals("*")||
                        str.substring(str.length()-1, str.length()).equals("/")||
                        str.substring(str.length()-1, str.length()).equals("+")||
                        str.substring(str.length()-1, str.length()).equals("-")||
                        str.substring(str.length()-1, str.length()).equals("("))
                {
                    sana[0]++;
                    str.append("(");
                    exercise.setText(str);
                }
                else if(String.valueOf(str).equals("0"))
                {
                    str.delete(0, 1);
                    sana[0]++;
                    str.append("(");
                    exercise.setText(str);
                }
                else if(sana[0]>0&&(
                        str.substring(str.length() - 1, str.length()).equals("0") ||
                        str.substring(str.length() - 1, str.length()).equals("1") ||
                        str.substring(str.length() - 1, str.length()).equals("2") ||
                        str.substring(str.length() - 1, str.length()).equals("3") ||
                        str.substring(str.length() - 1, str.length()).equals("4") ||
                        str.substring(str.length() - 1, str.length()).equals("5") ||
                        str.substring(str.length() - 1, str.length()).equals("6") ||
                        str.substring(str.length() - 1, str.length()).equals("7") ||
                        str.substring(str.length() - 1, str.length()).equals("8") ||
                        str.substring(str.length() - 1, str.length()).equals("9")||
                        str.substring(str.length() - 1, str.length()).equals(")")))
                {
                    str.append(")");
                    sana[0]--;
                    exercise.setText(str);
                    if(sana[0]==0)
                    {
                        try {
                            Core(str, bufersolve);
                        } catch (Exception e) {
                            Log.d("Xatolik", "****");
                        }
                    }
                }
            }
        });


    }
    public static void Core(StringBuffer question, TextView bufersolve) throws Exception
    {
        int[][] qavs = Hisobla.FirstStep(question);
        int size = 0;
        for (int i = 0; i < question.length(); i++) {
            if (valueOf(question.charAt(i)).equals("(")) {
                size++;
            }
        }
        for (int i = 0; i < size; i++) {
            StringBuffer bufer = new StringBuffer(question.substring(qavs[i][1] + 1, qavs[i][2]));
            int delta = Hisobla.NullStep(bufer).length();
            question.insert(qavs[i][1], Hisobla.NullStep(bufer));
            question.delete(qavs[i][1] + delta, qavs[i][2] + 1 + delta);
            for(int s=i+1; s<size; s++) {
                qavs[s][0]+=delta -1-qavs[i][0];

                if(qavs[s][2]>qavs[i][2])
                {
                    qavs[s][2] += delta -1-qavs[i][0];
                }
                if(qavs[s][1] > qavs[i][1])
                {
                    qavs[s][1] += delta -1-qavs[i][0];
                }
            }

        }

        bufersolve.setText(Hisobla.NullStep(question));
    }

}
