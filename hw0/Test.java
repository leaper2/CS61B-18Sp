public class Test{
     public static void main(String[] args) {
        int row;       
        for (row=1;row<=10;row++){
            // column=row;
            for(int column=0;column<row;column++){
                System.out.print("*");
            }
            System.out.print("\n");
        }
     }
}
