import program.*;
import program.cat.Cat;
import program.cp.Cp;
import program.mv.Mv;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static program.CommandType.*;


public class Main {
    public static void main(String[] args) {
        readyCommandInput(inputCommand -> {

            // &&를 기준으로 파싱하여 N개의 커맨드라인을 파싱한다.
            List<String> commands = Arrays.stream(inputCommand.split("&&"))
                    .map(String::trim)
                    .collect(Collectors.toUnmodifiableList());

            List<CommandArgument> commandArguments = commands.stream()
                    .map(CommandArgument::from)
                    .collect(Collectors.toUnmodifiableList());

            CommandFactory commandFactory = new CommandFactory();

            List<Command> _commands = commandArguments.stream()
                    .map(commandFactory::create)
                    .collect(Collectors.toUnmodifiableList());

            _commands.forEach(Command::execute);
        });
    }

    private static BufferedReader createBufferedReader(InputStream inputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);

        return bufferedReader;
    }

    private static CommandType toCommandType(String commandStr){
        if(commandStr == null){
            System.out.println("command not found");
            throw new IllegalArgumentException("commandStr is null");
        }

        try {
            return CommandType.fromString(commandStr);
        } catch (Exception e) {
            System.out.println("command not found");
            throw e;
        }
    }

    private static void readyCommandInput(Consumer<String> commandConsumer){
        InputStream inputStream = System.in;

        try (BufferedReader bufferedReader = createBufferedReader(inputStream)) {
            while (true) {
                try {
                    String inputCommand = bufferedReader.readLine();
                    commandConsumer.accept(inputCommand);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}