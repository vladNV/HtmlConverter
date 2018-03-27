<%@ page contentType="text/html;charset=UTF-8" %>
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
            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
</main>
<%@ include file="frame/footer.html"%>
</body>
</html>
