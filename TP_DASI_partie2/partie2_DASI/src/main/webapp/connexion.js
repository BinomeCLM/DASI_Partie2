/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('#seConnecter').on('click', function () {
        connexion();
    });
    $('.lesEmp').on('click', function () {
        var id = $(this).children().first().attr('id');
        connecterEmploye(id);
    });
});

function connexion() {
    $('#traitementConnexion').html('Connexion en cours ...');
    $.ajax({
        url: './ActionServlet',
        type: 'POST',
        data: {
            action: 'ConnexionClient',
            courriel: $('#courriel').val()
        },
        dataType: 'json'
    })
    .done(function (data) {
        if (data.success == true){
            window.location="profil.html";
        } else {
            $('#traitementConnexion').html('');
            $('#connexionFail').html('Connexion refusée : Courriel non enregistré dans la base de données ');
        }
    });
};

function connecterEmploye(idEmploye) {
    $.ajax({
       url : './ActionServlet',
       type: 'POST',
       data : {
           action : 'ConnexionEmploye',
           idEmp : idEmploye
       },
       dataType : 'json'
    })
    .done(function(data){
        if (data.success == true){
            window.location = 'demandeDeVoyance.html'
        }
        else {
            alert('Connexion employé a échoué.')
        }
    });
}