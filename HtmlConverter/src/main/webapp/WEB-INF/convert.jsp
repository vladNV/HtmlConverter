<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="frame/head.html" %>
    <title>Конвертация</title>
</head>
<body>
<%@ include file="frame/header.html" %>
<main>
    <div class="container">
        <div class="col-sm-12">
            <div class="col-sm-2"></div>
            <div class="col-sm-8 main-div">
                <form action="${pageContext.request.contextPath}/site/convert"
                      method="post">
                    <div class="form-group">
                        <button class="btn-success btn" name="upload">
                            Конвертировать в pdf</button>
                    </div>
                    <form method="post"
                            action="${pageContext.request.contextPath}/site/logout">
                        <button style="margin-bottom: 300px;"
                                class="btn btn-danger">
                            Закончить сессию
                        </button>
                    </form>
                </form>
            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
</main>
<%@ include file="frame/footer.html"%>
</body>
</html>
