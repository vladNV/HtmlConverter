package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    Response call(HttpServletRequest req, HttpServletResponse resp);

}
