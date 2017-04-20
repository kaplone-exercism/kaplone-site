$(document).ready(function () {
    $('ul.tree').hide();

    $('a.menu').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
    });

    $('a.cv').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
        $('ul.tree').hide(600);
        $("a[id='" + $(this).attr("id") + "']").parent().parent().toggle(600);
        window.scrollTo(0, 0);
    });

    $('a.home').click(function() {
        $('ul.tree').hide(600);
        $("#includedContent").load("/assets/articles/cv.html");
        window.scrollTo(0, 0);
    });

    $('a.expand').click(function() {
        $('ul.tree').hide(60);
        $('ul.tree').toggle(600);
        window.scrollTo(0, 0);
    });

    $('label.tree-toggler').click(function () {
        $('ul.tree').hide(600);
        $(this).parent().children('ul.tree').toggle(600);
    });
});

