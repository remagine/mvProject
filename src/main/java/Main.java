import program.Commands;
import program.Program;
import program.cat.Cat;
import program.cp.Cp;
import program.mv.Mv;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<String> commandList = Arrays.stream(Commands.values())
                .map(Commands::getCommandString)
                .collect(Collectors.toList());
        InputStream inputStream = System.in;

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
             InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);) {
            while (true) {
                String inputString = bufferedReader.readLine();
                List<String> inputStringList = List.of(inputString.split(" "));
                String command = inputStringList.get(0);
                List<String> filePaths = inputStringList.subList(1, inputStringList.size());

                if (!commandList.contains(command)) {
                    System.out.println("command not found");
                    break;
                }

                Program program = null;
                // enum값을 switch에서는 활용할 수 없구나
                switch (command) {
                    case "mv":
                        program = new Mv(filePaths);
                        break;
                    case "cp":
                        program = new Cp(filePaths);
                        break;
                    case "cat":
                        program = new Cat(filePaths);
                        break;
                    default:
                        System.out.println("unknown command");
                        break;
                }

                if (program == null) {
                    System.out.println("program doesn't exist");
                    break;
                }

                program.execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
