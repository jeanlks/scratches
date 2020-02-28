import java.util.*;
public class Scratch2 { 
    static class QueueCustom {
        Stack<Integer> firstStack = new Stack<>();
        Stack<Integer> secondStack = new Stack<>();
        
        public int pop(){
            while(!firstStack.isEmpty()){
                secondStack.push(firstStack.pop());
            }
            return secondStack.pop();
        }

        public void push(int n){
             firstStack.push(n);
        }
    }

    static class StackCustom { 
        Queue<Integer> firstQueue = new LinkedList<>();
        Queue<Integer> secondQueue = new LinkedList<>();

        public int pop(){
            return firstQueue.remove();
        }
        public void push(int n ){
            secondQueue.add(n);
            while(!firstQueue.isEmpty()){
                secondQueue.add(firstQueue.peek());
                firstQueue.remove();
            }
            Queue<Integer> temp = firstQueue;
            firstQueue = secondQueue;
            secondQueue = temp;
        }
    }

    public static int minNumberOfJumps(int [] array){
        int[] jumps = new int[array.length];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;
        for(int i = 1; i< array.length; i++){
            for (int j = 0; j < i; j++) {
                if(array[j] >= i - j){
                    jumps[i] = Math.min(jumps[j] + 1, jumps[i]);
                }
            }
        }
        return jumps[jumps.length - 1];
    }

    static int phoneCall(int min1, int min2_10, int min11, int s) {
        int cost = min1;
        int minutes = 0;
        while(s!=0){
            s -= cost;
            minutes++;
            if(minutes == 1)
                cost = min2_10;
            else if(minutes == 10)
                cost = min11;
        }
        return minutes;
    } 
    
    public static void main(String args[]){
        Scratch2.QueueCustom queue = new Scratch2.QueueCustom();
        queue.push(1);
        queue.push(2);
        //System.out.println(queue.pop());
        //System.out.println(queue.pop());
        Scratch2.StackCustom stack = new Scratch2.StackCustom();
        // stack.push(1);
        // stack.push(2);
        // System.out.println(stack.pop());
        // System.out.println(stack.pop());
        System.out.println(minNumberOfJumps(new int[]{2,1,1,1,2}));
        System.out.println(phoneCall(3, 1, 2, 20));
    }
}
