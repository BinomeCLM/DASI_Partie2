/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    recupererInfoClient();
    recupererInfoMedium();
    //desactiverLien();
});

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
        alert(data);
        remplirChampClient(data);
    });
};

function recupererInfoMedium() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoMedium'
        },
        dataType:'json'
    })
    .done(function(data){
        alert(data);
        remplirChampMedium(data);
    });
};

function remplirChampClient(data) {
    $('#infoClient').html(data.civilite + '. ' + data.prenom + ' ' + data.nom + ' | '
            + data.age + ' | #' + data.id);
}

function remplirChampMedium(data) {
    $('#infoMedium').html('Médium demandé: ' + data.nom + ' | '
            + data.talent + ' | ');
    if (data.metier === 'Tarologue'){
        $('#infoMedium').append(data.cartes);
    } 
    else if (data.metier === 'Astrologue') {
        $('#infoMedium').append('Ecole : ' + data.ecole + ' promotion ' + data.promotion);
    }
    else {
        $('#infoMedium').append(data.support);
    } 
}