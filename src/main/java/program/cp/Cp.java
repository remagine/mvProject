package program.cp;

import program.Commands;
import program.Program;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Cp implements Program {
    private String filePath1;
    private String filePath2;
    private List<String> fileNames;

    public Cp(List<String> filePaths) {
        fileNames = filePaths;
        filePath1 = filePaths.get(0);
        filePath2 = filePaths.get(1);
    }

    @Override
    public void execute() {
        boolean validate = validate(fileNames);
        if(!validate){
            System.out.println("filePaths arguments error");
            return;
        }
        Path originPath = Paths.get(filePath1);
        Path destinationPath = Paths.get(filePath2);
        try {
            Files.copy(originPath, destinationPath);
            System.out.println("copy complete");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean validate(List<String> filePaths) {
        return filePaths.size() != 2;
    }
}
