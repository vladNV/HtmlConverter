<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <%@ include file="../frame/head.html" %>
    <title>401 error</title>
</head>
<body>
<%@ include file="../frame/header.html" %>
<main>
    <div class="container">
        <div class="col-sm-12">
            <div class="col-sm-2"></div>
            <div class="col-sm-8 main-div">
                <h3>Плохой запрос</h3>
                <button type="button" onclick="window.history.back();"
                        class="btn btn-danger">Назад</button>
            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
</main>
<%@ include file="../frame/footer.html"%>
</body>
</html>
