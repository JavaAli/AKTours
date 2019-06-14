<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 09.06.2019
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Continents</title>
</head>
<body>
<c:forEach items="${continents}" var="continentItem">
    <div class="continentData">
        <c:out value="${continentItem.name}"/>
    </div>

</c:forEach>
</body>
</html>
