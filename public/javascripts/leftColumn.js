$(document).ready(function () {
    $('label.tree-toggler').click(function () {
        $(this).parent().children('ul.tree').toggle(600);
    });
});

$(document).ready(function () {
    $('ul.tree').click(function () {
        var out = document.getElementById("scroll");
        out.scrollTop = out.scrollHeight - out.clientHeight;
    });
});