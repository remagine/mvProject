package program.mv;

import program.Commands;
import program.Program;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Mv implements Program {
    private String filePath1;
    private String filePath2;

    public Mv(List<String> filePaths) {
        filePath1 = filePaths.get(0);
        filePath2 = filePaths.get(1);
    }

    public void execute()  {
        Path originPath = Paths.get(filePath1);
        Path destinationPath = Paths.get(filePath2);
        try {
            Files.move(originPath, destinationPath);
            System.out.println("move complete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
