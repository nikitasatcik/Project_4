package nikita.epam.project_4.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nikita.epam.project_4.model.logic.Command;
import nikita.epam.project_4.model.logic.CommandFactory;

public class ServletController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPath = request.getServletPath();
        String url = "/WEB-INF/view/";

        CommandFactory commands = CommandFactory.getInstance();
       // Command command;

        switch (userPath) {
            case "/login":
                url += "login.jsp";
                break;
            case "/registration":
                url += "registration.jsp";
                break;
            case "/logout":
                Command command = commands.getCommand("logout");
                command.execute(request, response);
                url += "login.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        String url = "/WEB-INF/view/";

        HttpSession session = request.getSession();
        CommandFactory commands = CommandFactory.getInstance();
        Command command;

        switch (userPath) {
            case "/check-login":
                String buttonUser = request.getParameter("userLogin");
                String buttonAdmin = request.getParameter("adminLogin");

                if (buttonUser != null) {

                    // User login
                    command = commands.getCommand("user_login");
                    command.execute(request, response);

                    if (session.getAttribute("user") == null) {
                        url += "login.jsp";
                    } else {
                        url += "user-panel.jsp";
                    }
                } else if (buttonAdmin != null) {

                    // Admin login
                    command = commands.getCommand("admin_login");
                    command.execute(request, response);

                    if (session.getAttribute("admin") == null) {
                        url += "login.jsp";
                    } else {
                        url += "admin-panel.jsp";
                    }
                }
                break;
            case "/check-registration":

                // check that passwords match.
                String password = request.getParameter("password");
                String passwordConfirm = request.getParameter("passwordConfirm");

                if (!password.equals(passwordConfirm)) {
                    request.setAttribute("passwordNotMatch", true);
                    url += "registration.jsp";
                } else {
                    // Passwords matched
                    request.removeAttribute("passwordNotMatch");
                    command = commands.getCommand("create_user");
                    command.execute(request, response);

                    if (request.getAttribute("userNotCreated") != null) {
                        url += "registration.jsp";
                    } else {
                        request.removeAttribute("userNotCreated");
                        url += "registration-success.jsp";
                    }
                }
                break;

            case "/admin-panel":
                // Update users.
                command = commands.getCommand("update_users");
                command.execute(request, response);
                url += "admin-panel.jsp";
                break;

            case "/find-tour":

                // Find tours
                command = commands.getCommand("find_all_tours");
                command.execute(request, response);
                url += "tour.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    public ServletController() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
