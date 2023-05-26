package test.com;


import com.sun.tools.javac.Main;

public class test2 {

    static int variavel = 4;


    public test2(int variavel) {
        this.variavel = variavel;
    }

    public static void main(String[] args) {
        System.out.println("Value of x = " + variavel);
        test2 myObj = new test2(5);
        System.out.println("Value of x = " + myObj.variavel);
    }

}
