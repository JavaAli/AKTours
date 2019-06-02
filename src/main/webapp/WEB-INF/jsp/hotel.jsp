<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 01.06.2019
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotel</title>
</head>
<body>
<c:forEach items="${hotel}" var="hotelItem">
    <div class="hotelData">
        <c:out value="${hotelItem.name}"/>
    </div>
    <div>
   <span class="hotelData">
       <c:out value="${hotelItem.standard}" />
   </span>
    </div>
</c:forEach>
</body>
</html>
