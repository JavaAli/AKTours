<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 02.06.2019
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotel Name</title>
</head>
<body>

<div class="hotelData">
    <c:out value="${hotelName.name}"/>
</div>
<div>
   <span class="hotelData">
       <c:out value="${hotelName.standard}" />
   </span>
</div>
</body>
</html>
