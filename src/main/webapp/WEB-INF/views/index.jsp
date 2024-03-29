<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        table {
            text-align: center;
        }

    </style>

    <title>Accidents</title>
</head>
<body>
<div class="container">
    <div class="row" id="login">
        <a class="nav-link" href="<c:url value="/login?logout=true"/>"> <c:out value="${userName}"/> | Выйти</a>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h3>Дорожные проишествия</h3>
            </div>
            <div class="card-body">
                <form action="<c:url value="/create"/>">
                    <div class="form-group row">
                        <div class="col-sm-5">
                            <button type="submit" class="btn btn-primary">Добавить проишествие</button>
                        </div>
                    </div>
                </form>
                <table class="table table-bordered" style="table-layout: fixed">
                    <thead>
                    <tr>
                        <th style="width: 50px; text-align: center">№</th>
                        <th style="text-align: center">Тип</th>
                        <th style="text-align: center">Статья</th>
                        <th style="text-align: center">Описание</th>
                        <th style="text-align: center">Номер машины</th>
                        <th style="text-align: center">Адрес</th>
                        <th style="text-align: center">Дата</th>
                        <th style="text-align: center">Автор</th>
                        <th style="text-align: center">Статус</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${accidents}" var="map">
                        <tr>
                            <td>
                                <c:out value="${map.id}"/>
                                <a href="<c:url value='/edit?itemId=${map.id}'/>"><i class="fa fa-edit"></i></a>
                            </td>
                            <td><c:out value="${map.type.name}"/></td>
                            <td>
                                <c:forEach items="${map.rules}" var="rule">
                                    <c:out value="${rule.name}"/>
                                </c:forEach>
                            </td>
                            <td><c:out value="${map.description}"/></td>
                            <td><c:out value="${map.carNumber}"/></td>
                            <td><c:out value="${map.address}"/></td>
                            <td ><fmt:formatDate type="time" value="${map.created.time}" pattern="dd.MM.yyyy HH:mm:ss"/></td>
                            <td ><c:out value="${map.author}"/></td>
                            <td ><c:out value="${map.status}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>

