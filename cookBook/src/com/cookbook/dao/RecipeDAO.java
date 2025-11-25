package com.cookbook.dao;

import java.util.List;
import com.cookbook.model.Recipe;

public interface RecipeDAO {
    void addRecipe(Recipe recipe) throws DAOException;
    Recipe getRecipeById(int id) throws DAOException;
    List<Recipe> getAllRecipes() throws DAOException;
    List<Recipe> searchRecipes(String keyword) throws DAOException;
}
