package program;

import java.util.List;

public interface Command {
    int bufferSize = 8192;

    void execute();
}
