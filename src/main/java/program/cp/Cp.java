package program.cp;

import program.Commands;
import program.Program;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Cp implements Program {
    private final String filePath1;
    private final String filePath2;
    private final List<String> fileNames;

    public Cp(List<String> filePaths) {
        fileNames = filePaths;
        filePath1 = filePaths.get(0);
        filePath2 = filePaths.get(1);
    }

    @Override
    public void execute() {
        boolean validate = validate(fileNames);
        if (!validate) {
            System.out.println("filePaths arguments error");
            return;
        }
        if (!filePath1.equals(filePath2)) {
            copyFile();
        }
    }

    public void copyFile() {
        File originFile = new File(filePath1);
        byte[] buffer = new byte[bufferSize];

        try (InputStream inputStream = new FileInputStream(originFile);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, bufferSize);
             OutputStream outputStream = new FileOutputStream(filePath2);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, bufferSize);
        ) {
            while (true) {
                int len = bufferedInputStream.read(buffer);
                if (len == -1) {
                    break;
                }
                bufferedOutputStream.write(buffer);
            }
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validate(List<String> filePaths) {
        return filePaths.size() != 2;
    }
}
