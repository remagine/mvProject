package program;

public class EmptyCommand implements Command{
    @Override
    public void execute() {
        System.out.println("program doesn't exist");
    }
}
