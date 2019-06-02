<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 02.06.2019
  Time: 09:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Countries</title>
</head>
<body>
<c:forEach items="${countries}" var="countryItem">
    <div class="countryData">
        <c:out value="${countryItem.name}"/>
    </div>
    <div>
   <span class="countryData">
       <c:out value="${countryItem.id}" />
   </span>
    </div>
</c:forEach>
</body>
</html>
