/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    // ajout d'un "handler" sur le clic du bouton de Confirmer Inscription
    $('#seConnecter').on('click', function () {
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Accéder au service"');
        connexion();
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
        dataType: 'text'
    })
    .done(function (data) {
        if (data){
            window.location="profil.html";
        } else {
            $('#traitementConnexion').html('');
            $('#connexionFail').html('Connexion refusée : Courriel non enregistré dans la base de données ');
        }
    });
};
