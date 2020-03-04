import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


//##############################################################################
public class Board1{

  int NCEL = 33;
  int NDIM = 7;

  private  int[]  x; 

  private int[][] m = {{0,1,2},{0,3,8},{1,4,9},{2,5,10},{3,4,5},{3,8,15},{4,9,16},{5,10,17},{6,7,8},{6,13,20},
                 {7,8,9},{7,14,21},{8,9,10},{8,15,22},{9,10,11},{9,16,23},{10,11,12},{10,17,24},{11,18,25},
                 {12,19,26},{13,14,15},{14,15,16},{15,16,17},{15,22,27},{16,17,18},{16,23,28},{17,18,19},{17,24,29},
                 {20,21,22},{21,22,23},{22,23,24},{22,27,30},{23,24,25},{23,28,31},{24,25,26},{24,29,32},{27,28,29},{30,31,32},
                  {2,1,0},{8,3,0},{9,4,1},{10,5,2},{5,4,3},{15,8,3},{16,9,4},{17,10,5},{8,7,6},{20,13,6},
                  {9,8,7},{21,14,7},{10,9,8},{22,15,8},{11,10,9},{23,16,9},{12,11,10},{24,17,10},{25,18,11},
                  {26,19,12},{15,14,13},{16,15,14},{17,16,15},{27,22,15},{18,17,16},{28,23,16},{19,18,17},{29,24,17},
                  {22,21,20},{23,22,21},{24,23,22},{30,27,22},{25,24,23},{31,28,23},{26,25,24},{32,29,24},{29,28,27},{32,31,30}};



  private int[][] tabxy = {{0,2},{0,3},{0,4},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{2,5},{2,6},{3,0},{3,1},{3,2},
                         {3,3},{3,4},{3,5},{3,6},{4,0},{4,1},{4,2},{4,3},{4,4},{4,5},{4,6},{5,2},{5,3},{5,4},{6,2},{6,3},{6,4}};


  private int[] pagoda = {0,1,0,
                     0,0,0,
                   -1,1,0,1,0,1,-1,
                    0,0,0,0,0,0,0,
                   -1,1,0,1,0,1,-1,
                        0,0,0,
                        0,1,0};

  uteis ut;
//---------------------------------------------
 public Board1(){  

   x = new int[NCEL];

   ut = new uteis();


 }
//----------------------------------------

 public boolean gameOver(){

  boolean termino=false;

  if(contaPegs() == 1 && x[16]==1)
    termino=true;

  return termino;

 }
//----------------------------------------

