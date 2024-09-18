<%--
  Created by IntelliJ IDEA.
  User: john_doe
  Date: ৫/২/২৪
  Time: ৩:৫৩ PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="allLink.jsp"%>
</head>
<body>

<div class="body-container">
    <div>
        <%@include file="navbar.jsp" %>
    </div>

        <div >
            <form class="form_container row" action="adduser" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email address</label>
                    <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email">
                    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">name</label>
                    <input type="text" class="form-control" id="name" name="name">
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
</div>

</body>
</html>
