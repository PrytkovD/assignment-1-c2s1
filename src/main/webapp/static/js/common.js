function noError(element, errorElement) {
    element.removeClass('border-red-600').addClass('border-gray-300');
    errorElement.html('');
    errorElement.hide();
}

function error(element, errorElement, message) {
    element.removeClass('border-gray-300').addClass('border-red-600');
    errorElement.html(message);
    errorElement.show();
}

function selectTab(tab, tabs) {
    tabs.removeClass('border-blue-600')
        .addClass('border-transparent')
        .addClass('hover:border-blue-600');
    for (const t of tabs) {
        $($(t).attr('target')).hide();
    }
    $(tab).addClass('border-blue-600')
        .removeClass('border-transparent')
        .removeClass('hover:border-blue-600');
    $($(tab).attr('target')).show();
}