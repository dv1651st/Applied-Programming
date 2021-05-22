package windowColorChanger;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class WriteFile {
    
    private String string;
    private static WriteFile writefile = null;

    public WriteFile(){
        this.string = "";
        
    }
    public void run() {
        try {
            String data = this.string;
            File f1 = new File("data.txt");
            if (!f1.exists()) {
                f1.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(f1.getName(), true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(data);
            bw.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setString(String string) {
        this.string = string;
    }

    public static WriteFile get() {
        if (WriteFile.writefile == null) {
            WriteFile.writefile = new WriteFile();
        }
        return WriteFile.writefile;
    }
}
