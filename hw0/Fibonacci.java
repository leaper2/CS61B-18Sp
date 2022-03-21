public class Fibonacci {
    
    public static void main(String[] args) {

        int n=9;
        System.out.printf("the %dth fibonacci is %d\n", n,fib(n));
    }

    public static int fib(int n){
        if (n<=1) {
            return 0;
        }else if (n==2){
            return 1;
        }
        int current=fib(n-1)+fib(n-2);
        return current;
        //the expressions of the last two statement evaluate to the same
        //combine into one: 
        //return fib(n-1)+fib(n-2);
    }
}
