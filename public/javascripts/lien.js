$(document).ready(function () {

    $('a.lien').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
        if ($("a[id='" + $(this).attr("id") + "']").parent().parent().is(":hidden")) {
            $('ul.tree').hide(600);
            $("a[id='" + $(this).attr("id") + "']").parent().parent().toggle(600);
        }
        window.scrollTo(0, 0);
    });
});