<%--
  Created by IntelliJ IDEA.
  User: Sneha Lakshminarasimhan
  Date: 2/25/2019
  Time: 10:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
    <meta charset="utf-8">
    <title>Login</title>

    <style>
        .col-centered{
            float: none;
            margin: 0 auto;
        }
    </style>
</head>
<body style="background-color: #4b5257">
<div class="container">
    <div class="col-xs-12 col-sm-8 col-md-4 col-lg-4 col-centered">
        <div class="jumbotron">
            <h3>Please login</h3>
            <form>
                <div class="form-group">
                    <input type="email" class="form-control" placeholder="Enter Username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Enter password">
                </div>
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Select User
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="#">Professor</a>
                        <a class="dropdown-item" href="#">Student</a>
                    </div>
                </div>
                <div class="custom-checkbox">
                    <label><input type="checkbox"> Remember me</label>
                </div>
                <button type="submit" class="btn-primary form-control">Login</button>
            </form>
        </div>
    </div>

</div>
</body>
</html>