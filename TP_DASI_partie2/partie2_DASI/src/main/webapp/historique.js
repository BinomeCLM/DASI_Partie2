/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    alert("A1");
    console.log("B1");
    recupererListePrestations();
    //desactiverLien();
});

function recupererListePrestations() {
    alert("A2");
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
    });
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererListePrestations'
        },
        dataType:'json'
    })
    .done(function(data){
        alert('ajouterListePrestations');
        remplirTableauHistorique(data.prestations);
    });
    
};

function remplirTableauHistorique(prestations){
        for (var i = 0; i < prestations.length; i++){
            ajouterPrestation(i, prestations[i]);
        }  
}

function ajouterPrestation(compteur,prestation){
     $('#tableauHistorique').append('<tr>'+
            '<td>'+ prestation.mediumStr +'</td>'+
            '<td>'+ prestation.mediumStr +'</td>'+
            '<td>'+ prestation.employeStr +'</td>'+
            '<td>'+ prestation.employeStr +'</td>'+
            '</tr>');
}

function remplirChampClient(client){
    $('#infoClient').html('Historique de '+client.prenom+' '+client.nom+' #'+client.id);
}