import java.util.*;




public class Parser{

    Deque<Double> numQ=new ArrayDeque<>();
    Stack<Integer> opStack=new Stack<>();

    double calc(double a, double b, int id){   //To calculate value according to the IDs.
        switch(id){
            case 1:return a+b;
            case 2:return b-a;
            case 3:return a*b;
            case 4:return b/a;
            case 5:resolveUnary();return Math.sin(a);
            case 6:resolveUnary();return Math.cos(a);
            case 7:resolveUnary();return Math.tan(a);
            case 8:resolveUnary();return Math.log(a);
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
    void validate(double[] arr, int[] id){}

    /*
    *Call resolveUnary for every unary function in calc.
    *
    *This call is necessary to do this:
    *input: 3(2)
    *required output: 6
    *output without calling: 3, 2 (in the stack)
    *
    *pushes a multiplication operator in the stack where required
    */
    void resolveUnary(){
        if((opStack.isEmpty() && !numQ.isEmpty()) || (!opStack.isEmpty() && isUnaryEnd(opStack.peek())))
            opStack.push(3);
    }

    private void parse(double[] arr, int[] id){            //Processing starts here.
        validate(arr,id);
        int len=arr.length;
        for(int x=0; x<len; x++){
            if(id[x]==0)                                    //If element is a number
                numQ.addLast(arr[x]);
            else if(isUnary(id[x]))                         //If start of a Unary function.
                opStack.push(id[x]);
            else if(isUnaryEnd(id[x])){                     //End of a Unary function
                while(!opStack.isEmpty() && (opStack.peek()<5 || opStack.peek()!=11))
                    perform();
                if(!opStack.isEmpty())
                    perform();
            }
            /*
            *If the precedence is higher perform the last operation in stack.
            *Then insert the current operator at id[x] in the stack to not loose the current operator.
            *I did this mistake like 20 times while writing the code.
            */
            else if(!opStack.isEmpty() && !isUnary(opStack.peek()) && opStack.peek()>id[x]){
                perform();
                opStack.push(id[x]);
            }
            else
                opStack.push(id[x]);
//             System.out.println(numQ.toString());
//             System.out.println(opStack.toString());
        }
        while(!opStack.isEmpty())   //To finish all operations left in the operator stack.
            perform();
    }

    private void perform(){
        int c=opStack.pop();
        double a=numQ.removeLast(), b=numQ.isEmpty()||isUnary(c)?0:numQ.removeLast();
        numQ.addLast(calc(a,b,c));
//         System.out.println(">"+numQ.toString());
//         System.out.println(">"+opStack.toString());
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
        System.out.println("Ans:"+numQ.toString());
    }
}
