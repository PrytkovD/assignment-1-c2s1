<%@tag description="Navbar Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="bg-white border-gray-300 py-2.5 px-2 sm:px-4 fixed w-full z-20 top-0 left-0 shadow">
    <div class="container flex flex-wrap items-center justify-between mx-auto">
        <a href="/" class="flex items-center space-x-2">
            <img src="/static/res/logo.svg" class="h-8" alt="Home"/>
            <span class="self-center text-xl font-semibold whitespace-nowrap dark:text-white">Баблометрика</span>
        </a>
        <a href="/sign_out" class="flex items-center space-x-2">
            <span>${authUser.name}</span>
            <img src="/static/res/sign_out.svg" class="h-6" alt="Sign Out"/>
        </a>
    </div>
</nav>
