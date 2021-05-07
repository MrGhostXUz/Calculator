package com.example.calculator;

import java.util.ArrayList;

import static java.lang.String.valueOf;

class Hisobla {


    static String NullStep(StringBuffer tt)
    {
        ArrayList<String> ex = new ArrayList<>();
        char[] chars = new char[]{'-', '+', '/', '*', '(', ')'};
        int s = 0;

        int ls3=tt.indexOf("*-");
        System.out.println(ls3);
        if(ls3!=-1)
        {
            tt.delete(ls3+1, ls3+2);
            int th=Math.max(tt.lastIndexOf("+", ls3), tt.lastIndexOf("-", ls3));
            tt.insert(th, "-");
        }
        System.out.println(tt);

        int ls4=tt.indexOf("/-");
        System.out.println(ls4);
        if(ls4!=-1)
        {
            tt.delete(ls4+1, ls4+2);
            int th=Math.max(tt.lastIndexOf("+", ls4), tt.lastIndexOf("-", ls4));
            tt.insert(th, "-");
        }
        System.out.println(tt);

        int ls=tt.indexOf("--");
        System.out.println(ls);
        if(ls!=-1)
        {
            tt.delete(ls, ls+2);
            tt.insert(ls, "+");
        }
        System.out.println(tt);

        int ls1=tt.indexOf("-+");
        System.out.println(ls1);
        if(ls1!=-1)
        {
            tt.delete(ls1, ls1+2);
            tt.insert(ls1, "-");
        }
        System.out.println(tt);

        int ls2=tt.indexOf("+-");
        System.out.println(ls2);
        if(ls2!=-1)
        {
            tt.delete(ls2, ls2+2);
            tt.insert(ls2, "-");
        }
        System.out.println(tt);

        if(tt.substring(0, 1).equals("+"))
        {
            tt.delete(0, 1);
        }

        for (int i = 0; i < tt.length(); i++) {

            for (int j = 0; j < 6; j++) {
                if (valueOf(tt.charAt(i)).equals(valueOf(chars[j]))) {
                    ex.add(tt.substring(s, i));
                    ex.add(tt.substring(i, i + 1));
                    s = i + 1;
                }

            }
        }
        if (!(tt.indexOf("+", s) >= 0) && !(tt.indexOf("-", s) >= 0) &&
                !(tt.indexOf("*", s) >= 0) && !(tt.indexOf("/", s) >= 0)) {
            ex.add(tt.substring(s));
        }
        for(int i=0; i<ex.size(); i++)
        {
            if(ex.get(i).equals(""))
            {ex.remove(i); i--;}
        }
        for(int i=0; i<ex.size(); i++)
        {

            if(!ex.get(0).equals("-")&&(ex.get(i).equals("*")||ex.get(i).equals("/")))
            {
                if(!ex.get(i+1).equals("-")) {
                    String res = FindStep(ex.get(i - 1), ex.get(i), ex.get(i + 1));
                    ex.remove(i - 1);
                    ex.remove(i - 1);
                    ex.remove(i - 1);
                    ex.add(i - 1, res);
                    i--;
                }else if(ex.get(i+1).equals("-"))
                {
                    String res = FindStep(ex.get(i - 1), ex.get(i), (ex.get(i + 1)+ex.get(i+2)));
                    ex.remove(i - 1);
                    ex.remove(i - 1);
                    ex.remove(i - 1);
                    ex.remove(i - 1);
                    ex.add(i - 1, res);
                    i--;

                }

            }
            else if(ex.get(0).equals("-")) {
                if (ex.size()>2) {
                    String res = FindStep((ex.get(0) + ex.get(1)), ex.get(2), ex.get(3));
                    ex.remove(0);
                    ex.remove(0);
                    ex.remove(0);
                    ex.remove(0);
                    ex.add(0, res);
                    i--;
                }
                else {
                    String res = ex.get(0) + ex.get(1);
                    ex.remove(0);
                    ex.remove(0);
                    ex.add(0, res);
                    i--;
                }
            }
        }
        for(int i=0; i<ex.size(); i++)
        {
                if(ex.get(0).equals("-")&&(ex.get(i).equals("+")||ex.get(i).equals("-")))
                {
                    String res=FindStep((ex.get(i-2)+ex.get(i-1)), ex.get(i), ex.get(i+1));
                    ex.remove(i-1);
                    ex.remove(i-1);
                    ex.remove(i-1);
                    ex.remove(i-1);
                    ex.add(i-1, res);
                    i--;
                }
            else if(!ex.get(0).equals("-")&&(ex.get(i).equals("+")||ex.get(i).equals("-"))){
                    if(!ex.get(i+1).equals("-")) {
                        String res = FindStep(ex.get(i - 1), ex.get(i), ex.get(i + 1));
                        ex.remove(i - 1);
                        ex.remove(i - 1);
                        ex.remove(i - 1);
                        ex.add(i - 1, res);
                        i--;
                    }else if(ex.get(i+1).equals("-"))
                    {
                        String res = FindStep(ex.get(i - 1), ex.get(i), (ex.get(i + 1)+ex.get(i+2)));
                        ex.remove(i - 1);
                        ex.remove(i - 1);
                        ex.remove(i - 1);
                        ex.remove(i - 1);
                        ex.add(i - 1, res);
                        i--;

                    }
            }
            else if(ex.get(0).equals("-"))
            {
                String res=FindStep((ex.get(0)+ex.get(1)), ex.get(2), ex.get(3));
                ex.remove(0);
                ex.remove(0);
                ex.remove(0);
                ex.remove(0);
                ex.add(0, res);
                i--;
            }
        }
        return ex.get(0);
    }

