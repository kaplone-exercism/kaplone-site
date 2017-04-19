$(document).ready(function () {
    $('ul.tree').hide();

    $('a.menu').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
    });

    $('label.tree-toggler').click(function () {
        $('ul.tree').hide(600);
        $(this).parent().children('ul.tree').toggle(600);
    });
});

