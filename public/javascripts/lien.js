$(document).ready(function () {

    $('a.lien').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
        if ($("a[id='" + $(this).attr("id") + "']").parent().parent().is(":hidden")) {
            $('ul.tree').hide(600);
            $("a[id='" + $(this).attr("id") + "']").parent().parent().toggle(600);
        }
        window.scrollTo(0, 0);
        alert(fetchHeader("/assets/articles/" + $(this).attr("id") + ".html",'Last-Modified'));
    });

    function fetchHeader(url, wch) {
        try {
            var req=new XMLHttpRequest();
            req.open("HEAD", url, false);
            req.send(null);
            if(req.status== 200){
                return req.getResponseHeader(wch);
            }
            else return false;
        } catch(er) {
            return er.message;
        }
    }
});