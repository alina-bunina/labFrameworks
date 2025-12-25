package servlets;

import entity.Profile;
import exception.ServiceException;
import exception.ValidationException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import repository.ProfileRepository;
import service.PasswordService;
import service.ProfileService;
import servlets.URLutils;

@WebServlet({"/registration"})
public class RegistrationServlet extends HttpServlet {
    private final ProfileService profileService = new ProfileService(new ProfileRepository(), new PasswordService());

    public RegistrationServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/registration.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            Profile newProfile = this.profileService.createProfile(login, password);
            HttpSession session = req.getSession();
            session.setAttribute("login", newProfile.getLogin());
            session.setAttribute("id", newProfile.getId());
            resp.sendRedirect(URLutils.getFullUrlForRedirect(req, "/login"));
        } catch (ValidationException var7) {
            req.setAttribute("error", "Пользователь с таким логином уже существует.");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/registration.jsp");
            requestDispatcher.forward(req, resp);
        } catch (ServiceException | NoSuchAlgorithmException var8) {
            Exception e = var8;
            throw new ServletException(e);
        }

    }
}
