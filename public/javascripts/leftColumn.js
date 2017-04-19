$(document).ready(function () {
    $('ul.tree').hide();

    $('a.menu').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
    });

    $('a.cv').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
        $('ul.tree').hide(600);
        $("a[id='" + $(this).attr("id") + "']").parent().toggle(600);
        alert("a[id='" + $(this).attr("id") + "']");
        alert($("a[id='" + $(this).attr("id") + "']").attr("class"));
        alert($("a[id='" + $(this).attr("id") + "']").parent().parent().attr("class"));
    });

    $('label.tree-toggler').click(function () {
        $('ul.tree').hide(600);
        $(this).parent().children('ul.tree').toggle(600);
    });
});

