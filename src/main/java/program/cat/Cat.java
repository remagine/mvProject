package program.cat;

import program.Program;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Cat implements Program {
    private List<String> fileNames;

    public Cat(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    @Override
    public void execute() {
        OutputStream outputStream = System.out;
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);
        for (String fileName : fileNames) {
            File file = new File(fileName);

            try (InputStream fileInputStream = new FileInputStream(file);
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 8192);
            ) {
                byte[] buffer = new byte[8192];
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
}
