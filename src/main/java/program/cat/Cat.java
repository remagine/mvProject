package program.cat;

import program.Command;

import java.io.*;
import java.util.List;

public class Cat implements Command {
    private final List<String> fileNames;

    public Cat(List<String> fileNames) {
        if(fileNames.size() == 0){
            throw new IllegalArgumentException();
        }
        this.fileNames = fileNames;
    }

    @Override
    public void execute() {
        OutputStream outputStream = System.out;
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, bufferSize);
        byte[] buffer = new byte[bufferSize];
        for (String fileName : fileNames) {
            File file = new File(fileName);
            try (InputStream fileInputStream = new FileInputStream(file);
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, bufferSize);
            ) {
                while (true) {
                    int len = bufferedInputStream.read(buffer);
                    if (len == -1) {
                        break;
                    }
                    bufferedOutputStream.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}