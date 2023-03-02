import java.io.IOException;
import java.util.HashMap;

public class Assembler{
    static HashMap<String,Integer> Symboltable;
    public static void main(String[] args)  throws IOException{
     
    new RmoveSpace(args[0],args[1], args[2]).readWrite();
    intTable();
    new firstPass(Symboltable,args[0], args[2]);

    // System.out.print(Symboltable);
    new SecondPass(Symboltable, args[0], args[2]);

       


    }


    public static void intTable(){
         Symboltable = new HashMap<String,Integer>();
        
        for(int i=0; i<=15; i++){
            Symboltable.put("R" + i, i);
        }
        Symboltable.put("SCREEN", 16384);
        Symboltable.put("KBD", 24576);
        Symboltable.put("SP", 0);
        Symboltable.put("LCL", 1);
        Symboltable.put("ARG", 2);
        Symboltable.put("THIS", 3);
        Symboltable.put("THAT", 4);

    }



 

   

}
    