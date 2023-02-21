import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Width: ");  // Width entry
        int width = scanner.nextInt();
        System.out.println("Enter Height");  // Height entry
        int height = scanner.nextInt();
        System.out.println("Enter the n:");  // Number of dots entry
        int n = scanner.nextInt();
        double[][] matrix = createDot(n,width,height);  // Creating dot matrix


        System.out.println("X    "+" Y   ");    // Printing dots
        for(int i = 0;i<n;i++){
            String tempx = String.format("%.2f",matrix[i][0]); String tempy = String.format("%.2f",matrix[i][1]);
            System.out.printf("%10s",tempx+"  "+tempy);
            System.out.println();
        }

        double[][] DistanceMatrix = DM(n,matrix);   // Creating distance matrix

        System.out.print("  ");  // Printing Distance Matrix
        for(int i = 0;i<n;i++){
            System.out.printf("%6d |",i);
        }
        System.out.println();

        for(int i = 0;i<n;i++){
            System.out.printf("%-3d| ",i);
            for(int j = 0;j<n;j++){
                String f = String.format("%.2f",DistanceMatrix[i][j]);
                System.out.printf("%-7s ",f);
            }
            System.out.println();
        }

        System.out.println();
        travelDot(n,DistanceMatrix);  // using travelDot function
    }

    public static void travelDot(int n,double[][] DistanceM){ // Creating random dot and
         Random rn = new Random();
         int dot = rn.nextInt(n);   // creating random dot for travel

         int nextdot = 0;
         double totalDist = 0;

         ArrayList<Integer> dots = new ArrayList<>();  // creating arraylist for storing traveled dots

         while(true){
             double y = 999999;

             for(int i = 0;i<n;i++){    // For loop for calculating closest dot
                 if(DistanceM[dot][i]<y && DistanceM[dot][i] != 0 && !dots.contains(i)){
                     nextdot = i;
                     y = DistanceM[dot][i];   // Changing value of Y
                 }
             }

             dots.add(dot);  // Adding dot to arraylist
             totalDist += DistanceM[dot][nextdot];  // adding Distance to totalDistance

             if(y == 999999)  // if there is no dots left breaking the while loop
                 break;


             DistanceM[nextdot][dot] = 0; // Making current dot unreachable so we wont calculate same dot twice

             dot = nextdot;  // Changing current dot with next dot

             System.out.println();
        }

        System.out.println();             // Printing Total distance and dots in order
        System.out.println("Total Disance Traveled: ");
        System.out.printf("%.2f",totalDist);
        System.out.println();
        System.out.println("Dots in order: ");

        for(int k = 0;k<dots.size();k++){
            System.out.print(dots.get(k)+",");
        }
    }

    public static double[][] DM(int n,double[][] matrix){  // Distance Matrix Function
        double[][] DM = new double[n][n];

        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
               DM[i][j] = distCalc(matrix[i][0],matrix[j][0],matrix[i][1],matrix[j][1]);
            }
        }
        return DM;
    }

    public static double distCalc(double x1,double x2,double y1,double y2){  // Function that Calculates distance between two dots
        return Math.sqrt(Math.pow(y1-y2,2)+Math.pow(x1-x2,2));
    }

    public static double[][] createDot(int n, int width, int height){ // Function that creates random dots and matrix
        double[][] matrix = new double[n][2];
        Random rn = new Random();
        for(int i = 0;i<n;i++){
            matrix[i][0] = rn.nextDouble(width);
            matrix[i][1] = rn.nextDouble(height);
        }
        return matrix;
    }
}


