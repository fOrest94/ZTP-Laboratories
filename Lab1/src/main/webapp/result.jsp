<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1><%=(String)request.getAttribute("method")%></h1>

<h3><%=(String)request.getAttribute("message")%></h3>

</body>
</html>
