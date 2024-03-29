<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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

    <script>
        function validate() {
            let rsl = true
            let atr = $('.form-control')
            for (let node of atr) {
                if (node.value === '' || node.value === null) {
                    alert(node.getAttribute('title'));
                    rsl = false
                    break
                }
            }
            return rsl
        }
    </script>

    <title>Edit accident</title>
</head>

<body>
<div class="container">
    <div class="row" id="login">
        <a class="nav-link" href="<c:url value="/login?logout=true"/>"> <c:out value="${userName}"/> | Выйти</a>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h3>Редактирование проишествия № <c:out value="${accident.id}"/></h3>
            </div>
            <div class="card-body">
                <form action="<c:url value='/save'/>" method="post">
                    <div class="form">
                        <div id="id">
                            <input type="hidden" name="id" value="${accident.id}">
                        </div>
                        <div class="form-group" id="type">
                            <label for="typeSel" >Тип проишествия: &nbsp</label>
                            <select id="typeSel" name="type.id" style="width: 280px">
                                <option value="${accident.type.id}">${accident.type.name}</option>
                                <c:forEach var="type" items="${types}" >
                                    <c:if test="${type.id != accident.type.id}">
                                        <option value="${type.id}">${type.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" id="status">
                            <label for="statusSel" style="margin-right: 83px">Статус: &nbsp</label>
                            <select id="statusSel" name="status" style="width: 280px">
                                <option value="${accident.status}" selected>${accident.status}</option>
                                <option value="На рассмотрении">На рассмотрении</option>
                                <option value="Отклонено">Отклонено</option>
                                <option value="Завершено">Завершено</option>

                            </select>
                        </div>
                        <div class="form-group" id="rule">
                            <label for="ruleSel" style="vertical-align: top; margin-right: 82px">Статья: &nbsp</label>
                            <select id="ruleSel" name="rIds" multiple required style="width: 280px">
                                <c:forEach var="rule" items="${rules}">
                                    <c:set var="sel" value=""/>
                                    <c:forEach var="acdRule" items="${accident.rules}" >
                                        <c:if test="${rule.id eq acdRule.id}">
                                            <c:set var="sel" value="selected"/>
                                        </c:if>
                                    </c:forEach>
                                    <option ${sel} value="${rule.id}">${rule.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" id="carNumber">
                            <label style="float: left; margin-right: 53px">Номер Авто:</label>
                            <input type="text" class="form-control" name="carNumber" value="${accident.carNumber}"
                                   title="Заполните поле: Номер Авто" style="width: 280px">
                        </div>
                        <div class="form-group" id="address">
                            <label style="float: left; margin-right: 98px">Адрес:</label>
                            <input type="text" class="form-control" name="address" value="${accident.address}"
                                   title="Заполните поле: Адрес" style="width: 280px">
                        </div>
                        <div class="form-group" id="description">
                            <label style="float: left; margin-right: 70px">Описание:</label>
                            <textarea maxlength="255" rows="3" class="form-control" name="description"
                                      title="Заполните поле: Описание" style="height: 113px; width: 280px">${accident.description}</textarea>
                        </div>
                        <div id="author">
                            <input type="hidden" name="author" value="${accident.author}">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>