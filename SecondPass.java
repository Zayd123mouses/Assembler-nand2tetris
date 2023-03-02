import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SecondPass {  
    private HashMap<String,Integer> SybmoleTable;
    private String FolderName;
    private String FileName;
    private HashMap<String,String> comp_map;
    private HashMap<String,String> dis_map;
    private HashMap<String,String> jump_map;



    
    public  SecondPass(HashMap<String,Integer> table, String fname, String filName) throws IOException {
     SybmoleTable = table;
     FolderName = fname;
     FileName = filName; 
     comp_map = new HashMap<String,String>();
     dis_map = new HashMap<String,String>();
     jump_map = new HashMap<String,String>();

      initiateComMap_com();
      initiateComMap_dis();
      initiateComMap_jump();
      wirteHack();

    }

    private void wirteHack() throws IOException {
        File file = new File("../"+ FolderName + "/" + FileName + ".asm");

    File file2 = new File("../" + FolderName + "/" + FileName + ".hack");
    FileWriter fw = new FileWriter(file2);
    PrintWriter pw = new PrintWriter(fw);

     try(Scanner scanner =  new Scanner(file)){
        while(scanner.hasNextLine()){
            String row = scanner.nextLine();
            if(row.charAt(0) == '@' ) {
                String a = handle_A_instruction(row);
                pw.println(a);
            }else if(row.charAt(0) == '('){
                continue;
            }else{
               String c =  handle_C_instruction(row);
               pw.println(c);
            }
        }
        
        pw.close();
     } catch(Exception e){
        System.out.println("Error: " + e.getMessage());
     }
        
    }

    private String handle_A_instruction(String row) {
        //Assumbtion: all the variables are inside Symboltable as the file is valid
        String newrow = row.substring(1);

        if(new firstPass(newrow).isInteger(newrow, 10)){
            String binary = convert_constant_to_binary(newrow);

            if(newrow == "counter"){
            System.out.println("/////////////////////////////////");
            System.out.println(binary);
            System.out.println("/////////////////////////////////");
            }
            binary = "0" + binary;
            return binary;

        }
        
        int value = SybmoleTable.get(newrow);
        System.out.println(newrow);
        System.out.println(value);


        String binary = value + "";
        binary = convert_constant_to_binary(binary);
        System.out.println(binary);

        binary = "0" + binary;
        return binary;
    }


    private String  convert_constant_to_binary(String constant) {
        int valucon = Integer.valueOf(constant);
        String preready = Integer.toBinaryString(valucon);
        int length = preready.length();
        for(int i = 0; i < length; i++){
            if (length >= 15){
                return preready;
            }
            preready = "0" + preready;
            length++;
        }
        return preready;
        
    }

    private String handle_C_instruction(String row) {
        String preready = "111";
        String[] parts = row.split(";");
        String[] equalParts = parts[0].split("=");

        if(parts.length == 2){
            if(equalParts.length == 2){
                return preready =  preready +  handleOp1(equalParts[0],equalParts[1],parts[1]);
            }else{
                return preready = preready + handleOp1(null,parts[0],parts[1]);
            }

            }else{
                return preready = preready +  handleOp1(equalParts[0],equalParts[1],null);
            }

        

    }

    private String handleOp1(String dist, String comp, String jump) {
        String dis_code = dis_map.get(dist);
        String comp_code = comp_map.get(comp);
        String jump_code = jump_map.get(jump);
        
        String answer = comp_code + dis_code + jump_code;
       
        return answer;
        
    }

    private void initiateComMap_jump() {
        jump_map.put("JGT","001");
        jump_map.put("JEQ","010");
        jump_map.put("JGE","011");
        jump_map.put("JLT","100");
        jump_map.put("JNE","101");
        jump_map.put("JLE","110");
        jump_map.put("JMP","111");
        jump_map.put(null,"000");

        
    }

    private void initiateComMap_dis() {
        dis_map.put(null,"000");
        dis_map.put("M","001");
        dis_map.put("D","010");
        dis_map.put("MD","011");
        dis_map.put("A","100");
        dis_map.put("AM","101");
        dis_map.put("AD","110");
        dis_map.put("AMD","111");
    }

    private void initiateComMap_com() {
        comp_map.put("0","0101010");
        comp_map.put("1","0111111");
        comp_map.put("-1","0111010");
        comp_map.put("D","0001100");
        comp_map.put("A","0110000");
        comp_map.put("!D","0001101");
        comp_map.put("!A","0110001");
        comp_map.put("-D","0001111");
        comp_map.put("-A","0110011");
        comp_map.put("D+1","0011111");
        comp_map.put("A+1","0110111");
        comp_map.put("D-1","0001110");
        comp_map.put("A-1","0110010");
        comp_map.put("D+A","0000010");
        comp_map.put("D-A","0010011");
        comp_map.put("A-D","0000111");
        comp_map.put("D&A","0000000");
        comp_map.put("D|A","0010101");

        comp_map.put("M","1110000");
        comp_map.put("!M","1110001");
        comp_map.put("-M","1110011");
        comp_map.put("M+1","1110111");
        comp_map.put("M-1","1110010");
        comp_map.put("D+M","1000010");
        comp_map.put("D-M","1010011");
        comp_map.put("M-D","1000111");
        comp_map.put("D&M","1000000");
        comp_map.put("D|M","1010101");
        
    }

}