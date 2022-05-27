import java.util.*;
public class Parser{
    double[] numStack;
    int[] opStack;
    int nsSize=0, x, opSize=0, len;
    int[] id;
    double[] arr;
    double calc(double a, double b, int id){   //To calculate value according to the IDs.
        switch(id){
            case 1:return b-a;
            case 2:return a+b;
            case 3:return a*b;
            case 4:return b/a;
            /*
            *For unary, arr[x] holds the metadata, hence check here it for extra functionality.
            */
            case 5:                                         //sin, sinh, asin
                resolveUnary();Math.sin(a);
            case 6:                                         //cos, cosh, acos
                resolveUnary();Math.cos(a);
            case 7:                                         //tan, tanh, atan
                resolveUnary();Math.tan(a);
            case 8:                                         //log10, ln, logb(a)
                resolveUnary();return Math.log10(a);

            //case 9:return fact(a);     factorial function call!
            case 10:return Math.pow(b,1/a);
            case 11:return Math.pow(b,a);
            case 12:resolveUnary();return a;
            default:return 69.6969696969;     //Nice
        }
    }
    boolean isUnary(int id){                 //Helper to know if the ID is a Unary function!
        return (id>=5 && id<=9) || id==12;
    }
    boolean isUnaryEnd(int id){              //Basically boolean for ')'.
        return id==13;
    }

    /*
    *Call resolveUnary for every unary function in resolveUnary.
    *
    *This call is necessary to do this:
    *input: 3(2)
    *required output: 6
    *output without calling: 3, 2 (in the stack)
    *
    *pushes a multiplication operator in the stack where required
    */
    void resolveUnary(){
        if(opSize<nsSize)
            push2(3);
        if(x<len-1 && (isUnary(x+1)||arr[x+1]==0))
            push2(3);
    }

    private void parse(double a[], int b[]){
        arr=a;
        id=b;
        len=arr.length;
        numStack=new double[len];
        opStack=new int[len];
        opSize=0;
        nsSize=0;
        for(x=0; x<len; x++){
            if(id[x]==0)                                    //If element is a number
                push1(arr[x]);
            else if(isUnary(id[x]))                         //If a paranthesis or start of Unary funcs.
                push2(id[x]);
            else if(isUnaryEnd(id[x])){                     //End of a Unary function or paranthesis
                while(opSize!=0 && !isUnary(id[x]))
                    perform();
                if(opSize!=0)
                    perform();
            }
            /*
            *If the precedence is higher perform the last operation in stack.
            *Then insert the current operator at id[x] in the stack to not loose the current operator.
            *I did this mistake like 20 times while writing the code.
            */
            else if(opSize!=0 && !isUnary(peek()) && peek()>id[x]){
                perform();
                push2(id[x]);
            }
            else
                push2(id[x]);
//             printArrs();
        }
        while(opSize!=0)   //To finish all operations left in the operator stack.
            perform();
    }

//     private void printArrs(){
//         for(int x=0; x<nsSize; x++)
//             System.out.print(numStack[x]+", ");
//             System.out.println();
//         for(int x=0; x<opSize; x++)
//             System.out.print(opStack[x]+", ");
//         System.out.println();
//     }

    private void perform(){
        int c=pop2();
        double a=pop1(), b= (nsSize==0 || isUnary(c))? 0:pop1();
        push1(calc(a,b,c));
//         printArrs();
    }
    void push1(double a){
        numStack[nsSize++]=a;
    }
    void push2(int a){
        opStack[opSize++]=a;
    }
    double pop1(){
        return numStack[--nsSize];
    }
    int pop2(){
        return opStack[--opSize];
    }
    double peek1(){
        return numStack[nsSize-1];
    }
    int peek(){
        return opStack[opSize-1];
    }

    public static double degToRad(double deg){
        return deg*Math.PI/180;
    }
    public static double radToDeg(double rad){
        return rad*180/Math.PI;
    }
    public static void main(String[] args) {
        if(args.length>0){
            System.out.println("CLI-support is not yet added because string-to-input function is not available.");
            //string-to-input func call.
            //remove this return statement after support added.
            return;
        }
        Parser p=new Parser();
        double arr[]={3,0,degToRad(90), 0};         //5sin90)
        int ids[]=   {0,5,   0,13};
        p.parse(arr,ids);
        System.out.println("ans:"+p.numStack[0]);
    }
}
