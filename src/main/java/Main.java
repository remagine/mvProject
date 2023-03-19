import program.CommandType;
import program.Command;
import program.cat.Cat;
import program.cp.Cp;
import program.mv.Mv;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static program.CommandType.*;


public class Main {
    public static void main(String[] args) {
        if (true) {
            try {
                throw new NullPointerException();
            } catch (Exception e) {
                throw e;
            }
        }
        InputStream inputStream = System.in;

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
             InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);) {
            while (true) {
                String inputString = bufferedReader.readLine();
                List<String> inputStringList = List.of(inputString.split(" "));
                String commandStr = inputStringList.get(0);

                CommandType commandType = null;
                try {
                    commandType = CommandType.fromString(commandStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("command not found");
                    continue;
                }
                List<String> filePaths = inputStringList.subList(1, inputStringList.size());

                Command program = null;

                if (commandType == MV && filePaths.size() == 2) {
                    String sourceFilePath = filePaths.get(0);
                    String destinationFilePath = filePaths.get(1);
                    Cp cp = Cp.fromPath(sourceFilePath, destinationFilePath);
                    program = new Mv(sourceFilePath, cp);
                }

                if (commandType == CP && filePaths.size() == 2) {
                    String sourceFilePath = filePaths.get(0);
                    String destinationFilePath = filePaths.get(1);
                    program = Cp.fromPath(sourceFilePath, destinationFilePath);
                }

                if (commandType == CAT && !filePaths.isEmpty()) {
                    program = Cat.fromPaths(filePaths);
                }

                if (program == null) {
                    System.out.println("program doesn't exist");
                    break;
                }

                program.execute();
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();

        }
    }
}