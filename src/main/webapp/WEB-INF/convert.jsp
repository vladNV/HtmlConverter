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
                <form action="${pageContext.request.contextPath}/site/convert"
                      method="post">
                    <h3>Information</h3>
                    <div class="form-group">
                        <label for="sheet1">Sheet number</label>
                        <input class="form-control"
                               type="number"
                               id="sheet1" name="sheet1">
                    </div>
                    <div class="form-group">
                        <label for="column1">Column number</label>
                        <input class="form-control"
                               type="number"
                               id="column1" name="column1">
                    </div>
                    <h3>Table</h3>
                    <div class="form-group">
                        <label for="sheet2">Sheet number with table</label>
                        <input class="form-control"
                               type="number"
                               id="sheet2" name="sheet2">
                    </div>
                    <div class="form-group">
                        <label for="column2">Column number with table</label>
                        <input class="form-control"
                               type="number"
                               id="column2" name="column2">
                    </div>
                    <div class="form-group">
                        <button class="btn-success btn" name="upload">
                            Convert</button>
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
