import java.util.*;




public class Parser{

    private static Deque<Double> numQ=new ArrayDeque<>();     //To keep track of the intermediate variables
    private static Stack<Integer> opStack=new Stack<>();      //To keep track of the operators
  
    private static double calc(double a, double b, int id){   //To calculate value according to the IDs.
        switch(id){
            case 1:return a-b;
            case 2:return a+b;
            case 3:return a*b;
            case 4:return a/b;
            default:return -69;
        }
    }

    public static void parse(double arr[], int id[]){      //public function to take input and process it
        int len=arr.length;
        // validate(arr,id);
        for(int x=0; x<len; x++){
            if(id[x]==0)                                      //If a number
                numQ.addLast(arr[x]);
            else if(id[x]==12)                                //If a '('
                opStack.push(id[x]);
            else if(id[x]==13){                               //If a ')', assumed preceded by a '('
                while(opStack.peek()!=12)
                    perform();
                opStack.pop();
            }
            else if(!opStack.isEmpty() && opStack.peek()!=12 && opStack.peek()>id[x]){    //If the precedence is higher perform the last operator operation.
                perform();
                opStack.push(id[x]);
            }
            else
                opStack.push(id[x]);
        }
        while(!opStack.isEmpty())   //To finish all operations that were left in the operator stack.
            perform();
    }

    private static void perform(){
        double a=numQ.removeLast(), b=numQ.isEmpty()?0:numQ.removeLast();
        int c=opStack.pop();
        numQ.addLast(calc(b,a,c));
    }

    public static void main(String[] args) {
        double[] arr={16,0, 0,4,0,2,0,4, 0,0, 0,3,0,4, 0,0,1};      //    16/(4+2*4)*(3/4)-1
        int[]     id={ 0,4,12,0,2,0,3,0,13,3,12,0,4,0,13,1,0};
        parse(arr,id);
        System.out.println("Ans: "+numQ.pop());
    }
}
