/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// TODO : Adapter pour savoir si c'est un employé ou un client qui se connecte
// a moins que l'employe ne se connecte pas ?

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
            action: 'Connexion',
            courriel: $('#courriel').val()
        },
        dataType: 'text'
    })
    .done(function (data) {
        alert(data);
        console.log(data);
        var t="true";
        if (data==t){
            console.log(data+"asfafs");
            alert(data+"asfafs");
            window.location="profil.html";
        } else {
            console.log(data+"asfafs2");
            alert(data+"asfafs2");
            $('#traitementConnexion').html('');
            $('#connexionFail').html('Connexion refusée : Courriel non enregistré dans la base de données ');
        }
    });
};
