$(document).ready(function () {
    $('label.tree-toggler').click(function () {
        $('ul.tree').hide(600);
        $(this).parent().children('ul.tree').toggle(600);
    });
});

$(document).ready(function () {
    $('ul.tree').hide();

    $('ul.tree').click(function () {
         $('ul.tree').hide(600);
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