<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CookBook - Add Recipe</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">

<%@ include file="navbar.jsp" %>

<div class="container mt-4">
    <h1 class="mb-3">Add New Recipe</h1>

    <form action="addRecipe" method="post" class="card p-4 shadow-sm">
        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" name="title" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="description" rows="3" class="form-control"></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Ingredients</label>
            <textarea name="ingredients" rows="4" class="form-control" required></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Steps</label>
            <textarea name="steps" rows="5" class="form-control" required></textarea>
        </div>

        <div class="row">
            <div class="col-md-4 mb-3">
                <label class="form-label">Category</label>
                <input type="text" name="category" class="form-control" placeholder="Dessert, Breakfast...">
            </div>
            <div class="col-md-4 mb-3">
                <label class="form-label">Cooking Time (min)</label>
                <input type="number" name="cookingTime" class="form-control" required>
            </div>
            <div class="col-md-4 mb-3">
                <label class="form-label">Difficulty</label>
                <select name="difficulty" class="form-select">
                    <option value="EASY">Easy</option>
                    <option value="MEDIUM">Medium</option>
                    <option value="HARD">Hard</option>
                </select>
            </div>
        </div>

        <button type="submit" class="btn btn-danger">Add Recipe</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
