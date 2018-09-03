<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.03.2018
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
    .header{
    background-color: #031A6B;
    width: 100%;
    height: 10%;
    margin-top: 0;
    }

    .logo {
    width: 10%;
    height: 70%;
    padding: 1%;
    float:left;
    background-image:url('../../images/logo.png');
    }
    .menu-button{
    width: 8%;
    height: 10%;
    border-radius: 15px;
    padding: 1%;
    float:left;
    text-align: center;
    margin: 10px;
    color: antiquewhite;
    font-size: 120%;
    }
    .menu-button:hover {
    background-color: #4B66C6;
    }
    </style>
</head>
<body>
<section class="header">
    <div class="logo">
    </div>
    <a href="/">
        <div class="menu-button">
            Planes
        </div>
    </a>
    <a href="/routes">
        <div class="menu-button">
            Routes
        </div>
    </a>
    <a href="/flights">
        <div class="menu-button">
            Flights
        </div>
    </a>
    <a href="/clients">
        <div class="menu-button">
            Clients
        </div>
    </a>
    <a href="/tickets">
        <div class="menu-button">
            Tickets
        </div>
    </a>
    <a href="/seats">
        <div class="menu-button">
            Seats
        </div>
    </a>
    <a href="/employees">
        <div class="menu-button">
            Employees
        </div>
    </a>
</section>

</body>
</html>
