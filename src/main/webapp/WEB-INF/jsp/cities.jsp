<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 09.06.2019
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cities</title>
</head>
<body>

<c:forEach items="${cities}" var="cityItem">
    <div class="cityData">
        <c:out value="${cityItem.name}"/>
<%--        <c:out value="${cityItem.airports}"/>--%>
    </div>
</c:forEach>

</body>
</html>
