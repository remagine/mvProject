package program.cat;

import program.Program;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Cat implements Program {
    private final List<String> fileNames;

    public Cat(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    @Override
    public void execute() {
        boolean validate = validate(fileNames);
        if(!validate){
            System.out.println("filePaths arguments error");
            return;
        }
        OutputStream outputStream = System.out;
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, bufferSize);
        for (String fileName : fileNames) {
            File file = new File(fileName);

            try (InputStream fileInputStream = new FileInputStream(file);
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, bufferSize);
            ) {
                byte[] buffer = new byte[bufferSize];
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
            System.out.println("cat complete");
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validate(List<String> filePaths) {
        return filePaths.size() > 0;
    }
}
