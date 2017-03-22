$(document).ready(function () {
    $('label.tree-toggler').click(function () {
        $('ul.tree').hide();
        $(this).parent().children('ul.tree').toggle(600);
    });
});

$(document).ready(function () {
    $('ul.tree').hide(600);

    $('ul.tree').click(function () {
         $(this).parent().children('label.tree-toggler').toggle(600);
    });
//        var out;
//        var level;
//
//        addEventListener("turbolinks:before-render", function() {
//            out = document.getElementById("scroll");
//            level = out.scrollTop;
//            alert(level);
//        });
//
//        addEventListener("turbolinks:render", function() {
//            out.scrollTop = level;
//        });
//    });
});