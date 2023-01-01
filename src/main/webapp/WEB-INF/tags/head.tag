<%@tag description="Head Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="title"%>

<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <script src="<c:url value="${pageContext.request.contextPath}/node_modules/jquery/dist/jquery.min.js"/>"></script>
    <script src="<c:url value="${pageContext.request.contextPath}/static/js/common.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/static/css/style.css"/>">
    <jsp:doBody/>
</head>