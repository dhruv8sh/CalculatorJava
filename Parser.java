abstract class Parser{
    protected double[] numStack;
    protected int[] opStack;
    private int x, len;
    protected int opSize=0, nsSize=0;
    protected int[] id;
    protected double[] arr;

    /*
     *Gives you two double variables to process.
     *Gives an int which you have to map to a function.
     *Return a double after operating.
     *
     *Note: If its a Unary function, {@code b=0.0;}
     */
    abstract double calc(double a, double b, int id);
    abstract boolean isUnary(int id);       //return true if unary function
    abstract boolean isUnaryEnd(int id);    //return true for parenthesis end.

    /*
     * Recommended function call for calculation(parse(String) also available).
     * Call this with the explained input format.
     * O(n) time, O(n) space.
     */
    protected void parse(double[] a, int[] b){
        arr=a;
        id=b;
        len=arr.length;
        numStack=new double[len];
        opStack=new int[len];
        opSize=0;
        nsSize=0;
        for(x=0; x<len; x++){
            if(id[x]==0)                                       //If element is a number
                push1(arr[x]);
            else if(isUnary(id[x])) {                         //If a parenthesis or start of Unary functions.
                if(x>0 && (id[x-1]==0 || isUnary(id[x-1])))     //Adds a multiply operation if no binary operator before.
                    push2(3);
                push2(id[x]);
            }
            else if(isUnaryEnd(id[x])){                       //End of a Unary function or parenthesis
                while(!isUnary(peek()))
                    perform();
                if(x<len-1 && (id[x+1]==0 || isUnary(id[x+1])))     //Adds a multiply operation if no binary operator after.
                    push2(3);
                else if(opSize!=0) {
                    perform();
                }
            }
            else if(opSize!=0 && !isUnary(peek()) && peek()>=id[x]){    //If the precedence is higher perform the last operator operation.
                perform();
                push2(id[x]);
            }
            else
                push2(id[x]);
        }
        while(opSize!=0)   //To finish all operations left in the operator stack.
            perform();
    }

    /*
     *String to double and int array.
     *Currently not implemented...
     * Will probably implement in the next commit.
     *
     * NOTE: Recommended to use parse(double[],int[]) instead if possible. Due to performance issues.
     *
     */

    protected double parse(String str){
        //process String
        //get double[]
        //get int[]
        //return parse(double[], int[])
        return 0.0;
    }

    private void printArrays(){
        for(int x=0; x<nsSize; x++)
            System.out.print(numStack[x]+", ");
        System.out.println();
        for(int x=0; x<opSize; x++)
            System.out.print(opStack[x]+", ");
        System.out.println("\n------------");
    }

    private void perform(){
        int c=pop2();
        double a=pop1(), b= isUnary(c)? 0:pop1();
        push1(calc(a,b,c));
    }

    /*
     *
     * Below are the functions to manage stack operations...
     * Functions ending in '1' work on numStack.
     * Others on opStack.
     *
     */
    protected void push1(double a){
        numStack[nsSize++]=a;
    }
    protected void push2(int a){
        opStack[opSize++]=a;
    }
    protected double pop1(){
        return numStack[--nsSize];
    }
    protected int pop2(){
        return opStack[--opSize];
    }
    protected double peek1(){
        return numStack[nsSize-1];
    }
    protected int peek(){
        return opStack[opSize-1];
    }

    protected static double degToRad(double deg){
        return deg*Math.PI/180;
    }
    protected static double radToDeg(double rad){
        return rad*180/Math.PI;
    }
}