    static int [][] FirstStep(StringBuffer fs) {
        ArrayList<Integer> list = new ArrayList<>();
        int size = 0;
        for (int i = 0; i < fs.length(); i++) {
            if (valueOf(fs.charAt(i)).equals("(")) {
                size++;

            }
        }
        int [][]qavs = new int[size][3];
        for (int m = 0; m < size;) {
            for (int i = 0; i < fs.length(); i++) {
                if (valueOf(fs.charAt(i)).equals("(")) {
                    list.add(i);
                } else if (valueOf(fs.charAt(i)).equals(")")) {
                    qavs[m][1]=list.get(list.size()-1);
                    list.remove(list.size()-1);
                    qavs[m][2]=i;
                    qavs[m][0]=qavs[m][2]-qavs[m][1];
                    m++;

                }
            }
        }

        for(int i=0; i<size; i++)
        {
            for(int j=i; j<size; j++)
            {
                int[] bufer=new int[]{qavs[i][0], qavs[i][1], qavs[i][2]};
                if(qavs[j][0]<qavs[i][0])
                {
                    qavs[i][0]=qavs[j][0];
                    qavs[i][1]=qavs[j][1];
                    qavs[i][2]=qavs[j][2];

                    qavs[j][0]=bufer[0];
                    qavs[j][1]=bufer[1];
                    qavs[j][2]=bufer[2];
                }
            }
        }


        for(int i=0; i<size; i++){
            for(int j=0; j<3; j++)
            {
                System.out.print(qavs[i][j]+" ");
            }
            System.out.println();
        }


        return qavs;

    }


    private static String FindStep(String a1, String a2, String a3) {
        String res=null;
        switch (a2) {
            case "+":
                res = valueOf(Double.parseDouble(a1) + Double.parseDouble(a3));
                break;
            case "-":
                res = valueOf(Double.parseDouble(a1) - Double.parseDouble(a3));
                break;
            case "*":
                res = valueOf(Double.parseDouble(a1) * Double.parseDouble(a3));
                break;
            case "/":
                res = valueOf(Double.parseDouble(a1) / Double.parseDouble(a3));
                break;
        }
        return res;
    }
}
