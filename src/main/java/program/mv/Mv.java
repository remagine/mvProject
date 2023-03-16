package program.mv;

import program.Program;
import program.cp.Cp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Mv implements Program {
    private final String filePath1;
    private final String filePath2;
    private final List<String> fileNames;

    public Mv(List<String> filePaths) {
        fileNames = filePaths;
        filePath1 = filePaths.get(0);
        filePath2 = filePaths.get(1);
    }

    public void execute() {
        boolean validate = validate(fileNames);
        if (!validate) {
            System.out.println("filePaths arguments error");
            return;
        }
        if (!filePath1.equals(filePath2)) {
            Cp cp = new Cp(fileNames);
            cp.copyFile();

            try {
                Files.delete(Paths.get(filePath1)); // try resource 안에서 실행하면 FileSystemException: file1.txt: 다른 프로세스가 파일을 사용 중이기 때문에 프로세스가 액세스 할 수 없습니다.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean validate(List<String> filePaths) {
        return filePaths.size() == 2;
    }
}