<%@ page import="com.cookbook.model.Recipe" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Recipe recipe = (Recipe) request.getAttribute("recipe");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CookBook - Recipe Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">

<%@ include file="navbar.jsp" %>

<div class="container mt-4">
    <%
        if (recipe == null) {
    %>
        <div class="alert alert-danger">Recipe not found.</div>
    <%
        } else {
    %>
        <h1 class="mb-2"><%= recipe.getTitle() %></h1>
        <p class="text-muted">
            <strong>Category:</strong> <%= recipe.getCategory() %> |
            <strong>Time:</strong> <%= recipe.getCookingTime() %> min |
            <strong>Difficulty:</strong> <%= recipe.getDifficulty() %>
        </p>

        <div class="card mb-3">
            <div class="card-body">
                <h4>Description</h4>
                <p><%= recipe.getDescription() %></p>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <div class="card">
                    <div class="card-body">
                        <h4>Ingredients</h4>
                        <pre class="pre-block"><%= recipe.getIngredients() %></pre>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <div class="card">
                    <div class="card-body">
                        <h4>Steps</h4>
                        <pre class="pre-block"><%= recipe.getSteps() %></pre>
                    </div>
                </div>
            </div>
        </div>
    <%
        }
    %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
