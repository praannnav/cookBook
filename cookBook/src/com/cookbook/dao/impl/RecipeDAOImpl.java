package com.cookbook.dao.impl;

import com.cookbook.dao.RecipeDAO;
import com.cookbook.dao.DAOException;
import com.cookbook.model.Recipe;
import com.cookbook.model.DifficultyLevel;
import com.cookbook.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO {

    @Override
    public void addRecipe(Recipe recipe) throws DAOException {
        String sql = "INSERT INTO recipes (title, description, ingredients, steps, category, cooking_time, difficulty) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, recipe.getTitle());
            ps.setString(2, recipe.getDescription());
            ps.setString(3, recipe.getIngredients());
            ps.setString(4, recipe.getSteps());
            ps.setString(5, recipe.getCategory());
            ps.setInt(6, recipe.getCookingTime());
            ps.setString(7, recipe.getDifficulty().name());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error adding recipe", e);
        }
    }

    @Override
    public Recipe getRecipeById(int id) throws DAOException {
        String sql = "SELECT * FROM recipes WHERE id = ?";
        Recipe recipe = null;

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    recipe = mapRowToRecipe(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error fetching recipe by id", e);
        }

        return recipe;
    }

    @Override
    public List<Recipe> getAllRecipes() throws DAOException {
        String sql = "SELECT * FROM recipes ORDER BY created_at DESC";
        List<Recipe> recipes = new ArrayList<>();

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                recipes.add(mapRowToRecipe(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error fetching all recipes", e);
        }

        return recipes;
    }

    @Override
    public List<Recipe> searchRecipes(String keyword) throws DAOException {
        String sql = "SELECT * FROM recipes WHERE title LIKE ? OR category LIKE ? ORDER BY created_at DESC";
        List<Recipe> recipes = new ArrayList<>();
        String like = "%" + keyword + "%";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, like);
            ps.setString(2, like);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    recipes.add(mapRowToRecipe(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error searching recipes", e);
        }

        return recipes;
    }

    private Recipe mapRowToRecipe(ResultSet rs) throws SQLException {
        Recipe r = new Recipe();
        r.setId(rs.getInt("id"));
        r.setTitle(rs.getString("title"));
        r.setDescription(rs.getString("description"));
        r.setIngredients(rs.getString("ingredients"));
        r.setSteps(rs.getString("steps"));
        r.setCategory(rs.getString("category"));
        r.setCookingTime(rs.getInt("cooking_time"));

        String diff = rs.getString("difficulty");
        try {
            r.setDifficulty(diff != null ? DifficultyLevel.valueOf(diff) : DifficultyLevel.EASY);
        } catch (IllegalArgumentException e) {
            r.setDifficulty(DifficultyLevel.EASY);
        }

        return r;
    }
}
