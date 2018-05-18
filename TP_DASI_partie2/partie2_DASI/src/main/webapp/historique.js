/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

$(document).ready(function () {
    getInfoClient();
    recupererInfoEmploye();
    
    $('#deconnexion').on('click', function () {
        seDeconnecter();
    });
}); 

function seDeconnecter(){
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'Deconnecter' // Est-ce qu'on en fait une pour l'employe
            // et une pour le cient ou alors separement (pour l'instant je fais les deux en meme 
            // temps --> voir ActionServlet
        },
        dataType:'json'
    })
    .done(function(){
        alert('deconnexion reussi');
        // On redirige vers la page de connexion
        window.location="connexion.html";
    });
}

function getInfoClient(){ 
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoClientPourEmp'
        },
        dataType:'json'
    })
    .done(function(data){
        alert('remplirChampClient');
        remplirChampClient(data);
        // Pourquoi le fait-on a ce moment la (récupérer la liste des prestations ?)
        // C'est l'historique du client ou de l'employé --> A vérifier
        recupererListePrestations();
    });
}

function recupererListePrestations() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererListePrestations'
        },
        dataType:'json'
    })
    .done(function(data){
        alert('remplirTableauHistorique');
        remplirTableauHistorique(data.prestations);
    });
};

function remplirTableauHistorique(prestations){
        $('#date').after('<div>' + prestations[0].heureDebut + '</div>');
        $('#medium').after('<div>' + prestations[0].mediumStr + '</div>');
        $('#employe').after('<div>' + prestations[0].employeStr + '</div>');
        $('#duree').after('<div>' + prestations[0].dureeStr + '</div>');
        for (var i = 1; i < prestations.length; i++){
            ajouterPrestation(prestations[i]);
        }  
        $('.infoBox').children().css('margin', '10px');
}

// ATTENTION
// TODO : Il faudra modifier la mise en forme une fois le css implémentée
// Parcontre à quoi sert le compteur ici ?
// C'est juste un oubli du au copier-coller ou il a une utilité ?
function ajouterPrestation(prestation){
     $('#date').siblings().last().after('<div>' + prestation.heureDebut + '</div>');
     $('#medium').siblings().last().after('<div>' + prestation.mediumStr + '</div>');
     $('#employe').siblings().last().after('<div>' + prestation.employeStr + '</div>');
     $('#duree').siblings().last().after('<div>' + prestation.dureeStr + '</div>');
}

function remplirChampClient(client){
    $('#infoClient').html('Historique de '+client.prenom+' '+client.nom+' #'+client.id);
}

function recupererInfoEmploye( ) {
    alert("recupInfoEmp");
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoEmp'
        },
        dataType:'json'
    })
    .done(function(data){
        remplirChampEmploye(data);
    });
};

function remplirChampEmploye(data) {
    $('.possibilite').prepend(data.nom);
}
