import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.*;
import java.util.ArrayList;

public class resta1Recursao{

  static uteis ut;

  static final int NCEL = 33;

  static long start,end,nPagoda;
//#############################################################
  public static void main(String[] args){

    Board1 b = new Board1();

    b.inicializa();

    b.printBoard();    

    System.out.println("pagoda = " + b.calcPagoda());

    ut = new uteis();

    ArrayList<Integer> rota = new ArrayList<>();

    start =  System.currentTimeMillis();

    nPagoda = 0;

    solve(b,rota);

    

  }//###############################################
    public static void solve(Board1 b,ArrayList<Integer> rota){

          end = System.currentTimeMillis() - start;

          if(end/1000 % 30 == 0){
            System.out.println("==========================================");
            System.out.println("Tempo(s): " + end/1000  + " (" + end/60000 + "min)");        
            System.out.println("--------------------------");
            System.out.println("N. pagoda = " + nPagoda);
            System.out.println("--------------------------");

            b.printBoard();
            try { Thread.sleep (1000); } catch (InterruptedException ex) {ex.printStackTrace();}
          }

          //------------------------------------------------------------------------------
          if(b.gameOver()){
            System.out.println("*************resolvido***************");
            b.printBoard();
            for(int i=0;i<rota.size();i++)
               b.imprimeMove(rota.get(i));

            System.out.println("-----------");
            System.out.println("N. pagoda = " + nPagoda);
            System.out.println("-----------");

            end = System.currentTimeMillis() - start;
            System.out.println("Tempo(s): " + end/1000  + " (" + end/60000 + "min)"); 
            System.exit(0);
          }


          //------------------------------------------------------------------------------
          ArrayList<Integer> cand = b.geraCand(); 

                             
          while(cand.size() > 0){              

                 int mov = cand.get(cand.size()-1);

                 rota.add(mov);

                 cand.remove(cand.size()-1);

                 b.setMove(mov); 

                 if(b.calcPagoda() >= 0)
                   solve(b,rota); 
                 else
                   nPagoda += 1;             

                 b.unsetMove(mov);

                 rota.remove(rota.size()-1);

          }
          

   }// fim do solve


}//###########################################################
