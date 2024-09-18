<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="org.springframework.format.annotation.DateTimeFormat" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  Seller: john_doe
  Date: ২৯/১/২৪
  Time: ১০:১০ AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Hello, world!</title>

</head>
<body>

<%!          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");%>
    <div class="counterForm">
        <form action="countH" method="post">

            <div class="counterHeading">
                <h1>Count is now at <%= request.getAttribute("count")%> </h1> <br>
                <h1>static Count is now at <%= request.getAttribute("staticSount")%></h1>
            </div>

            <div class="counterBox">
                <h3>Date :: <%=  LocalDate.now() +" Time ::"  %> > </h3>
                <h3> <%=   LocalDateTime.now().format(formatter) %></h3>
            </div>

            <button type="submit" class="button">Submit</button>
        </form>
    </div>
</body>
</html>