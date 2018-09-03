<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.03.2018
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Flights page</title>
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
        }
        .content{
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
        }
        .clients-wrapper{
            overflow:auto;
            height: 70%;
            width: 100%;
            margin-top: 5%;
        }
        .clients{
            margin: 0 auto;
            padding: 15px;
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
    <div class="btn-create">
        <a href="/addFlight">Add flight
        </a>
    </div>
    <div class="clients-wrapper">
    <table class="clients">
        <caption class="table-title">Flights</caption>
        <tr>
            <th>Name</th>
            <th>Date</th>
            <th>Route</th>
            <th>Status</th>
            <th>The count of available seats</th>
            <th>Available actions</th>
        </tr>
        <c:forEach items = "${flightList}" var = "flight">
            <tr>
                <td><c:out value = "${flight.name}"/></td>
                <td><c:out value = "${flight.date}"/></td>
                <td><c:out value = "${flight.route.startPoint} - ${flight.route.endPoint}"/></td>
                <td><c:out value = "${flight.status}"/></td>
                <td><c:out value = "${flight.availableSeatsCount}"/></td>
                <td>
                    <div class="btn-update" action="/updateFlight" method="post">
                        <a href="/updateFlight?id=${flight.id}">Update flight</a>
                    </div>
                    <div class="btn-remove" action="/deleteFlight" method="post">
                        <a href="/deleteFlight?id=${flight.id}">Remove flight</a>
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

