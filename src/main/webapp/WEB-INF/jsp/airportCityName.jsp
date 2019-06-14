<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 09.06.2019
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Airport City name</title>
</head>
<body>
<c:forEach items="${airportCityName}" var="airportItem">
    <div class="airportData">
        <c:out value="${airportItem.name}"/>
    </div>
</c:forEach>
</body>
</html>
