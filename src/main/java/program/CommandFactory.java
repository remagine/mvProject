package program;

import program.cat.Cat;
import program.cp.Cp;
import program.mv.Mv;

import java.util.List;

import static program.CommandType.*;

public class CommandFactory {
    public Command create(CommandArgument commandArgument){
        CommandType commandType = commandArgument.getCommandType();
        List<String> filePaths = commandArgument.getParameters();

        if (commandType == MV && filePaths.size() == 2) {
            String sourceFilePath = filePaths.get(0);
            String destinationFilePath = filePaths.get(1);
            Cp cp = Cp.fromPath(sourceFilePath, destinationFilePath);
            return new Mv(sourceFilePath, cp);
        }

        if (commandType == CP && filePaths.size() == 2) {
            String sourceFilePath = filePaths.get(0);
            String destinationFilePath = filePaths.get(1);
            return Cp.fromPath(sourceFilePath, destinationFilePath);
        }

        if (commandType == CAT) {
            return Cat.fromPaths(filePaths);
        }

        return new EmptyCommand();
    }
}
