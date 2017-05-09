$(document).ready(function () {

    $('a.cv').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
        $('ul.tree').hide(600);
        $("a[id='" + $(this).attr("id") + "']").parent().parent().toggle(600);
        window.scrollTo(0, 0);
    });
});