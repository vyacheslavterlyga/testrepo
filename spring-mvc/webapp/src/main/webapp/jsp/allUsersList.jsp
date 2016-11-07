<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>All users</title>

</head>
<body>
<table>
<c:forEach var = "listValue" items = "${lists}">
    <tr>
      <%for(int i = 0; i<lists.size; i++){ %>
                <td>
                    <%=${listValue.get(i)}%>
                </td>
            <%} %>
    </tr>
  </c:forEach>
</table> 
	
</body>
</html>