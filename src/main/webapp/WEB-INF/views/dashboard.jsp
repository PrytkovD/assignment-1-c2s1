<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html>
<html>
<t:head title="Главная">
    <script src="<c:url value="${pageContext.request.contextPath}/static/js/dashboard.js"/>"></script>
</t:head>
<t:body>
    <t:navbar/>
    <div class="flex flex-col items-center justify-center mx-auto h-screen bg-gray-50">
        <div class="w-full sm:max-w-xl bg-white sm:rounded-lg shadow p-6 space-y-4">
                <%-- Menu --%>
            <div class="mb-4 border-b border-gray-300">
                <ul class="flex flex-wrap -mb-px text-sm font-medium text-center space-x-2" id="myTab" role="tablist">
                    <li>
                        <button class="inline-block p-4 rounded-t-lg border-b-2 border-blue-600" id="profile-tab"
                                type="button" role="tab" target="#profile">Профиль
                        </button>
                    </li>
                    <li>
                        <button class="inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:border-blue-600"
                                id="dashboard-tab" type="button" role="tab" target="#rooms">Комнаты
                        </button>
                    </li>
                </ul>
            </div>
                <%-- Profile --%>
            <div id="profile">
                <div>
                    <p class="block text-sm font-medium">Имя</p>
                    <div class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2">
                        <c:out value="${authUser.name}"/>
                    </div>
                </div>
                <div>
                    <p class="block text-sm font-medium">Email</p>
                    <div class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2">
                        <c:out value="${authUser.email}"/>
                    </div>
                </div>
            </div>
                <%-- Rooms --%>
            <div id="rooms">
                <div class="divide-y">
                    <c:forEach var="room" items="${rooms}">
                        <div class="flex justify-center items-center space-x-2 w-full p-2">
                            <p class="w-full"><c:out value="${room.name}"/></p>
                            <button class="block text-white text-sm font-medium border border-gray-300 bg-blue-600 hover:bg-blue-700 rounded-lg p-2"
                                    target="${room.code}" role="link">Перейти
                            </button>
                        </div>
                    </c:forEach>
                </div>
                <a href="/rooms/create"
                   class="block border-2 flex justify-center border-dashed border-gray-300 text-gray-300 rounded-lg w-full my-2 p-2 hover:border-blue-600 hover:text-blue-600">
                    <span class="text-sm font-medium">Новая комната</span>
                </a>
            </div>
        </div>
    </div>
</t:body>
</html>