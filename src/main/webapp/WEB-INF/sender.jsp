<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@ include file="frame/head.html" %>
    <title>Sender</title>
</head>
<body>
<%@ include file="frame/header.html" %>
    <main>
        <div class="container">
            <div class="col-sm-12">
                <div class="col-sm-2"></div>
                <div class="col-sm-8 main-div">
                    <form action="${pageContext.request.contextPath}/rest/upload"
                          method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <input name="xls" id="xls" type="file"/>
                        </div>
                        <div class="form-group">
                            <button class="btn-success btn" name="upload">
                                Upload</button>
                        </div>
                        <input type="hidden" name="login" value="${sessionScope.login}">
                    </form>
                </div>
                <div class="col-sm-2"></div>
            </div>
        </div>
    </main>
<%@ include file="frame/footer.html"%>
</body>
</html>
