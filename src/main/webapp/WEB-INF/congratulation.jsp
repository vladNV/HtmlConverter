<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="frame/head.html" %>
    <title>Congratulation</title>
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
                <button type="button" onclick="window.location.href = '${pageContext}'"
                        class="btn btn-danger">Назад</button>
                <br>
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
