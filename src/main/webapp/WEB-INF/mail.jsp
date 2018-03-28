<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="frame/head.html" %>
    <title>Login</title>
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
                               name="from" placeholder="enter your email">
                    </div>
                    <div class="form-group">
                        <input class="form-control" id="to"
                               name="to" placeholder="enter receiver">
                    </div>
                    <div class="form-group">
                        <input class="form-control" id="subject"
                               name="subject" placeholder="enter some subject">
                    </div>
                    <div class="form-group">
                        <input class="form-control" id="text"
                               name="text" placeholder="enter some text">
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
