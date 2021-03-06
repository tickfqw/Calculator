package com.example.tick.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private Button[] btn=new Button[10];
    private EditText input;
    private TextView mem;
    private TextView _drg;
    private TextView tip;
    private Button div,mul,sub,add,equal,
    sin,cos,tan,log,ln,sqrt,square,factorial,bksp,left,right,dot,exit,drg,mc,c;
    public String str_old;
    public String str_new;
    public boolean vbegin=true;
    public boolean drg_flag=true;
    public double pi=4*Math.atan(1);
    public boolean tip_lock=true;
    public boolean equals_flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input=(EditText)findViewById(R.id.input);
        mem=(TextView)findViewById(R.id.mem);
        tip=(TextView)findViewById(R.id.tip);
        _drg=(TextView)findViewById(R.id._drg);
        btn[0]=(Button)findViewById(R.id.zero);
        btn[1]=(Button)findViewById(R.id.one);
        btn[2]=(Button)findViewById(R.id.two);
        btn[3]=(Button)findViewById(R.id.three);
        btn[4]=(Button)findViewById(R.id.four);
        btn[5]=(Button)findViewById(R.id.five);
        btn[6]=(Button)findViewById(R.id.six);
        btn[7]=(Button)findViewById(R.id.seven);
        btn[8]=(Button)findViewById(R.id.eight);
        btn[9]=(Button)findViewById(R.id.nine);
        div=(Button)findViewById(R.id.divide);
        mul=(Button)findViewById(R.id.mul);
        sub=(Button)findViewById(R.id.sub);
        add=(Button)findViewById(R.id.add);
        equal=(Button)findViewById(R.id.equal);
        sin=(Button)findViewById(R.id.sin);
        cos=(Button)findViewById(R.id.cos);
        tan=(Button)findViewById(R.id.tan);
        log=(Button)findViewById(R.id.log);
        ln=(Button)findViewById(R.id.ln);
        sqrt=(Button)findViewById(R.id.sqrt);
        square=(Button)findViewById(R.id.square);
        factorial=(Button)findViewById(R.id.factorial);
        bksp=(Button)findViewById(R.id.bksp);
        left=(Button)findViewById(R.id.left);
        right=(Button)findViewById(R.id.right);
        dot=(Button)findViewById(R.id.dot);
        exit=(Button)findViewById(R.id.exit);
        drg=(Button)findViewById(R.id.drg);
        mc=(Button)findViewById(R.id.mc);
        c=(Button)findViewById(R.id.c);
        for(int i=0;i<10;i++){
            btn[i].setOnClickListener(actionPerformed);
        }
        div.setOnClickListener(actionPerformed);
        mul.setOnClickListener(actionPerformed);
        sub.setOnClickListener(actionPerformed);
        add.setOnClickListener(actionPerformed);
        equal.setOnClickListener(actionPerformed);
        sin.setOnClickListener(actionPerformed);
        cos.setOnClickListener(actionPerformed);
        tan.setOnClickListener(actionPerformed);
        log.setOnClickListener(actionPerformed);
        ln.setOnClickListener(actionPerformed);
        sqrt.setOnClickListener(actionPerformed);
        square.setOnClickListener(actionPerformed);
        factorial.setOnClickListener(actionPerformed);
        bksp.setOnClickListener(actionPerformed);
        left.setOnClickListener(actionPerformed);
        right.setOnClickListener(actionPerformed);
        dot.setOnClickListener(actionPerformed);
        exit.setOnClickListener(actionPerformed);
        drg.setOnClickListener(actionPerformed);
        mc.setOnClickListener(actionPerformed);
        c.setOnClickListener(actionPerformed);
    }

    String[] Tipcommand=new String[500];
    int tip_i=0;

    private View.OnClickListener actionPerformed=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String command=((Button) v).getText().toString();
            String str=input.getText().toString();
            if(equals_flag==false&&"0123456789.()sincostanlnlogn!+-x/√^".indexOf(command)!=-1){
                if(right(str)){
                    if("+-x/√^)".indexOf(command)!=-1){
                        for (int i=0;i<str.length();i++){
                            Tipcommand[tip_i]=String.valueOf(str.charAt(i));
                            tip_i++;
                        }
                        vbegin=false;
                    }
                }else {
                    input.setText("0");
                    vbegin=true;
                    tip_i=0;
                    tip_lock=true;
                    tip.setText("欢迎使用");
                }
                equals_flag=true;
            }
            if(tip_i>0)
                TipChecker(Tipcommand[tip_i-1],command);
            else if(tip_i==0){
                TipChecker("#",command);
            }
            if("0123456789.()sincostanlnlogn!+-x/√^".indexOf(command)!=-1&&tip_lock){
                Tipcommand[tip_i]=command;
                tip_i++;
            }
            if("0123456789.()sincostanlnlogn!+-x/√^".indexOf(command)!=-1
                                 &&tip_lock){
                print(command);
            }else if(command.compareTo("DRG")==0&&tip_lock){
                if(drg_flag==true){
                    drg_flag=false;
                    _drg.setText("  RAD");
                }else {
                    drg_flag=true;
                    _drg.setText("  DEG");
                }
            }else if(command.compareTo("Bksp")==0&&equals_flag){
                if(TTO(str)==3){
                    if(str.length()>3)
                        input.setText(str.substring(0,str.length()-3));
                    else if(str.length()==3){
                        input.setText("0");
                        vbegin=true;
                        tip_i=0;
                        tip.setText("欢迎使用");
                    }
                }else if(TTO(str)==2){
                    if (str.length()>2)
                        input.setText(str.substring(0,str.length()-2));
                    else if(str.length()==2){
                        input.setText("0");
                        vbegin=true;
                        tip_i=0;
                        tip.setText("欢迎使用");
                    }
                }else if(TTO(str)==1){
                    if(right(str)){
                        if(str.length()>1)
                            input.setText(str.substring(0,str.length()-1));
                        else if(str.length()==1){
                            input.setText("0");
                            vbegin=true;
                            tip_i=0;
                            tip.setText("欢迎使用");
                        }
                    }else {
                        input.setText("0");
                        vbegin=true;
                        tip_i=0;
                        tip.setText("欢迎使用");
                    }
                }
                if(input.getText().toString().compareTo("-")==0||equals_flag==false){
                    input.setText("0");
                    vbegin=true;
                    tip_i=0;
                    tip.setText("欢迎使用");
                }
                tip_lock=true;
                if(tip_i>0)
                    tip_i--;
            }else if(command.compareTo("Bksp")==0&&equals_flag==false){
                input.setText("0");
                vbegin=true;
                tip_i=0;
                tip_lock=true;
                tip.setText("欢迎使用");
            }else if(command.compareTo("C")==0){
                input.setText("0");
                vbegin=true;
                tip_i=0;
                tip_lock=true;
                equals_flag=true;
                tip.setText("欢迎使用");
            }else if(command.compareTo("MC")==0){
                mem.setText("0");
            }else if(command.compareTo("exit")==0){
                System.exit(0);
            }else if(command.compareTo("=")==0&&tip_lock&&right(str)&&equals_flag){
                tip_i=0;
                tip_lock=false;
                equals_flag=false;
                str_old=str;
                str=str.replaceAll("sin","s");
                str=str.replaceAll("cos","c");
                str=str.replaceAll("tan","t");
                str=str.replaceAll("log","g");
                str=str.replaceAll("ln","l");
                str=str.replaceAll("n!","!");
                vbegin=true;
                str_new=str.replaceAll("-","-1x");
                new calc().process(str_new);
            }
            tip_lock=true;
        }
    };

    private void TipChecker(String tipcommand1, String tipcommand2) {
        int Tipcode1=0, Tipcode2=0;
        int tiptype1=0,tiptype2=0;
        int bracket=0;
        if(tipcommand1.compareTo("#")==0&&(tipcommand2.compareTo("/")==0||tipcommand2.compareTo("x")==0||tipcommand2.compareTo("+")==0||tipcommand2.compareTo(")")==0
        ||tipcommand2.compareTo("√")==0||tipcommand2.compareTo("^")==0)){
            Tipcode1=-1;
        }
        else if(tipcommand1.compareTo("#")!=0){
            if(tipcommand1.compareTo("(")==0){
                tiptype1=1;
            }else if(tipcommand1.compareTo(")")==0){
                tiptype1=2;
            }else if(tipcommand1.compareTo(".")==0){
                tiptype1=3;
            }else if("0123456789".indexOf(tipcommand1)!=-1){
                tiptype1=4;
            }else if("+-x/".indexOf(tipcommand1)!=-1){
                tiptype1=5;
            }else if("√^".indexOf(tipcommand1) != -1) {
                tiptype1=6;
            }else if("sincostanloglnn!".indexOf(tipcommand1) != -1){
                tiptype1=7;
            }
            if(tipcommand2.compareTo("(")==0){
                tiptype2=1;
            }else if(tipcommand2.compareTo(")")==0){
                tiptype2=2;
            }else if(tipcommand2.compareTo(".")==0){
                tiptype2=3;
            }else if("0123456789".indexOf(tipcommand2)!=-1){
                tiptype2=4;
            }else if("+-x/".indexOf(tipcommand2)!=-1){
                tiptype2=5;
            }else if("√^".indexOf(tipcommand2) != -1) {
                tiptype2=6;
            }else if("sincostanloglnn!".indexOf(tipcommand2) != -1){
                tiptype2=7;
            }
            switch (tiptype1){
                case 1:
                    if(tiptype2==2||(tiptype2==5&&tipcommand2.compareTo("-")!=0)||tiptype2==6)
                        Tipcode1=1;
                    break;
                case 2:
                    if(tiptype2==1||tiptype2==3||tiptype2==4||tiptype2==7)
                        Tipcode1=2;
                    break;
                case 3:
                    if(tiptype2==1||tiptype2==7)
                        Tipcode1=3;
                    if(tiptype2==3)
                        Tipcode1=8;
                    break;
                case 4:
                    if(tiptype2==1||tiptype2==7)
                        Tipcode1=4;
                    break;
                case 5:
                    if(tiptype2==2||tiptype2==5||tiptype2==6)
                        Tipcode1=5;
                    break;
                case 6:
                    if(tiptype2==2||tiptype2==5||tiptype2==6||tiptype2==7)
                        Tipcode1=6;
                    break;
                case 7:
                    if(tiptype2==2||tiptype2==5||tiptype2==6||tiptype2==7)
                        Tipcode1=7;
                    break;
            }
        }
        if(Tipcode1==0&&tipcommand2.compareTo(".")==0){
            int tip_point=0;
            for(int i=0;i<tip_i;i++){
                if(Tipcommand[i].compareTo(".")==0){
                    tip_point++;
                }
                if(Tipcommand[i].compareTo("sin")==0||Tipcommand[i].compareTo("cos")==0||
                        Tipcommand[i].compareTo("tan")==0||Tipcommand[i].compareTo("log")==0||
                        Tipcommand[i].compareTo("ln")==0||Tipcommand[i].compareTo("n!")==0||
                        Tipcommand[i].compareTo("√")==0||Tipcommand[i].compareTo("^")==0||
                        Tipcommand[i].compareTo("/")==0||Tipcommand[i].compareTo("x")==0||
                        Tipcommand[i].compareTo("-")==0||Tipcommand[i].compareTo("+")==0||
                        Tipcommand[i].compareTo("(")==0||Tipcommand[i].compareTo(")")==0){
                    tip_point=0;
                }
            }
            tip_point++;
            if(tip_point>1){
                Tipcode1=8;
            }
        }
        if(Tipcode1==0&&tipcommand2.compareTo(")")==0){
            int tip_right_bracket=0;
            for(int i=0;i<tip_i;i++){
                if(Tipcommand[i].compareTo("(")==0){
                    tip_right_bracket++;
                }
                if(Tipcommand[i].compareTo(")")==0){
                    tip_right_bracket--;
                }
            }
            if(tip_right_bracket==0){
                Tipcode1=10;
            }
        }
        if(Tipcode1==0&&tipcommand2.compareTo("=")==0){
            int tip_bracket=0;
            for(int i=0;i<tip_i;i++){
                if(Tipcommand[i].compareTo("(")==0){
                    tip_bracket++;
                }
                if(Tipcommand[i].compareTo(")")==0){
                    tip_bracket--;
                }
            }
            if(tip_bracket>0){
                Tipcode1=9;
                bracket=tip_bracket;
            }else if(tip_bracket==0){
                if("√^sincostanloglnn!".indexOf(tipcommand1)!=-1){
                    Tipcode1=6;
                }
                if("+-x/".indexOf(tipcommand1)!=-1){
                    Tipcode1=5;
                }
            }
        }
        if(tipcommand2.compareTo("MC")==0) Tipcode2=1;
        if(tipcommand2.compareTo("C")==0) Tipcode2=2;
        if(tipcommand2.compareTo("DRG")==0) Tipcode2=3;
        if(tipcommand2.compareTo("Bksp")==0) Tipcode2=4;
        if(tipcommand2.compareTo("sin")==0) Tipcode2=5;
        if(tipcommand2.compareTo("cos")==0) Tipcode2=6;
        if(tipcommand2.compareTo("tan")==0) Tipcode2=7;
        if(tipcommand2.compareTo("log")==0) Tipcode2=8;
        if(tipcommand2.compareTo("ln")==0) Tipcode2=9;
        if(tipcommand2.compareTo("n!")==0) Tipcode2=10;
        if(tipcommand2.compareTo("√")==0) Tipcode2=11;
        if(tipcommand2.compareTo("^")==0) Tipcode2=12;
        TipShow(bracket,Tipcode1,Tipcode2,tipcommand1,tipcommand2);
    }

    private void TipShow(int bracket, int tipcode1, int tipcode2, String tipcommand1, String tipcommand2) {
        String tipmessage="";
        if(tipcode1!=0)
            tip_lock=false;
        switch (tipcode1){
            case -1:
                tipmessage=tipcommand2+"不能作为第一个算符 \n";
                break;
            case 1:
                tipmessage=tipcommand1+"后应该输入：数字/(/./-/函数 \n";
                break;
            case 2:
                tipmessage=tipcommand1+"后应该输入:)/算符 \n";
                break;
            case 3:
                tipmessage=tipcommand1+"后应该输入:)/数字/算符 \n";
                break;
            case 4:
                tipmessage=tipcommand1+"后应输入:)/./数字/算符 \n";
                break;
            case 5:
                tipmessage=tipcommand1+"后应输入：（/./数字/函数\n";
                break;
            case 6:
                tipmessage=tipcommand1+"后应输入：（/./数字\n";
                break;
            case 7:
                tipmessage=tipcommand1+"后应输入：（/./数字\n";
                break;
            case 8:
                tipmessage=tipcommand1+"小数点重复\n";
                break;
            case 9:
                tipmessage=tipcommand1+"不能计算,缺少"+bracket+"个）";
                break;
            case 10:
                tipmessage=tipcommand1+"不需要 ）";
                break;
        }
        switch (tipcode2){
            case 1:
                tipmessage=tipmessage+"[MC 用法：清除记忆 MEM]";
                break;
            case 2:
                tipmessage=tipmessage+"[C 用法：归零]";
                break;
            case 3:
                tipmessage=tipmessage+"[DRG 用法：选择DEG或RAD]";
                break;
            case 4:
                tipmessage=tipmessage+"[Bksp 用法：退格]";
                break;
            case 5:
                tipmessage=tipmessage+"[sin 用法：\n"+"DEG:sin30=0.5   RAD:sin1=0.84\n"+
                "注;与其他函数一起使用时要加括号，如：\n"+"sin(cos45),而不是sincos45";
                break;
            case 6:
                tipmessage=tipmessage+"[cos 用法：\n"+"DEG:cos60=0.5   RAD:cos1=0.54\n"+
                        "注;与其他函数一起使用时要加括号，如：\n"+"cos(sin45),而不是cossin45";
                break;
            case 7:
                tipmessage=tipmessage+"[tan 用法：\n"+"DEG:tan45=1   RAD:tan1=1.55\n"+
                        "注;与其他函数一起使用时要加括号，如：\n"+"tan(sin45),而不是tansin45";
                break;
            case 8:
                tipmessage=tipmessage+"[log 用法：\n"+"DEG:log10=1\n"+
                        "注;与其他函数一起使用时要加括号，如：\n"+"log(sin45),而不是logsin45";
                break;
            case 9:
                tipmessage=tipmessage+"[ln 用法：\n"+"DEG:lne=1\n"+
                        "注;与其他函数一起使用时要加括号，如：\n"+"ln(sin45),而不是lnsin45";
                break;
            case 10:
                tipmessage=tipmessage+"[n! 用法：\n"+"如：n!3=3x2x1=6\n"+
                        "注;与其他函数一起使用时要加括号，如：\n"+"n!(log1000),而不是n!log1000";
                break;
            case 11:
                tipmessage=tipmessage+"[√ 用法：\n"+"如：27开3次根为27√3=3\n"+
                        "注;与其他函数一起使用时要加括号，如：\n"+"（函数）√（函数),(n!3)√(log100)=2.45";
                break;
            case 12:
                tipmessage=tipmessage+"[^ 用法：\n"+"如：2的3次方为2^3\n"+
                        "注;与其他函数一起使用时要加括号，如：\n"+"（函数）^（函数),(n!3)^(log100)=36";
                break;
        }
        tip.setText(tipmessage);
    }

    private boolean right(String str) {
        int i=0;
        for(i=0;i<str.length();i++){
            if(str.charAt(i)!='0'&&str.charAt(i)!='1'&&str.charAt(i)!='2'&&str.charAt(i)!='3'&&str.charAt(i)!='4'&&str.charAt(i)!='5'
                    &&str.charAt(i)!='6'&&str.charAt(i)!='7'&&str.charAt(i)!='8'&&str.charAt(i)!='9'&&str.charAt(i)!='.'&&str.charAt(i)!='-'
            &&str.charAt(i)!='+'&&str.charAt(i)!='x'&&str.charAt(i)!='/'&&str.charAt(i)!='√'&&str.charAt(i)!='^'&&str.charAt(i)!='s'&&str.charAt(i)!='i'
                    &&str.charAt(i)!='n'&&str.charAt(i)!='c'&&str.charAt(i)!='o'&&str.charAt(i)!='t'&&str.charAt(i)!='a'&&str.charAt(i)!='l'&&str.charAt(i)!='g'
                    &&str.charAt(i)!='('&&str.charAt(i)!=')'&&str.charAt(i)!='!')
            break;
        }
        if(i==str.length()){
            return true;
        }else {
            return false;
        }
    }

    private int TTO(String str) {
        if((str.charAt(str.length()-1)=='n'&&
                str.charAt(str.length()-2)=='i'&&
                str.charAt(str.length()-3)=='s')||
                (str.charAt(str.length()-1)=='s'&&
                        str.charAt(str.length()-2)=='o'&&
                        str.charAt(str.length()-3)=='c')||
                (str.charAt(str.length()-1)=='n'&&
                        str.charAt(str.length()-2)=='a'&&
                        str.charAt(str.length()-3)=='t')||
                (str.charAt(str.length()-1)=='g'&&
                        str.charAt(str.length()-2)=='o'&&
                        str.charAt(str.length()-3)=='l')){
            return  3;
        }else if ((str.charAt(str.length()-1)=='n'&&
                str.charAt(str.length()-2)=='l')||
                (str.charAt(str.length()-1)=='!'&&
                        str.charAt(str.length()-2)=='n')){
            return 2;
        }else {return 1;}
    }

    private void print(String str) {
        if(vbegin)
            input.setText(str);
        else
            input.append(str);
        vbegin=false;
    }

    public class calc{

        public calc(){

        }
        final int MAXLEN=500;
        public void process(String str) {
            int weightPlus=0,topOp=0,
                    topNum=0,flag=1,weightTemp=0;
            int weight[];
            double number[];
            char ch,ch_gai,operator[];
            String num;
            weight = new int[MAXLEN];
            number=new double[MAXLEN];
            operator=new char[MAXLEN];
            String expression=str;
            StringTokenizer expToken=new StringTokenizer(expression,"+-x/()sctgl!√^");
            int i=0;
            while (i<expression.length()){
                ch=expression.charAt(i);
                if(i==0){
                    if(ch=='-')
                        flag=-1;
                }else if(expression.charAt(i-1)=='('&&ch=='-')
                    flag=-1;
                if(ch<='9'&&ch>='0'||ch=='.'||ch=='E'){
                    num=expToken.nextToken();
                    ch_gai=ch;
                    Log.e("goujs", ch + "---->" + i);
                    while (i<expression.length()&&(ch_gai<='9'&&ch_gai>='0'||ch_gai=='.'||ch_gai=='E')){
                        ch_gai=expression.charAt(i++);
                        Log.e("goujs","i的值为："+i);
                    }
                    if(i>=expression.length()) i-=1;else {i-=2;}
                    if(num.compareTo(".")==0) number[topNum++]=0;else {
                        number[topNum++]=Double.parseDouble(num)*flag;
                        flag=1;
                    }
                }
                if(ch=='(') weightPlus+=4;
                if(ch==')') weightPlus-=4;
                if(ch=='-'&&flag==1||ch=='+'||ch=='x'||ch=='/'||ch=='s'||ch=='c'||ch=='t'||
                        ch=='g'||ch=='l'||ch=='!'||ch=='√'||ch=='^'){
                    switch (ch){
                        case '+':
                        case '-':
                            weightTemp=1+weightPlus;
                            break;
                        case 'x':
                        case '/':
                            weightTemp=2+weightPlus;
                            break;
                        case 's':
                        case 'c':
                        case 't':
                        case 'g':
                        case 'l':
                        case '!':
                            weightTemp=3+weightPlus;
                            break;
                        default:
                            weightTemp=4+weightPlus;
                            break;
                    }
                    if(topOp==0||weight[topOp-1]<weightTemp){
                        weight[topOp]=weightTemp;
                        operator[topOp]=ch;
                        topOp++;
                    }else {
                        while (topOp>0&&weight[topOp-1]>=weightTemp){
                            switch (operator[topOp-1]){
                                case '+':
                                    number[topNum-2]+=number[topNum-1];
                                    break;
                                case '-':
                                    number[topNum-2]-=number[topNum-1];
                                    break;
                                case 'x':
                                    number[topNum-2]*=number[topNum-1];
                                    break;
                                case '/':
                                    number[topNum-2]/=number[topNum-1];
                                    break;
                                case '√':
                                    if(number[topNum-1]==0||(number[topNum-2]<0&&number[topNum-1]%2==0)){
                                        showError(2,str_old);
                                        return;
                                    }
                                    number[topNum-2]=Math.pow(number[topNum-2],1/number[topNum-1]);
                                    break;
                                case '^':
                                    number[topNum-2]=Math.pow(number[topNum-2],number[topNum-1]);
                                    break;
                                case 's':
                                    if(drg_flag==true){
                                        number[topNum-1]=Math.sin((number[topNum-1]/180)*pi);
                                    }else {
                                        number[topNum-1]=Math.sin(number[topNum-1]);
                                    }
                                    topNum++;
                                    break;
                                case 'c':
                                    if(drg_flag==true){
                                        number[topNum-1]=Math.cos((number[topNum-1]/180)*pi);
                                    }else {
                                        number[topNum-1]=Math.cos(number[topNum-1]);
                                    }
                                    topNum++;
                                    break;
                                case 't':
                                    if(drg_flag==true){
                                        if((Math.abs(number[topNum-1])/90)%2==1){
                                            showError(2,str_old);
                                            return;
                                        }
                                        number[topNum-1]=Math.tan((number[topNum-1]/180)*pi);
                                    }else{
                                        if((Math.abs(number[topNum-1])/(pi/2))%2==1){
                                            showError(2,str_old);
                                            return;
                                        }
                                        number[topNum-1]=Math.tan(number[topNum-1]);
                                    }
                                    topNum++;
                                    break;
                                case 'g':
                                    if(number[topNum-1]<=0){
                                        showError(2,str_old);
                                        return;
                                    }
                                    number[topNum-1]=Math.log10(number[topNum-1]);
                                    topNum++;
                                    break;
                                case 'l':
                                    if(number[topNum-1]<=0){
                                        showError(2,str_old);
                                        return;
                                    }
                                    number[topNum-1]=Math.log(number[topNum-1]);
                                    topNum++;
                                    break;
                                case '!':
                                    if(number[topNum-1]>170){
                                        showError(3,str_old);
                                        return;
                                    }else if(number[topNum-1]<0){
                                        showError(2,str_old);
                                        return;
                                    }
                                    number[topNum-1]=N(number[topNum-1]);
                                    topNum++;
                                    break;
                            }
                            topNum--;
                            topOp--;
                        }
                        weight[topOp]=weightTemp;
                        operator[topOp]=ch;
                        topOp++;
                    }
                }
                i++;
            }
            while (topOp>0){
                switch (operator[topOp-1]){
                    case '+':
                        number[topNum-2]+=number[topNum-1];
                        break;
                    case '-':
                        number[topNum-2]-=number[topNum-1];
                        break;
                    case 'x':
                        number[topNum-2]*=number[topNum-1];
                        break;
                    case '/':
                        if(number[topNum-1]==0){
                            showError(1,str_old);
                            return;
                        }
                        number[topNum-2]/=number[topNum-1];
                        break;
                    case '√':
                        if(number[topNum-1]==0||(number[topNum-2]<0&&number[topNum-1]%2==0)){
                            showError(2,str_old);
                            return;
                        }
                        number[topNum-2]=Math.pow(number[topNum-2],1/number[topNum-1]);
                        break;
                    case '^':
                        number[topNum-2]=Math.pow(number[topNum-2],number[topNum-1]);
                        break;
                    case 's':
                        if(drg_flag==true){
                            number[topNum-1]=Math.sin((number[topNum-1]/180)*pi);
                        }else{
                            number[topNum-1]=Math.sin(number[topNum-1]);
                        }
                        topNum++;
                        break;
                    case 'c':
                        if(drg_flag==true){
                            number[topNum-1]=Math.cos((number[topNum-1]/180)*pi);
                        }else{
                            number[topNum-1]=Math.cos(number[topNum-1]);
                        }
                        topNum++;
                        break;
                    case 't':
                        if(drg_flag==true){
                            if((Math.abs(number[topNum-1])/90)%2==1){
                                showError(2,str_old);
                                return;
                            }
                            number[topNum-1]=Math.tan((number[topNum-1]/180)*pi);
                        }else {
                            if((Math.abs(number[topNum-1])/(pi/2))%2==1){
                                showError(2,str_old);
                                return;
                            }
                            number[topNum-1]=Math.tan(number[topNum-1]);
                        }
                        topNum++;
                        break;
                    case 'g':
                        if(number[topNum-1]<=0){
                            showError(2,str_old);
                            return;
                        }
                        number[topNum-1]=Math.log(number[topNum-1]);
                        topNum++;
                        break;
                    case '!':
                        if(number[topNum-1]>170){
                            showError(3,str_old);
                            return;
                        }else if(number[topNum-1]<0){
                            showError(2,str_old);
                            return;
                        }
                        number[topNum-1]=N(number[topNum-1]);
                        topNum++;
                        break;
                }
                topNum--;
                topOp--;
            }
            if(number[0]>7.3E306){
                showError(3,str_old);
                return;
            }
            input.setText(String.valueOf(FP(number[0])));
            tip.setText("计算完毕，要继续请按归零键C");
            mem.setText(str_old+"="+String.valueOf(FP(number[0])));
        }

        private double N(double n) {
            int i=0;
            double sum=1;
            for(i=1;i<=n;i++){
                sum=sum*i;
            }
            return sum;
        }

        public double FP(double n) {
            /*NumberFormat format = NumberFormat.getInstance();
            format.setMaximumFractionDigits(18);*/
            DecimalFormat format=new DecimalFormat(".###########");
            Log.d("aa",String.valueOf(n));
            return Double.parseDouble(format.format(n));
        }


        private void showError(int code, String str) {
            String message="";
            switch (code){
                case 1:
                    message="零不能作除数";
                    break;
                case 2:
                    message="函数格式错误";
                    break;
                case 3:
                    message="值太大了，超出范围";
            }
            input.setText("\""+str+"\""+":"+message);
            tip.setText(message+"\n"+"计算完毕，要继续请按归零键C");

        }
    }
}
