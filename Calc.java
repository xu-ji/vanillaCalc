import java.util.*;
import java.lang.*;

public class Calc {

  public static void main(String[] args) {
    System.out.println("Welcome to Calc!");
    System.out.println("Enter expression:");
    String expr = IOUtil.readString();

    Stack exprStack = new Stack();
    Stack opStack = new Stack();
    opStack.push('$');
    String val = "0";

    Hashtable<Character, Integer> opTable = new Hashtable<Character, Integer>();
    opTable.put('+', 1);
    opTable.put('-', 1);
    opTable.put('*', 2);
    opTable.put('/', 2);
    opTable.put('$', 0);
    opTable.put('%', 3);
    for (int i=0; i<expr.length(); i++) {
        if (isNum(expr.charAt(i))) {
           val += expr.charAt(i);
        } else {
                if (!val.equals("0")) {
                    exprStack.push(Integer.parseInt(val));
                    val = "0";
                }
                if ((opTable.get(expr.charAt(i))).hashCode()>(opTable.get(opStack.peek())).hashCode()) {
                    opStack.push(expr.charAt(i));
                }
                else {
                    eval(exprStack, opStack);
                    i--;
                }   
        }
        if (i==expr.length()-1)   
           exprStack.push(Integer.parseInt(val));
    }

    while (exprStack.size()!=1) {
      eval(exprStack, opStack);
    }

    System.out.println(exprStack.pop());

  }

  public static void eval(Stack<Integer> exprStack, Stack<Character> opStack) {
    int ex2 = exprStack.pop().hashCode();
    int ex1 = exprStack.pop().hashCode();
    char op = (char)opStack.pop().hashCode();
    switch (op) {
      case '+': exprStack.push(ex1+ex2); return;
      case '-': exprStack.push(ex1-ex2); return;
      case '*': exprStack.push(ex1*ex2); return;
      case '/': exprStack.push(ex1/ex2); return;
      case '%': exprStack.push(ex1 % ex2); return;
      default: System.out.println("Failure"); return;
    }
  }

  public static boolean isNum(char x) {
    return ('0' <= x && x <= '9');
  } 
    
}
