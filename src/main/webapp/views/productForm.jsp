<%--
  Created by IntelliJ IDEA.
  User: john_doe
  Date: ৫/২/২৪
  Time: ২:১৩ PM
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
    <div>
        <%@include file="navbar.jsp"%>
    </div>

<div class="form_container">
    <form action="addproduct" class="row g-3" method="post" >
        <div class="col-md-6">
            <label for="productName" class="form-label">Brand Name</label>
            <input type="text" class="form-control" id="productName" name="brandName">
        </div>
        <div class="col-md-6">
            <label for="productModel" class="form-label">Model</label>
            <input type="text" class="form-control" id="productModel" name="productModel">
        </div>
        <div class="col-12">
            <label for="productDetail" class="form-label">Product Detail</label>
            <input type="text" class="form-control" id="productDetail" placeholder="productDetail" name ="productDetail">
        </div>
        <div class="col-md-4">
            <label for="processor" class="form-label">processor</label>
            <select id="processor" class="form-select" name="processor">
                <option>Intel</option>
                <option>Ryzen</option>
            </select>
        </div>

        <div class="col-md-4">
            <label for="generation" class="form-label">generation</label>
            <select id="generation" class="form-select" name="generation">
                <option selected>Choose...</option>
                <option>i5-14th gen</option>
                <option>i7-14th gen</option>
                <option>i9-14th gen</option>
            </select>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Add Product</button>
        </div>
    </form>
</div>
</div>
</body>
</html>
