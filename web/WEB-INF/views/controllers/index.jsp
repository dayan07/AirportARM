<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.03.2018
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Root page</title>
    <style type="text/css">
        body{
            margin: 0;
        }
        th, td {
            border: 1px solid;
        }
        td {
            height: 5%;
        }
        .content{
            width: 100%;
            height: 80%;
            color: #ffffff;
            background-color: #2344BA;
        }
        .after-removed {
            height: 2%;
            color: red;
            font-size: 140%;
            padding: 15px 120px;
        }
        .table-title{
            height: 5%;
            font-size: 200%;
            text-align: left;
            padding: 1%;
            display:block;
        }
        .header-planes{
            width: 100%;
            height: 10%;

        }
        .clients-wrapper{
            overflow:auto;
            height: 70%;
            width: 100%;
            margin-top: 5%;
        }
        .clients{
            margin: 0 auto;
            width: 90%;
            color: antiquewhite;
            font-size: 120%;
            text-align: center;
        }
        .btn-create{
            width: 10%;
            background-color: orange;
            border-radius: 5px;
            color: #000000;
            text-align: center;
            float: right;
            margin-top: 5%;
            margin-right: 7%;
            font-size: 120%;
            line-height: 40px;
        }
        .btn-update{
            margin: 5px auto;
            height: 5%;
            width: 80%;
            background-color: aquamarine;
            border-radius: 5px;
            color: #000000;
        }
        .btn-remove{
            margin: 5px auto;
            height: 5%;
            width: 80%;
            background-color: red;
            border-radius: 5px;
            color: #000000;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<section class="content">
    <c:if test="${messageAfterRemoved != null}">
        <div class="after-removed" >
                ${messageAfterRemoved}
        </div>
    </c:if>
    <div class="header-planes">
    <div class="btn-create">
        <a href="/addPlane">Add Plane
        </a>
    </div>
    </div>
    <div class="clients-wrapper">
    <table class="clients">
        <caption class="table-title">Planes</caption>
        <tr>
            <th>Manufacturer</th>
            <th>Name</th>
            <th>Type</th>
            <th>Country</th>
            <th>Creation date</th>
            <th>Available actions</th>
        </tr>
        <c:forEach items = "${planeList}" var = "plane">
        <tr>
            <td><c:out value = "${plane.manufacturer}"/></td>
            <td><c:out value = "${plane.name}"/></td>
            <td><c:out value = "${plane.type}"/></td>
            <td><c:out value = "${plane.country}"/></td>
            <td><c:out value = "${plane.createDate}"/></td>
            <td>
                <div class="btn-update" action="/updatePlane" method="post">
                    <a href="/updatePlane?id=${plane.id}">Update plane</a>
                </div>
                <div class="btn-remove" action="/deletePlane" method="post">
                    <a href="/deletePlane?id=${plane.id}">Remove plane</a>
                </div>
            </td>
        </tr>
        </c:forEach>
        </table>
    </div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>