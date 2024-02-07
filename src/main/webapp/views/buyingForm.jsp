<%@ page import="java.util.List" %>
<%@ page import="com.dsi.project.model.Product" %>

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
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <%@include file="allLink.jsp"%>



</head>
<body>

<div class="container">
    <%@include file="navbar.jsp"%>

    <div class="card">

        <form action="orderProduct" method="post" class="form_container">
            <div class="user_container">


                <label for="userEmail">User Email</label>
                <input  name="email" id="userEmail" placeholder="User Email">
            </div>
            <div class="product_container">
                <label for="product">Example select</label>
                <select class="form_select" name="product" id="product">

                    <%
                        List<Product> list =(List<Product>) request.getAttribute("availableProductList");
                        if(list!=null){
                            for (Product product : list) {
                                System.out.println(product.getBrandName());
                    %>
                    <option><%= product.getId() %>  <%= product.getBrandName() %> - <%= product.getProcessor() %> - <%= product.getGeneration()%>  </option>
                    <%
                            }
                        }
                    %>

                </select>
                <div class="form-group formPad">
                    <label for="productDetail">Product Detail</label>
                    <textarea  class="textarea" id="productDetail" name="detail" placeholder="Write something about you about this product." required></textarea>
                </div>


                <button type="submit" class="btn btn-primary">Submit</button>
            </div>

        </form>
    </div>

    </div>
</body>
</html>
