/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

$(document).ready(function () {
    
    recupererInfoEmploye();
    
    $('#deconnexion').on('click', function () {
        seDeconnecter();
    });
    
    $('#tabLien').on('click', function () {
        window.location = "tableauDeBord.html"
    });
    
    $('#accueil').on('click', function () {
        window.location = "tableauDeBord.html"
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
        remplirChampClient(data);
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
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoEmp'
        },
        dataType:'json'
    })
    .done(function(data){
        if (data.id > 0) {
            remplirChampEmploye(data);
            if (data.busy == 1){
                getInfoClient();
            }
            else {
                alert('Vous n\'avez pas de client actuellement.');
                window.location = "demandeDeVoyance.html";
            }
        }
        else {
            alert('Vous allez être redirigé vers la page de connexion. En effet, vous ne vous êtes pas identifié.');
            window.location = "connexion.html";
        }
    });
};

function remplirChampEmploye(data) {
    $('.possibilite').prepend(data.nom);
}
