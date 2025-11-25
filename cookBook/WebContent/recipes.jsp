<%@ page import="java.util.List" %>
<%@ page import="com.cookbook.model.Recipe" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Recipe> recipes = (List<Recipe>) request.getAttribute("recipes");
    String searchQuery = (String) request.getAttribute("searchQuery");
    if (searchQuery == null) searchQuery = "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CookBook - Recipes</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">

<%@ include file="navbar.jsp" %>

<div class="container mt-4">
    <h1 class="mb-3">All Recipes</h1>

    <form action="search" method="get" class="row g-2 mb-4">
        <div class="col-md-8">
            <input type="text" name="q" class="form-control"
                   placeholder="Search by name or category" value="<%= searchQuery %>">
        </div>
        <div class="col-md-4">
            <button type="submit" class="btn btn-danger w-100">Search</button>
        </div>
    </form>

    <div class="row">
        <%
            if (recipes == null || recipes.isEmpty()) {
        %>
            <div class="col-12">
                <div class="alert alert-info">No recipes found.</div>
            </div>
        <%
            } else {
                for (Recipe r : recipes) {
        %>
            <div class="col-md-4 mb-3">
                <div class="card shadow-sm h-100">
                    <div class="card-body">
                        <h5 class="card-title">
                            <a href="recipe?id=<%= r.getId() %>"
                               class="text-decoration-none text-danger">
                                <%= r.getTitle() %>
                            </a>
                        </h5>
                        <p class="card-text mb-1">
                            <strong>Category:</strong> <%= r.getCategory() %>
                        </p>
                        <p class="card-text mb-1">
                            <strong>Time:</strong> <%= r.getCookingTime() %> min
                        </p>
                        <p class="card-text">
                            <strong>Difficulty:</strong> <%= r.getDifficulty() %>
                        </p>
                    </div>
                </div>
            </div>
        <%
                }
            }
        %>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
