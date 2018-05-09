/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

$(document).ready(function () {
    getInfoClient();
}); 

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
        for (var i = 0; i < prestations.length; i++){
            ajouterPrestation(i, prestations[i]);
        }  
}

// ATTENTION
// TODO : Il faudra modifier la mise en forme une fois le css implémentée
// Parcontre à quoi sert le compteur ici ?
// C'est juste un oubli du au copier-coller ou il a une utilité ?
function ajouterPrestation(compteur,prestation){
     $('#tableauHistorique').append('<tr>'+
            '<td>'+ prestation.heureDebut +'</td>'+
            '<td>'+ prestation.mediumStr +'</td>'+
            '<td>'+ prestation.employeStr +'</td>'+
            '<td>'+ prestation.dureeStr +'</td>'+
            '</tr>');
}

function remplirChampClient(client){
    $('#infoClient').html('Historique de '+client.prenom+' '+client.nom+' #'+client.id);
}