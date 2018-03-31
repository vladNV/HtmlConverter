<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="frame/head.html" %>
    <title>Поздравления</title>
</head>
<body>
<%@ include file="frame/header.html" %>
<main>
    <div class="container">
        <div class="col-sm-12">
            <div class="col-sm-2"></div>
            <div class="col-sm-8 main-div">
                <h3><span style="color:green;">
                    Сообщение было успешно отправлено !
                </span></h3>
                <div style="margin: 50px 0;">
                    <button type="button" onclick="window.location.href = '/'"
                            class="btn btn-danger">Назад</button>
                </div>
                <form method="post"
                      action="${pageContext.request.contextPath}/site/logout">
                    <div class="form-group">
                        <button class="btn btn-danger">
                            Закончить сессию
                        </button>
                    </div>
                </form>
            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
</main>
<%@ include file="frame/footer.html"%>
</body>
</html>
