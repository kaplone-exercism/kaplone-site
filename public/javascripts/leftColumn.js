$(document).ready(function () {
    $('label.tree-toggler').click(function () {
        $('ul.tree').hide(600);
        $(this).parent().children('ul.tree').toggle(600);
    });
});

$(document).ready(function () {
    $('ul.tree').hide();

    $('ul.tree').click(function () {

        var out;

        addEventListener("turbolinks:before-render", function() {
            out = $(this).parent().children('label.tree-toggler');
            alert("save");
        });

        addEventListener("turbolinks:render", function() {
            out.toggle(600);
            alert("toggle");
        });
    });
});