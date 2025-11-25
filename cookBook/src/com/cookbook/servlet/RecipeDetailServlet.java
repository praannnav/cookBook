package com.cookbook.servlet;

import com.cookbook.dao.RecipeDAO;
import com.cookbook.dao.impl.RecipeDAOImpl;
import com.cookbook.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RecipeDetailServlet extends HttpServlet {

    private RecipeDAO recipeDAO = new RecipeDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("recipes");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Recipe recipe = recipeDAO.getRecipeById(id);

            if (recipe == null) {
                request.setAttribute("errorMessage", "Recipe not found.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            request.setAttribute("recipe", recipe);
            request.getRequestDispatcher("recipe-details.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to load recipe.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
