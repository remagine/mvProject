package program.cp;

import program.Command;

import java.io.*;
import java.util.List;

public class Cp implements Command {
    private final String sourceFilePath;
    private final String destinationFilePath;

    public Cp(List<String> filePaths) {
        if(isInvalid(filePaths)){
            throw new IllegalArgumentException();
        }
        sourceFilePath = filePaths.get(0);
        destinationFilePath = filePaths.get(1);
    }

    private boolean isInvalid(List<String> filePaths){
        return filePaths.size() != 2;
    }

    @Override
    public void execute() {
        if (!sourceFilePath.equals(destinationFilePath)) {
            copyFile();
        }
    }


    private void copyFile() {
        File originFile = new File(sourceFilePath);
        byte[] buffer = new byte[bufferSize];

        // 1. destinationFilePath가 이미 존재한 경우
        // sourceFilePath로 overwrite
        // 2. sourceFilePath가 유효하지 않는 경우
        // new FileNotFoundException
        // 3. path가 동일한 경우
        // 아무일도 하지 않는다.

        try (InputStream inputStream = new FileInputStream(originFile);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, bufferSize);
             OutputStream outputStream = new FileOutputStream(destinationFilePath);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, bufferSize);
        ) {
            while (true) {
                int len = bufferedInputStream.read(buffer);
                if (len == -1) {
                    break;
                }
                bufferedOutputStream.write(buffer, 0 , len);
            }
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
