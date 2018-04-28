/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    // ajout d'un "handler" sur le clic du bouton de Confirmer Inscription
    $('#validerForm').on('click', function () {
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Confirmer inscription"');
        inscription();
    });
});

function inscription() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'ConfirmationInscription',
            civilite: $('civilite option:selected').text(),
            nom: $('#nom').val(),
            prenom: $('#prenom').val(),
            dateNaissance: $('#dateNaissance').val(),
            telephone: $('#telephone').val(),
            mail: $('#mail').val(),
            adresse: $('#adresse').val()
        },
        dataType:'text'
    })
    .done(function(data){
        alert(data);
        if (data){
            window.location="confEnvoieMail.html";
        }
        else {
            alert('done_erreur');
            $('#msgErreur').html("Erreur lors de l'inscription");
        }
    });
};