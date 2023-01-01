<%@tag description="Sign-In Form Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w-full sm:max-w-md bg-white sm:rounded-lg shadow p-6 space-y-4">
    <h1 class="text-xl font-bold leading-tight tracking-tight">Вход</h1>
    <form id="sign_in_form" action="/sign_in" class="space-y-4">
        <div>
            <label for="email" class="block text-sm font-medium">Email</label>
            <input id="email" name="email" type="email"
                   class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2"
                   placeholder="ivan.petrov@mail.com" required>
            <p id="emailError" class="text-sm text-red-600"></p>
        </div>
        <div>
            <label for="password" class="block text-sm font-medium">Пароль</label>
            <input id="password" name="password" type="password"
                   class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2"
                   placeholder="••••••••"
                   required>
            <p id="passwordError" class="text-sm text-red-600"></p>
        </div>
        <div>
            <button id="submit"
                    class="w-full text-white text-sm font-medium border border-gray-300 bg-blue-600 hover:bg-blue-700 rounded-lg p-2">
                Войти
            </button>
        </div>
        <p class="text-center text-sm">
            Вы здесь новенький? <a href="/sign_up" class="text-sm font-medium text-blue-600">Регистрация</a>
        </p>
    </form>
</div>