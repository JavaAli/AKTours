<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 01.06.2019
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Standard Hotel</title>
</head>
<body>

<c:forEach items="${standardHotel}" var="hotelItem1">
    <div class="hotelData">
        <c:out value="${hotelItem1.name}"/>
    </div>
    <div>
   <span class="hotelData">
       <c:out value="${hotelItem1.standard}" />
   </span>
    </div>
</c:forEach>
</body>
</html>
