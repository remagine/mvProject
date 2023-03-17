package program;

public enum CommandType {
    MV("mv"),
    CP("cp"),
    CAT("cat");

    private final String commandString;

    CommandType(String commandString) {
        this.commandString = commandString;
    }

    public String getCommandString() {
        return commandString;
    }

    public static CommandType fromString(String commandString){
        for (CommandType commandType : CommandType.values()){
            if(commandType.getCommandString().equals(commandString)){
                return commandType;
            }
        }
        throw new IllegalArgumentException();
    }
}
