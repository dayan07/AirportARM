<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.03.2018
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Add client</title>
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
        <form action="/createClient" method="post">
            <div class="inp-div" hidden>
                <input class="inp-form" name="id" id="id" type="text" value="${client.id}">Id<Br>
            </div>
            <div class="inp-div">
                <input class="inp-form" name="firstName" id="firstName" type="text" value="${client.firstName}">First name<Br>
            </div>
            <div class="inp-div">
                <input class="inp-form" name="lastName" id="lastName" type="text" value="${client.lastName}">Last name<Br>
            </div>
            <div class="inp-div">
                <input class="inp-form" name="dateOfBirth" id="dateOfBirth" type="date" value="${client.dateOfBirth}">Date of birth<Br>
            </div>
            <div class="inp-div">
                <input class="inp-form" name="passportNumber" id="passportNumber" type="text" value="${client.passportNumber}">Passport number<Br>
            </div>
            <div class="inp-div">
                <input class="inp-form" name="dateOfIssue" id="dateOfIssue" type="date" value="${client.dateOfIssue}">Date of issue<Br>
            </div>
            <div class="inp-div">
                <input class="inp-form" name="expiryDate" id="expiryDate" type="date" value="${client.expiryDate}">Expiry date<Br>
            </div>
            <div class="inp-div">
                <input class="inp-form" name="email" id="email" type="text" value="${client.email}">Email<Br>
            </div>
            <div class="inp-div">
                <input class="inp-form" name="phoneNumber" id="phoneNumber" type="text" value="${client.phoneNumber}">Phone number<Br>
            </div>
            <div class="inp-div">
                <label class="inp-label">User</label>
                <select name='userId' class="inp-form">
                    <c:forEach var="user" items="${userList}">
                        <option id="${user.id}" value="${user.id}" ${user.id == selectedUserId ? 'selected' : ''}>${user.userName}</option>
                    </c:forEach>
                </select>
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