import java.util.Arrays;
import java.util.HashSet;

/*
*This is an Example of usage of the class Parser.
*These steps are necessary:
*1.extend the Parser
*2.Override calc method
*3.Override isUnary
*4.Override isUnaryEnd
*/

class defaultImplementation extends Parser{
    private final HashSet<Object> unarySet;
    private final int unaryEnd;
    defaultImplementation(){
        unarySet=new HashSet<>(Arrays.asList(5, 6, 7, 8, 9, 12));
        unaryEnd = 13;
    }
    @Override
    double calc(double a, double b, int id){
        switch(id){
            case 1:return b-a;
            case 2:return a+b;
            case 3:return a*b;
            case 4:return b/a;
            case 5:Math.sin(a);
            case 6:Math.cos(a);
            case 7:Math.tan(a);
            case 8:return Math.log10(a);
            case 9:return fact((int)a);
            case 10:return Math.pow(b,1/a);
            case 11:return Math.pow(b,a);
            case 12:return a;
            default:return 69.6969696969;           //Because it's Nice... You might want to throw an Exception instead.
        }
    }
    @Override
    boolean isUnary(int id) {
        return unarySet.contains(id);
    }
    @Override
    boolean isUnaryEnd(int id) {
        return unaryEnd==id;
    }
    private static double fact(int a){
        double ans=1;
        for(;a>0;a--)
            ans*=a;
        return ans;
    }
    public static void main(String[] args){
        if(args.length>0){
            System.out.println("CLI support not added yet");
            return;
        }
        double[] arr ={4,0,3,0};             //4(3!)           Since ! is unary, represented as 4!3)
        int[] ids =   {0,9,0,13};
        defaultImplementation ob=new defaultImplementation();
        ob.parse(arr,ids);
        System.out.println("ans:"+ob.numStack[0]);
    }
}
