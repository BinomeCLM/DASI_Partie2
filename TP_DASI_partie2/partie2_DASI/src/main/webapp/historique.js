/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

$(document).ready(function () {
//    alert("A1");
//    console.log("B1");   
    getInfoClient();
    //desactiverLien();
}); 
function getInfoClient(){ 
//    alert("A2");
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