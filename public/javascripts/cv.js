$(document).ready(function () {

    $('a').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
        $('ul.tree').hide(600);
        $("a#" + $(this).attr("id")).parent().children('ul.tree').toggle(600);
    });
});

