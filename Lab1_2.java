/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1_2;

/**
 *
 * @author Amos
 */
public class Lab1_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HugeInteger int1 = new HugeInteger("25");
        HugeInteger int2 = new HugeInteger("11");
//        HugeInteger int3 = int1.add(int2);
//        HugeInteger int4 = int1.subtract(int2);
//        HugeInteger int5 = int1.multiply(int2);
        HugeInteger int6 = int1.divide(int2);
        System.out.println("Integer 1 =" + int1);
        System.out.println("Integer 2 =" + int2);
        System.out.println("Result =" + int6);
//        System.out.println(int4);
//        System.out.println(int5);
//        System.out.println(int6);
        
        
//                HugeInteger huge1, huge2, huge3;
//                int hi;
//        long startTime, endTime;
//        double runTime=0;
//        int n=10000, MAXNUMINTS = 100, MAXRUN=300;
//        for (int numInts=0; numInts < MAXNUMINTS; numInts++){
//            huge1 = new HugeInteger(n); //creates a random integer of n digits
//            huge2 = new HugeInteger(n); //creates a random integer of n digits
//            startTime = System.currentTimeMillis();
//            for(int numRun=0; numRun < MAXRUN; numRun++)
//                { hi = huge1.compareTo(huge2); }
//            endTime = System.currentTimeMillis();
//            runTime +=(double) (endTime - startTime)/((double) MAXRUN);
//        }
//        runTime = runTime/((double)MAXNUMINTS);
//        System.out.println(runTime);

    }
    
}



        

