$(document).ready(function () {
    const tabs = $('button[role="tab"]');

    selectTab(tabs[0], tabs);

    tabs.click(function () {
        selectTab($(this), tabs);
    });

    $('#submit').click(function (event) {
        event.preventDefault();

        const form = $('#money_request_form');

        $.post(form.attr('action'), form.serialize());
    });

    $('#delete').click(function () {
        $.get('/rooms/delete', { code: $(this).attr('target') });
    })
});