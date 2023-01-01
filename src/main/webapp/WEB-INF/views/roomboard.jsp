<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html>
<html>
<t:head title="Комната">
    <script src="<c:url value="${pageContext.request.contextPath}/static/js/roomboard.js"/>"></script>
</t:head>
<t:body>
    <t:navbar/>
    <div class="flex flex-col items-center justify-center mx-auto h-screen bg-gray-50">
        <div class="w-full sm:max-w-xl bg-white sm:rounded-lg shadow p-6 space-y-4">
                <%-- Menu --%>
            <div class="mb-4 border-b border-gray-300">
                <ul class="flex flex-wrap -mb-px text-sm font-medium text-center space-x-2" id="myTab" role="tablist">
                    <li>
                        <button class="inline-block p-4 rounded-t-lg border-b-2 border-blue-600" id="metrics-tab"
                                type="button" role="tab" target="#incoming">Входящие
                        </button>
                    </li>
                    <li>
                        <button class="inline-block p-4 rounded-t-lg border-b-2 border-blue-600" id="metrics-tab"
                                type="button" role="tab" target="#outgoing">Исходящие
                        </button>
                    </li>
                    <li>
                        <button class="inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:border-blue-600"
                                id="users-tab" type="button" role="tab" target="#users">Участники
                        </button>
                    </li>
                    <li>
                        <button class="inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:border-blue-600"
                                id="manage-tab" type="button" role="tab" target="#manage">Управление
                        </button>
                    </li>
                </ul>
            </div>
                <%-- Metrics --%>
            <div id="incoming">
                <c:forEach var="request" items="${requests}">
                    <div class="p-2 space-y-2">
                        <p>
                        Пользователь <span class="font-semibold">Denis2</span> запросил
                        <span class="font-semibold">${request.amount}</span> рублей.
                        </p>
                        <p class="block text-sm font-medium">Комментарий</p>
                        <p class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2">${request.description}</p>
                    </div>
                </c:forEach>
            </div>
                <%-- Metrics --%>
            <div id="outgoing">
                <form  id="money_request_form" action="${pageContext.request.contextPath}/dashboard/room" class="space-y-4">
                    <div>
                        <label for="user_select" class="block text-sm font-medium">Получатель</label>
                        <select id="user_select" name="user_select"
                                class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2">
                            <c:forEach var="assignee" items="${users}">
                                <option value="${assignee.id}">${assignee.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label for="amount" class="block text-sm font-medium">Сумма</label>
                        <input id="amount" name="amount" type="number"
                               class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2"
                               placeholder="100" required>
                    </div>
                    <div>
                        <label for="description" class="block text-sm font-medium">Комментарий</label>
                        <input id="description" name="description" type="text"
                               class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2"
                               placeholder="За пиво!" required>
                    </div>
                    <div>
                        <button id="submit"
                                class="w-full text-white text-sm font-medium border border-gray-300 bg-blue-600 hover:bg-blue-700 rounded-lg p-2">
                            Отправить
                        </button>
                    </div>
                </form>
            </div>
                <%-- Users --%>
            <div id="users">
                <div class="divide-y">
                    <c:forEach var="user" items="${users}">
                        <div class="p-2 space-y-2">
                            <p><c:out value="${user.name}"/></p>
                            <p><c:out value="${user.email}"/></p>
                        </div>
                    </c:forEach>
                </div>
            </div>
                <%-- Manage --%>
            <div id="manage" class="space-y-4">
                <p class="block text-sm font-medium">Ссылка</p>
                <div class="flex justify-center items-center space-x-2 w-full">
                    <p id="link" class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2">
                        <c:out value="http://localhost:8080/rooms/join?code=${room.code}"/>
                    </p>
                    <button id="copy" class="rounded-full p-2 bg-white hover:bg-gray-100">
                        <img src="${pageContext.request.contextPath}/static/res/copy.svg" class="h-6" alt="Sign Out"/>
                    </button>
                </div>
                <button id="delete"
                        class="w-full text-red-600 hover:text-white text-sm font-medium border border-red-600 bg-white hover:bg-red-600 rounded-lg p-2"
                        target="${room.code}">
                    Удалить комнату
                </button>
            </div>
        </div>
    </div>
</t:body>
</html>