 public void printBoard(){


   String[] w = {" ","x"};

   System.out.println("--------------------------"); 
   System.out.println("      | " + w[x[0]] + " | " + w[x[1]] + " | " + w[x[2]] + " |");
   System.out.println("      | " + w[x[3]] + " | " + w[x[4]] + " | " + w[x[5]] + " |");
   System.out.println(w[x[6]] + " | " + w[x[7]] + " | " + w[x[8]] + " | " + w[x[9]] + " | " + w[x[10]]+ " | " + w[x[11]]+ " | " + w[x[12]] + "|");
   System.out.println(w[x[13]] + " | " + w[x[14]] + " | " + w[x[15]] + " | " + w[x[16]] + " | " + w[x[17]]+ " | " + w[x[18]]+ " | " + w[x[19]] + "|");
   System.out.println(w[x[20]] + " | " + w[x[21]] + " | " + w[x[22]] + " | " + w[x[23]] + " | " + w[x[24]]+ " | " + w[x[25]]+ " | " + w[x[26]] + "|");
   System.out.println("      | " + w[x[27]] + " | " + w[x[28]] + " | " + w[x[29]] + " |");
   System.out.println("      | " + w[x[30]] + " | " + w[x[31]] + " | " + w[x[32]] + " |");
   System.out.println("--------------------------");


}
//--------------------------------------------
public void inicializa() {

    int i;

    try{
       File input = new File("resta1Input.dat");

       Scanner inputFile = new Scanner(input);

       for(i=0;i<NCEL;i++)
          x[i] = inputFile.nextInt();

    }catch(IOException e) {System.out.println("Arquivo nao encontrado");}
    
 }

//--------------------------------------------
 public ArrayList<Integer> geraCandSimples(){

  ArrayList<Integer> cand = new ArrayList<>();  

  for(int i=0;i<m.length;i++)
      if(x[m[i][0]] == 1 && x[m[i][1]] == 1 && x[m[i][2]] == 0)        
        cand.add(i);       

  return cand;

}

//--------------------------------------------
 public ArrayList<Integer> geraCand(){

  ArrayList<Integer> cand = new ArrayList<>();  

  for(int i=0;i<m.length;i++)
      if(x[m[i][0]] == 1 && x[m[i][1]] == 1 && x[m[i][2]] == 0)        
        cand.add(i);       


  //System.out.println("------------------------------------------");

/*
  if(cand.size() > 0){
    
    double[] d = new double[cand.size()];

    for(int i=0;i<cand.size();i++){
       setMove(cand.get(i));
       d[i] = distCentro();
       //imprimeMove(cand.get(i));
       //System.out.printf("distcentro = (%.1f)\n",d[i]);
       unsetMove(cand.get(i));
    }

    //System.out.println("--------------------antes");
    //ut.printVetor(d);
    //ut.imprime_x(cand);

     for(int k=0;k<cand.size();k++)
        for(int j=1;j<cand.size();j++)
           if(d[j] > d[j-1]){

             double daux = d[j-1];
             d[j-1] = d[j];
             d[j] = daux;

             int c = cand.get(j-1);
             cand.set(j-1,cand.get(j));
             cand.set(j,c);

           }


      //System.out.println("--------------------depois");
      //ut.printVetor(d);
      //ut.imprime_x(cand);
  }


*/


  return cand;
}
//--------------------------------------------
public void swap(double a,double b){

  double c = a;

  a = b;

  b = c;

}
//--------------------------------------------
public void setMove(int i){
 
    x[m[i][0]] = 0;
    x[m[i][1]] = 0;
    x[m[i][2]] = 1;

}
//--------------------------------------------
public void unsetMove(int i){

    x[m[i][0]] = 1;
    x[m[i][1]] = 1;
    x[m[i][2]] = 0;

}
//--------------------------------------------
public int contaPegs(){

 int s=0;

 for(int i=0;i<x.length;i++)
    s += x[i];

 return s;

}
//--------------------------------------------
public int getItem(int i){

  return x[i];
}
//--------------------------------------------
 public void imprimeMove(int i){

 System.out.printf("mov = (%d)(%d)(%d)\n",m[i][0],m[i][1],m[i][2]);

}
//--------------------------------------------
public int calcPagoda(){

  int s=0;

  for(int i=0;i<x.length;i++)
     if(x[i] == 1)
       s += pagoda[i];

  return s;
}
//--------------------------------------------
public int distCentro(){

   int s=0;

  for(int i=0;i<x.length;i++)
     if(x[i] == 1)
       s += Math.abs(tabxy[i][0] - 3) + Math.abs(tabxy[i][1] - 3);


  return s;
}
//--------------------------------------------
public int dispersao(){

    int s = 0;

    for(int i=0;i<x.length;i++)
    for(int j=i+1;j<x.length;j++)
       if(x[i] == 1 && x[j] == 1)
         s += Math.abs(tabxy[i][0]-tabxy[j][0]) + Math.abs(tabxy[i][1]-tabxy[j][1]);



    return s;

}
//--------------------------------------------
public int cornerPegs(){

   return x[0] + x[2] + x[6] + x[12] + x[20] + x[26] + x[30] + x[32];
}
//--------------------------------------------
public int cornerPegs1(){

   return x[0] + x[1] + x[2] + x[3] + x[4] + x[5] + x[6] + x[13] + x[20] + x[7] + x[14] + x[21]
        + x[12] + x[19] + x[26] + x[11] + x[18] + x[25] + x[27] + x[28] + x[29] + x[30] + x[31] + x[32];
}
//--------------------------------------------
public int centroPegs(){

   return x[8] + x[10] + x[22] + x[24];
}
//--------------------------------------------
//--------------------------------------------
//--------------------------------------------
public int peaoIsolado() {

//      00 01 02
//      03 04 05
//06 07 08 09 10 11 12
//13 14 15 16 17 18 19
//20 21 22 23 24 25 26
//      27 28 29
//      30 31 32

   int p=-1;

   if(x[0]+x[1]+x[2] == 1 && x[3]+x[4]+x[5] == 0)
     p = 1;

   if(x[6]+x[13]+x[20] == 1 && x[7]+x[14]+x[21] == 0)
     p = 1;

   if(x[12]+x[19]+x[26] == 1 && x[11]+x[18]+x[25] == 0)
     p = 1;

   if(x[12]+x[19]+x[26] == 2 && x[19] == 0 && x[11]+x[18]+x[25] == 0)
     p = 1;


   if(x[30]+x[31]+x[32] == 1 && x[27]+x[28]+x[29] == 0)
     p = 1;

   return p;

}
}//###############################################################################
