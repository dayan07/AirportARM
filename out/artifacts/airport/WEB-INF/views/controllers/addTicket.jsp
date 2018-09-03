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
    <title>Add ticket</title>
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
        <form action="/createTicket" method="post">
            <div class="inp-div" hidden>
                <input class="inp-form" name="id" id="id" type="text" value="${ticket.id}">Id<Br>
            </div>
            <div class="inp-div">
                <label class="inp-label">Client</label>
                <select name='clientId' class="inp-form">
                    <c:forEach var="client" items="${clientList}">
                        <option id="${client.id}" value="${client.id}" ${client.id == selectedClientId ? 'selected' : ''}>${client.firstName} ${client.lastName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="inp-div">
                <label class="inp-label">User</label>
                <select name='userId' class="inp-form">
                    <c:forEach var="user" items="${userList}">
                        <option id="${user.id}" value="${user.id}" ${user.id == selectedUserId ? 'selected' : ''}>${user.userName} </option>
                    </c:forEach>
                </select>
            </div>
            <div class="inp-div">
                <label class="inp-label">Employee</label>
                <select name='employeeId' class="inp-form">
                    <c:forEach var="employee" items="${employeeList}">
                        <option id="${employee.id}" value="${employee.id}" ${employee.id == selectedEmployeeId ? 'selected' : ''}>${employee.firstName} ${employee.lastName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="inp-div">
                <label class="inp-label">Flight</label>
                <select name='flightId' class="inp-form">
                    <c:forEach var="flight" items="${flightList}">
                        <option id="${flight.id}" value="${flight.id}" ${flight.id == selectedFlightId ? 'selected' : ''}>${flight.name} ${flight.route.startPoint}-${flight.route.endPoint}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="inp-div">
                <label class="inp-label">Seat</label>
                <select name='seatId' class="inp-form">
                    <c:forEach var="seat" items="${seatList}">
                        <option id="${seat.id}" value="${seat.id}" ${seat.id == selectedSeatId ? 'selected' : ''}>${seat.row} ${seat.number}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="inp-div">
                <input class="inp-form" name="cost" id="cost" type="text" value="${ticket.cost}">Cost<Br>
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
