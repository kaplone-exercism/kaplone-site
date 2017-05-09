$(document).ready(function () {

    $('a.lien').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
        $('ul.tree').hide(200);
        $("a[id='" + $(this).attr("id") + "']").parent().parent().toggle(600);
        window.scrollTo(0, 0);
    });
});