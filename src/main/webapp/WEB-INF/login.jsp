<%@ page contentType="text/html;charset=UTF-8" %>
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
                <form action="${pageContext.request.contextPath}/site/login">
                    <div class="form-group">
                        <input class="form-control" id="login"
                        name="login" placeholder="enter your login">
                    </div>
                    <div class="form-group">
                        <input class="form-control" id="password"
                        name="password" placeholder="enter your password"
                        type="password">
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
