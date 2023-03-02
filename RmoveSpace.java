import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RmoveSpace {
    private String FolderName;
    private String FileName;
    private String NameToBe;


    public RmoveSpace(String folder_name,String file_name, String name_to_be){
        FolderName = folder_name;
        FileName = file_name;
        NameToBe = name_to_be;

    }

    public void readWrite() throws IOException{
        File file = new File("../"+ FolderName + "/" + FileName);

    File file2 = new File("../" + FolderName + "/" + NameToBe + ".asm");
    FileWriter fw = new FileWriter(file2);
    PrintWriter pw = new PrintWriter(fw);

     try(Scanner scanner =  new Scanner(file)){
        while(scanner.hasNextLine()){
            String row = scanner.nextLine();
            if(row.isEmpty() || row.charAt(0) == '/' ) {
                continue;
            }
            String newrow = removeComSpace(row);
            pw.println(newrow);
        }
        
        pw.close();
     } catch(Exception e){
        System.out.println("Error: " + e.getMessage());
     }
    }

    public String removeComSpace(String row){
        for(String r : row.split(" ")){
            if(r != "" && r != "//"){
                return r;
            }
        }
       return null;
    }
    
}
