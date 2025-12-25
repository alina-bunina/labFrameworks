package servlets;

import entity.Pet;
import exception.DBException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.PetRepository;
import service.PetService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/pets")
public class PetServlet extends HttpServlet {

    private PetService petService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            PetRepository petRepository = new PetRepository();
            this.petService = new PetService(petRepository);
        } catch (DBException e) {
            throw new ServletException("Ошибка подключения к базе данных", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userIdLong = (Long) req.getSession().getAttribute("id");
        if (userIdLong == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int userId = userIdLong.intValue();
        String search = req.getParameter("search");

        if (search != null && search.isBlank()) {
            search = null;
        } else if (search != null) {
            search = search.trim();
        }

        List<Pet> pets = new ArrayList<>();

        try {
            if (search != null) {
                pets = petService.getPetsByNameAndAge(userId, search);
            } else {
                pets = petService.getByPet(userId);
            }

            req.setAttribute("pets", pets);
            req.setAttribute("search", search);
            req.getRequestDispatcher("/WEB-INF/user/pets.jsp").forward(req, resp);
        } catch (DBException e) {
            req.setAttribute("error", "Произошла ошибка при загрузке питомцев.");
            req.getRequestDispatcher("/WEB-INF/user/pets.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Long userIdLong = (Long) req.getSession().getAttribute("id");

        if (userIdLong == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        int userId = userIdLong.intValue();

        try {
            switch (action) {
                case "add": {
                    String petName = req.getParameter("pet");
                    int age = Integer.parseInt(req.getParameter("age"));
                    Pet pet = new Pet();
                    pet.setPet(petName);
                    pet.setAge(age);
                    pet.setIdProfile(userId);
                    petService.savePet(petName, age, userId);
                    String redirect = servlets.URLutils.getFullUrlForRedirect(req, "/pets");
                    resp.sendRedirect(redirect);
                    break;
                }
                case "update": {
                    int id = Integer.parseInt(req.getParameter("id_pet"));
                    String updatedPet = req.getParameter("pet");
                    int updatedAge = Integer.parseInt(req.getParameter("age"));
                    petService.updatePet(id, updatedPet, updatedAge, userId);
                    String redirect = servlets.URLutils.getFullUrlForRedirect(req, "/pets");
                    resp.sendRedirect(redirect);
                    break;
                }
                case "delete": {
                    int deleteId = Integer.parseInt(req.getParameter("id_pet"));
                    petService.deletePet(deleteId, userId);
                    String redirect = servlets.URLutils.getFullUrlForRedirect(req, "/pets");
                    resp.sendRedirect(redirect);
                    break;
                }
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Неверный формат числа.");
            req.getRequestDispatcher("/WEB-INF/user/pets.jsp").forward(req, resp);
        } catch (DBException e) {
            req.setAttribute("error", "Ошибка при работе с базой данных.");
            req.getRequestDispatcher("/WEB-INF/user/pets.jsp").forward(req, resp);
        }
    }
}
