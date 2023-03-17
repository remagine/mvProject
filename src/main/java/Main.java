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


public class Main {
    public static void main(String[] args) {
        Set<String> commandSet = Arrays.stream(CommandType.values())
                .map(CommandType::getCommandString)
                .collect(Collectors.toSet());
        InputStream inputStream = System.in;

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
             InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);) {
            while (true) {
                String inputString = bufferedReader.readLine();
                List<String> inputStringList = List.of(inputString.split(" "));
                String command = inputStringList.get(0);
                List<String> filePaths = inputStringList.subList(1, inputStringList.size());

                if (!commandSet.contains(command)) {
                    System.out.println("command not found");
                    break;
                }

                Command program = null;
                switch (CommandType.fromString(command)) {
                    case MV:
                        program = new Mv(filePaths);
                        break;
                    case CP:
                        program = new Cp(filePaths);
                        break;
                    case CAT:
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
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}