$(document).ready(function () {
    $('ul.tree').hide();
});

$('a.menu').click(function () {
    //$('ul.tree').hide();
    //$(this).parent().parent().children('label.tree-toggler').toggle(600);
    $("#includedContent").load("/assets/images/articles/"+$(this).attr("id"));
});

$('label.tree-toggler').click(function () {
    $('ul.tree').hide(600);
    $(this).parent().children('ul.tree').toggle(600);
});