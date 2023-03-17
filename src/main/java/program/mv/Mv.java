package program.mv;

import program.Command;
import program.cp.Cp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Mv implements Command {
    private final String sourceFilePath;
    private final String destinationFilePath;
    private final Cp cp;

    public Mv(List<String> filePaths) {
        if (isInvalid(filePaths)) {
            throw new IllegalArgumentException();
        }
        sourceFilePath = filePaths.get(0);
        destinationFilePath = filePaths.get(1);
        cp = new Cp(filePaths);
    }

    private boolean isInvalid(List<String> filePaths) {
        return filePaths.size() != 2;
    }

    public void execute() {
        if (!sourceFilePath.equals(destinationFilePath)) {
            cp.execute();
            try {
                Files.delete(Paths.get(sourceFilePath)); // try resource 안에서 실행하면 FileSystemException: file1.txt: 다른 프로세스가 파일을 사용 중이기 때문에 프로세스가 액세스 할 수 없습니다.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}