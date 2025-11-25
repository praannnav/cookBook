package com.cookbook.servlet;

import com.cookbook.dao.RecipeDAO;
import com.cookbook.dao.impl.RecipeDAOImpl;
import com.cookbook.model.DifficultyLevel;
import com.cookbook.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddRecipeServlet extends HttpServlet {

    private RecipeDAO recipeDAO = new RecipeDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("add-recipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String ingredients = request.getParameter("ingredients");
        String steps = request.getParameter("steps");
        String category = request.getParameter("category");
        String cookingTimeStr = request.getParameter("cookingTime");
        String difficultyStr = request.getParameter("difficulty");

        try {
            int cookingTime = Integer.parseInt(cookingTimeStr);
            DifficultyLevel difficulty = DifficultyLevel.valueOf(difficultyStr);

            Recipe recipe = new Recipe();
            recipe.setTitle(title);
            recipe.setDescription(description);
            recipe.setIngredients(ingredients);
            recipe.setSteps(steps);
            recipe.setCategory(category);
            recipe.setCookingTime(cookingTime);
            recipe.setDifficulty(difficulty);

            recipeDAO.addRecipe(recipe);

            response.sendRedirect("recipes");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error adding recipe.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
