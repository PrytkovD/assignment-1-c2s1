$(document).ready(function () {
    $('#link_holder').hide();

    const link = $('#link');

    $('#submit').click(function (event) {
        event.preventDefault();

        const form = $('#create_room_form');

        $.post(form.attr('action'), form.serialize(), function (data) {
            if (data.success === true) {
                link.html(data.link);
                $('#hyperlink').attr('href', data.link);
                $('#submit').hide();
                $('#link_holder').show();
            }
        });
    });

    $('#copy').click(function (event) {
        event.preventDefault();

        navigator.clipboard.writeText($('#link').innerText).then(function () {}, function (error) {});
    })
});