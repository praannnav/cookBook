package com.cookbook.servlet;

import com.cookbook.dao.RecipeDAO;
import com.cookbook.dao.impl.RecipeDAOImpl;
import com.cookbook.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class RecipeListServlet extends HttpServlet {

    private RecipeDAO recipeDAO = new RecipeDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Recipe> recipes = recipeDAO.getAllRecipes();
            request.setAttribute("recipes", recipes);
            RequestDispatcher rd = request.getRequestDispatcher("recipes.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to load recipes.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
