import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

public class firstPass{
    private HashMap<String,Integer> SybmoleTable;
    private String folderName;
    private String filename;

    public String assess_int; 
    public firstPass(HashMap<String,Integer> table,String folder, String fileN){
        SybmoleTable = table;
        folderName = folder;
        filename = fileN;
        FirstPass();
    }
    public  firstPass(String s) {
        assess_int = s;
        
    }

   private void FirstPass(){

   
    File file = new File("../"+ folderName + "/" + filename + ".asm");
    
    int n = 0; // number of lines read
    int variableAddress = 16; // address of variables so far
   
    try(Scanner scanner =  new Scanner(file)){
        while(scanner.hasNextLine()){
            String row = scanner.nextLine();
            // populate the tabele with (xxx) Vriables
            if(row.charAt(0) == '('){
              SybmoleTable.put(row.replaceAll("[()]",""), n);
              continue;
            }
            n++;
            //  populate the table with varibales
           

   }

} catch(Exception e){
    System.out.print(e);
}

try(Scanner scanner =  new Scanner(file)){
    while(scanner.hasNextLine()){
        String row = scanner.nextLine();
        // populate the tabele with (xxx) Vriables
        
if(row.charAt(0) == '@'){
    String newrow = row.substring(1);
    if(!SybmoleTable.containsKey(newrow) && !isInteger(newrow, 10)){
       

      SybmoleTable.put(newrow,variableAddress);
      variableAddress++;
    }
} 
       

}

} catch(Exception e){
System.out.print(e);
}




}


public boolean isInteger(String s, int radix) {
    if(s.isEmpty()) return false;
    for(int i = 0; i < s.length(); i++) {
        if(i == 0 && s.charAt(i) == '-') {
            if(s.length() == 1) return false;
            else continue;
        }
        if(Character.digit(s.charAt(i),radix) < 0) return false;
    }
    return true;
}

}

