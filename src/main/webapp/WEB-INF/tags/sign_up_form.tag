<%@tag description="Sign-Up Form Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="w-full sm:max-w-md bg-white sm:rounded-lg shadow p-6 space-y-4">
    <h1 class="text-xl font-bold leading-tight tracking-tight">Регистрация</h1>
    <form id="sign_up_form" action="/sign_up" class="space-y-4">
        <div>
            <label for="name" class="block text-sm font-medium">Имя</label>
            <input id="name" name="name" type="text"
                   class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2"
                   placeholder="Иван Петров"
                   required>
            <p id="nameError" class="text-sm text-red-600"></p>
        </div>
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
            <label for="confirmation" class="block text-sm font-medium">Повторите пароль</label>
            <input id="confirmation" name="confirmation" type="password"
                   class="block border border-gray-300 text-sm rounded-lg w-full my-2 p-2"
                   placeholder="••••••••"
                   required>
            <p id="confirmationError" class="text-sm text-red-600"></p>
        </div>
        <div>
            <button id="submit"
                    class="w-full text-white text-sm font-medium border border-gray-300 bg-blue-600 hover:bg-blue-700 rounded-lg p-2">
                Зарегестрироваться
            </button>
        </div>
        <p class="text-center text-sm">
            Уже есть аккаунт? <a href="/sign_in" class="text-sm font-medium text-blue-600">Вход</a>
        </p>
    </form>
</div>