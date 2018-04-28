/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    recupererInfoClient();
    //desactiverLien();
});

function recupererInfoClient() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoClient'
        },
        dataType:'json'
    })
    .done(function(data){
        alert(data);
        remplirChamp(data);
    });
};

function remplirChamp(data) {
    $('#prenom').html(data.prenom);
    $('#signezodiaque').html(data.signeZodiaque);
    $('#signechinois').html(data.signeChinois);
    $('#couleur').html(data.couleur);
    $('#animal').html(data.animalTotem);
}

/*function desactiverLien() {
    var chemin = $(location).attr(pathname);
    if (contains(chemin, 'profil')) {
        $('#consulterProfil').css('text-decoration: none; color: black');
        $('#consulterProfil').on('click', function() {
            return false;
        });
        $('#serviceVoyance').css('text-decoration: underline; color: blue');
        $('#serviceVoyance').on('click', function() {
            return true;
        });
    }
    else {
        $('#serviceVoyance').css('text-decoration: none; color: black');
        $('#serviceVoyance').on('click', function() {
            return false;
        });
        $('#consulterProfil').css('text-decoration: underline; color: blue');
        $('#consulterProfil').on('click', function() {
            return true;
        });
    }
}*/