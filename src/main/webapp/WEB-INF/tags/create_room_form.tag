<%@tag description="Sign-Up Form Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w-full sm:max-w-md bg-white sm:rounded-lg shadow p-6 space-y-4">
    <h1 class="text-xl font-bold leading-tight tracking-tight">Создать комнату</h1>
    <form id="create_room_form" action="/rooms/create" class="space-y-4">
        <div>
            <label for="name" class="block text-sm font-medium">Название</label>
            <input id="name" name="name" type="text"
                   class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2"
                   placeholder="Черепашки-Ниндзя"
                   required>
            <p id="nameError" class="text-sm text-red-600"></p>
        </div>
        <div>
            <button id="submit"
                    class="w-full text-white text-sm font-medium border border-gray-300 bg-blue-600 hover:bg-blue-700 rounded-lg p-2">
                Создать
            </button>
        </div>
        <div id="link_holder">
            <p class="block text-sm font-medium">Ссылка</p>
            <div class="flex justify-center items-center space-x-2 w-full">
                <p id="link" class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2"></p>
                <button id="copy" class="rounded-full p-2 bg-white hover:bg-gray-100">
                    <img src="${pageContext.request.contextPath}/static/res/copy.svg" class="h-6" alt="Sign Out"/>
                </button>
            </div>
            <a id="hyperlink" href="" class="block w-full text-white text-sm font-medium border border-gray-300 bg-blue-600 hover:bg-blue-700 rounded-lg p-2">
                Перейти
            </a>
        </div>
    </form>
</div>