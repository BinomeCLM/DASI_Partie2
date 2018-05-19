/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    
    $('#validerForm').on('click', function () {
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
        dataType:'json'
    })
    .done(function(data){
        if (data.success === true){
            window.location="confEnvoieMail.html";
        }
        else {
            $('#msgErreur').html("Erreur lors de l'inscription.");
        }
    });
};