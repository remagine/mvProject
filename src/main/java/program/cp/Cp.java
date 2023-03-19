package program.cp;

import program.Command;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Cp implements Command {
    private final Path sourceFilePath;
    private final Path destinationFilePath;


    public Cp(Path sourceFilePath, Path destinationFilePath) {
        if (Objects.equals(sourceFilePath, destinationFilePath)) {
            throw new IllegalArgumentException("경로가 동일합니다. sourceFilePath = " + sourceFilePath + ", destinationFilePath = " + destinationFilePath);
        }
        this.sourceFilePath = sourceFilePath;
        this.destinationFilePath = destinationFilePath;
    }

    public static Cp fromPath(String sourceFilePath, String destinationFilePath) {
        return new Cp(
                Paths.get(sourceFilePath),
                Paths.get(destinationFilePath)
        );
    }

    @Override
    public void execute() {
        if (Files.notExists(sourceFilePath)) {
            throw new IllegalStateException("파일이 존재하지 않습니다. path = " + sourceFilePath);
        }

        if (Files.exists(destinationFilePath)) {
            throw new IllegalStateException("복사할 대상 파일이 존재합니다. path = " + destinationFilePath);
        }

        byte[] buffer = new byte[BUFFER_SIZE];

        try (InputStream inputStream = Files.newInputStream(sourceFilePath);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE);
             OutputStream outputStream = Files.newOutputStream(destinationFilePath);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, BUFFER_SIZE);
        ) {
            while (true) {
                int len = bufferedInputStream.read(buffer);
                if (len == -1) {
                    break;
                }
                bufferedOutputStream.write(buffer, 0, len);
            }
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
