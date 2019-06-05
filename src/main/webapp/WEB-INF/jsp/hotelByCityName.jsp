<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 04.06.2019
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HotelByCityName</title>
</head>
<body>
<h1>Hotel</h1>

<c:forEach items="${hotelName1}" var="hotelItem">
    <div class="hotelData">
        <c:out value="${hotelItem.name}"/>
    </div>
    <div>
   <span class="hotelData">
       <c:out value="${hotelItem.standard}" />
   </span>
    </div>
    <p> <c:out value="${hotelItem.description}"/></p>
</c:forEach>
</body>
</html>
