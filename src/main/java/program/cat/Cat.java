package program.cat;

import program.Command;
import program.Util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cat implements Command {

    private final List<Path> paths;

    public Cat(List<Path> paths) {
        this.paths = validatePaths(paths);
    }

    public static Cat fromPaths(List<String> filePathStrings) {
        List<String> checkedParam = Util.checkParam(filePathStrings);
        List<Path> newPaths = checkedParam.stream()
                .filter(str -> Objects.nonNull(str))
                .map(str -> Paths.get(str))
                .collect(Collectors.toUnmodifiableList());
        return new Cat(newPaths);
    }

    @Override
    public void execute() {
        OutputStream outputStream = System.out;
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, BUFFER_SIZE);
        byte[] buffer = new byte[BUFFER_SIZE];

        //시나리오 코딩은 지양
        // 구조적으로 코딩하는 연습 (역활별로 for if등을 사용
        // 시나리오를 구조로 바꿀 수 있는 능력을 길러야 한다.
        // 1. paths -> List<InputStream>으로
        List<InputStream> inputStreams = paths.stream().map(path ->{
            if(Files.notExists(path)){
                return new ByteArrayInputStream(("파일이 존재하지 않습니다. path : " + path).getBytes(StandardCharsets.UTF_8));
            }

            try {
                return Files.newInputStream(path);
            } catch (IOException e) {
                return new ByteArrayInputStream(("파일이 존재하지 않습니다. path : " + path).getBytes(StandardCharsets.UTF_8));
            }
        })
                .map(inputStream -> new BufferedInputStream(inputStream, BUFFER_SIZE))
                .collect(Collectors.toList());
        // 2. List<InputStream> 을 출력
        inputStreams.forEach(inputStream -> {
            try(inputStream;){
                while(true){
                    int len = inputStream.read(buffer);
                    if(len == -1) {
                        break;
                    }
                    bufferedOutputStream.write(buffer,0,len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> List<T> validatePaths(List<T> paths){
        if(paths == null){
            return Collections.emptyList();
        }
        return paths.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());
    }
}