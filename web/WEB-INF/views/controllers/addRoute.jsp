<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.03.2018
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Add route</title>
    <style type="text/css">
        body{
            margin: 0;
        }
        .content{
            width: 100%;
            height: 80%;
            color: #ffffff;
            background-color: #2344BA;
        }
        .add-plane-form{
            margin: auto 25%;
            width: 50%;
            padding: 5%;
            font-size: 120%;


        }
        .inp-div{
            margin: 1%;
        }
        .inp-form{
            float:right;
            width: 75%;
        }
        .btn-submit{

        }
        .inp-submit{
            width: 50%;
            margin: 15% 25% auto 25%;
            background-color: azure;

        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<section class="content">
    <div class="add-plane-form">
        <form action="/createRoute" method="post">
            <div class="inp-div" hidden>
                <input class="inp-form" name="id" id="id" type="text" value="${route.id}">Id<Br>
            </div>
            <div class="inp-div">
                <input class="inp-form" name="startPoint" id="startPoint" type="text" value="${route.startPoint}">Start point<Br>
            </div>
            <div class="inp-div">
                <input class="inp-form" name="endPoint" id="endPoint" type="text" value="${route.endPoint}">End point<Br>
            </div>
            <div class="btn-submit">
                <p><input class="inp-submit" type="submit" value="${isUpdateOrCreate}"></p>
            </div>
        </form>
    </div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
