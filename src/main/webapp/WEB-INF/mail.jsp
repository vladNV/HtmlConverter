<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="frame/head.html" %>
    <title>Отправка письма</title>
</head>
<body>
<%@ include file="frame/header.html" %>
<main>
    <div class="container">
        <div class="col-sm-12">
            <div class="col-sm-2"></div>
            <div class="col-sm-8 main-div">
                <form method="post"
                      action="${pageContext.request.contextPath}/site/mail">
                    <div class="form-group">
                        <input class="form-control" id="from"
                               name="from" placeholder="Отправителя (необязательно)">
                    </div>
                    <div class="form-group">
                        <input class="form-control" id="to"
                               required name="to" placeholder="Введите получателя">
                    </div>
                    <div class="form-group">
                        <input class="form-control" id="subject"
                               required name="subject" placeholder="Введите тему письма">
                    </div>
                    <div class="form-group">
                        <input class="form-control" id="text"
                               required name="text" placeholder="Введите какой нибудь текст">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-success">Отправить письмо</button>
                    </div>
                </form>
                <form method="post"
                        action="${pageContext.request.contextPath}/site/logout">
                    <button style="margin-bottom: 300px;"
                            class="btn btn-danger">
                        Закончить сессию
                    </button>
                </form>
            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
</main>
<%@ include file="frame/footer.html"%>
</body>
</html>
