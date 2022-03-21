public class DrawTriangle{
    public static void main(String[] args) {
        drawTriangle(10);  
    }

    public static void drawTriangle(int rowToDraw){
        for(int row=1;row<=rowToDraw;row++){
            for (int column=1;column<=row;column++){
                System.out.print("*");
            }
            System.out.println("");
        }
    }

}