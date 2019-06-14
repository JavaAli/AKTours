<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 09.06.2019
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Airports</title>
</head>
<body>
<c:forEach items="${airports}" var="airportItem">
    <div class="airportData">
    <c:out value="${airportItem.name}"/>
    </div>
</c:forEach>
</body>
</html>
