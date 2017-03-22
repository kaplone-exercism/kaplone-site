$(document).ready(function () {
    $('label.tree-toggler').click(function () {
        $(this).parent().children('ul.tree').toggle(600);
    });
});

$(document).ready(function () {
    $('ul.tree').click(function () {
        var out;
        var level;

        addEventListener("turbolinks:before-render", function() {
            out = document.getElementById("scroll");
            level = out.scrollTop;
            alert(level);
        });

        addEventListener("turbolinks:render", function() {
            out.scrollTop = level;
        });
    });
});