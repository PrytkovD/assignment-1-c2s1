<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html>
<html>
<t:head title="Создать комнату">
    <script src="${pageContext.request.contextPath}/static/js/create_room.js"></script>
</t:head>
<t:body>
    <div class="flex flex-col items-center justify-center mx-auto h-screen bg-gray-50">
        <t:create_room_form/>
    </div>
</t:body>
</html>