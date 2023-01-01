$(document).ready(function () {
    const profile = $('#profile');
    const rooms = $('#rooms');

    $('#new_room_form').hide();

    const tabs = $('button[role="tab"]');

    selectTab(tabs[0], tabs);

    tabs.click(function () {
        selectTab($(this), tabs);
    });

    $('#new_room').click(function () {
        $(this).hide();
        $('#new_room_form').show();
    })

    const buttons = $('button[role="link"]');

    buttons.click(function () {
        window.location = '/rooms/join?code=' + $(this).attr('target');
    });
});