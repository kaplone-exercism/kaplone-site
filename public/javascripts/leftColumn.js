$(document).ready(function () {
    $('ul.tree').hide();

    $('a.menu').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("data-lien") + ".html");
    });

    $('a.cv').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("data-lien") + ".html");
        $('ul.tree').hide(600);
        $("a[id='" + $(this).attr("data-lien") + "']").parent().parent().toggle(600);
    });

    $('label.tree-toggler').click(function () {
        $('ul.tree').hide(600);
        $(this).parent().children('ul.tree').toggle(600);
    });
});

