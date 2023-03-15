package program;

public enum Commands {
    MV("mv"),
    CP("cp"),
    CAT("cat");

    private final String commandString;

    Commands(String commandString) {
        this.commandString = commandString;
    }

    public String getCommandString() {
        return commandString;
    }

}
