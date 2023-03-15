import program.Commands;
import program.Program;
import program.cat.Cat;
import program.cp.Cp;
import program.mv.Mv;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> commandList = Arrays.stream(Commands.values())
                .map(Commands::getCommandString)
                .collect(Collectors.toList());
        Map<Commands, Class<? extends Program>> commandMap = new HashMap<>();
        commandMap.put(Commands.MV, Mv.class);
        commandMap.put(Commands.CP, Cp.class);
        commandMap.put(Commands.CAT, Cat.class);
        InputStream inputStream = System.in;
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
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

                if (filePaths.size() != 2) {
                    System.out.println("need 2 file paths");
                    break;
                }


                if(Commands.MV.equals(command)){
                    Program program = new Mv(filePaths);
                    program.execute();
                }

                if(Commands.CP.equals(command)){
                    Program program = new Cp(filePaths);
                    program.execute();
                }

                if(Commands.CAT.equals(command)){
                    Program program = new Cat(filePaths);
                    program.execute();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
