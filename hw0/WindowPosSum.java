public class WindowPosSum {
    public static void main(String[] args) {
        int [] a=new int []{1, -1, -1, 10, 5, -1};
        windowPosSum(a, 2);
        for (int i=0;i<a.length;i++){
            System.out.printf("%d\n", a[i]);
        } 
    }
    public static void windowPosSum(int[] a, int n){
        int [] dumb=new int[a.length];
        for (int ai =0;ai<a.length;ai++){
            dumb[ai]=a[ai];
            if (a[ai]<0) {    //corner case checking code
                continue;
            }
            // dumb[ai]=a[ai];   //if this statement arrange here after the checking code,
                                 //will make the corner case value zero
            for (int index=ai+1;index<=ai+n;index++){
    
                if (index>(a.length-1)) {      //corner case checking code
                    break;
                }
                dumb[ai]+=a[index];
            }
        } 
        for (int i=0;i<a.length;i++){
            a[i]=dumb[i];
        }

    }
}