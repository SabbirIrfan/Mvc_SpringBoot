<%--
  Created by IntelliJ IDEA.
  User: john_doe
  Date: ২৫/১/২৪
  Time: ১১:০৫ AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<%--%>
<%--    String name = (String) request.getAttribute("name");--%>

<%--%>--%>


<h1>
    <c:forEach var="item" items="${marks}">
        <h1>marks ${item}</h1>
    </c:forEach>
        the name from the model view is ${name}
</h1>
</body>
</html>
