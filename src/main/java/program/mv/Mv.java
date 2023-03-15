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
    private List<String> fileNames;

    public Mv(List<String> filePaths) {
        fileNames = filePaths;
        filePath1 = filePaths.get(0);
        filePath2 = filePaths.get(1);
    }

    public void execute()  {
        boolean validate = validate(fileNames);
        if(!validate){
            System.out.println("filePaths arguments error");
            return;
        }
        Path originPath = Paths.get(filePath1);
        Path destinationPath = Paths.get(filePath2);
        try {
            Files.move(originPath, destinationPath);
            System.out.println("move complete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validate(List<String> filePaths) {
        return filePaths.size() != 2;
    }
}
