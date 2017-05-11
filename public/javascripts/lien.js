$(document).ready(function () {

    $('a.lien').click(function () {
        $("#includedContent").load("/assets/articles/" + $(this).attr("id") + ".html");
        if ($("a[id='" + $(this).attr("id") + "']").parent().parent().is(":hidden")) {
            $('ul.tree').hide(600);
            $("a[id='" + $(this).attr("id") + "']").parent().parent().toggle(600);
        }
        window.scrollTo(0, 0);

//        alert(fetchHeader("/assets/articles/" + $(this).attr("id") + ".html",'Last-Modified'));
    });


    // reproduire dans leftColumn.js pour a.menu
    // et dans cv.js pour a.cv
    function fetchHeader(url, wch) {
        try {
            var req=new XMLHttpRequest();
            req.open("HEAD", url, false);
            req.send(null);
            if(req.status== 200){
                var derniereModif= req.getResponseHeader(wch);
                var dateModif = new Date(derniereModif);
                var jour = dateModif.getDate();
                var mois = dateModif.getMonth() + 1;
                var annee = dateModif.getYear() + 1900;
                return "Dernière modification de la page le " + jour + "/" + mois + "/" + annee;
            }
            else return false;
        } catch(er) {
            return er.message;
        }
    }
});