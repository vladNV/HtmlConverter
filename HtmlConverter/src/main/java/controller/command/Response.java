package controller.command;

import javax.servlet.ServletException;
import java.io.IOException;

@FunctionalInterface
public interface Response {

    void answer() throws IOException, ServletException;

}
