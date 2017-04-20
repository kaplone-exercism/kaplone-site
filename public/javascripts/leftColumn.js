$(document).ready(function () {
    $('ul.tree').hide();

    $('a.menu').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
        window.scrollTo(0, 0);
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
        $(this).parent().parseHTML('<a class="collapse"><img src="/assets/images/TreeView.gif" height="42" width="42"><label class="tree-toggler">Replier tout</label></a>');
        window.scrollTo(0, 0);
    });

    $('a.collapse').click(function() {
        $('ul.tree').toggle(600);
        $(this).parent().parseHTML('<a class="expand"><img src="/assets/images/icon-65-128.png" height="42" width="42"><label class="tree-toggler">Déplier tout</label></a>');
        window.scrollTo(0, 0);
    });

    $('label.tree-toggler').click(function () {
        $('ul.tree').hide(600);
        $(this).parent().children('ul.tree').toggle(600);
    });
});

