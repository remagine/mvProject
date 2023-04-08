package program;

import java.util.List;

public class CommandArgument {
    private final CommandType commandType;
    private final List<String> parameters;

    public CommandArgument(CommandType commandType, List<String> parameters) {
        this.commandType = commandType;
        this.parameters = parameters;
    }

    public static CommandArgument from(String commandString){
        List<String> commandList = List.of(commandString.split(" "));
        CommandType commandType = CommandType.fromString(commandList.get(0));
        List<String> parameters = commandList.subList(1, commandList.size());

        return new CommandArgument(commandType, parameters);
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
