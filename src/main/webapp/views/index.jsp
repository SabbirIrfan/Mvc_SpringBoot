<%--
  Created by IntelliJ IDEA.
  User: john_doe
  Date: ২৪/১/২৪
  Time: ৮:১৩ PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%
    String name = (String) request.getAttribute("name");
    int count = (Integer) request.getAttribute("count");
    int countStatic = (Integer) request.getAttribute("CountStatic");

%>

<h1> HEELLOO from spring mvc <br> from the controller to view and the name we got is <%= name%>
    <br>  and the count is <%= count%>  and CountStatic is <%=countStatic%> </h1>
<a href="countH">Count :: HTTPSERVLET :: and  </a>
</body>
</html>
