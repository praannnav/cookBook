package com.cookbook.servlet;

import com.cookbook.dao.RecipeDAO;
import com.cookbook.dao.impl.RecipeDAOImpl;
import com.cookbook.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {

    private RecipeDAO recipeDAO = new RecipeDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("q");
        if (keyword == null) keyword = "";

        try {
            List<Recipe> recipes = recipeDAO.searchRecipes(keyword);
            request.setAttribute("recipes", recipes);
            request.setAttribute("searchQuery", keyword);
            request.getRequestDispatcher("recipes.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error searching recipes.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
