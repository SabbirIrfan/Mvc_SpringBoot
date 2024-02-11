<%@ page import="java.util.List" %>
<%@ page import="com.dsi.project.model.Product" %>
<%@ page import="java.util.ArrayList" %>

<%--
  Created by IntelliJ IDEA.
  User: john_doe
  Date: ৫/২/২৪
  Time: ১২:৫৩ PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="allLink.jsp"%>


</head>
<body>
<div class="container">
    <div >
        <%@include file="navbar.jsp"%>
        <h4> Hello! Welcome to home page of bugichugi product web"cite" !! </h4>

    </div>
    <div class="card_container">

        <%
            List<Product> productList = new ArrayList<>();
            productList = (List<Product>) request.getAttribute("productList");
            for(Product product: productList){
        %>
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="https://www.bhphotovideo.com/images/images2500x2500/dell_inspiron_17_i17rv_5454blk_17_3_1004767.jpg" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title"><%= product.getBrandName() %></h5>
                <p class="card-text"><%= product.getProductDetail()  %></p>
                <a href="#" style="width: 10rem; height: 2.6rem" class="btn btn-primary">Sold ::<%=product.isSold()%></a>
            </div>
        </div>
        <%
            }
        %>
    </div>

</div>

</body>
</html>
