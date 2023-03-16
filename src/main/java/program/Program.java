package program;

import java.util.List;

public interface Program {
    int bufferSize = 8192;

    void execute();

    boolean validate(List<String> filePaths);
}
