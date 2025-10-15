package com.carely.servlet;

import com.carely.entity.Utilisateur;
import com.carely.repository.UtilisateurRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UtilisateurRepository userRepo = new UtilisateurRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Utilisateur user = userRepo.findByEmail(email);

        if (user != null && BCrypt.checkpw(password, user.getMotDePasse())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole().toString());

            switch (user.getRole()) {
                case INFIRMIER -> response.sendRedirect(request.getContextPath() + "/infirmier/dashboard");
                case MEDECIN_GENERALISTE -> response.sendRedirect(request.getContextPath() + "/generaliste/dashboard");
                case MEDECIN_SPECIALISTE -> response.sendRedirect(request.getContextPath() + "/specialiste/dashboard");
            }
        } else {
            request.setAttribute("error", "Email ou mot de passe incorrect");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}