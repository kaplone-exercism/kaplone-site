$(document).ready(function () {

    $('a.lien').click(function () {
        alert($(this).attr("id"));
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
        $('ul.tree').hide(600);
        $("a[id='" + $(this).attr("id") + "']").parent().parent().toggle(600);
    });
});