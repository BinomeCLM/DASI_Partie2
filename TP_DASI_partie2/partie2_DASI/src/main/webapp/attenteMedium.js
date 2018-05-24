/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    recupererInfoClient();
    
    $('#deconnexion').on('click', function () {
        seDeconnecter();
    });
    
    $('#accueil').on('click', function () {
        window.location = "profil.html"
    });
}); 

function seDeconnecter(){
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'Deconnecter' 
        },
        dataType:'json'
    })
    .done(function(){
        window.location="connexion.html";
    });
}

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
        if (data.id >= 0){
            remplirChamp(data);
        }
        else {
            alert('Vous allez être redirigé vers la page de connexion. En effet, vous ne vous êtes pas identifié.');
            window.location = "connexion.html";
        }
    });
};

function remplirChamp(data) {
    $('.possibilite').prepend(data.prenom + " " + data.nom + " ");
}