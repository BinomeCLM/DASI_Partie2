/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    var url = new URL(window.location.href);
    var idEmploye = url.searchParams.get("idEmploye");
    
    recupererInfoEmploye();
    
    $('#demarrerVoyance').on('click', function () {
        demarrerVoyance();
    });
    $('#demanderPrediction').on('click', function () {
        genererPrediction();
    });
    $('#deconnexion').on('click', function () {
        seDeconnecter();
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

function demarrerVoyance() {
   $.ajax({
       url : './ActionServlet',
       type: 'POST',
       data : {
           action : 'StartPrestation'
       },
       dataType : 'json'
    })
    .done(function(data){
        if (data.id > 0){
            openInNewTab("consultation.html");
        }
        else {
            alert('Aucun client pour le moment. Impossible de démarrer une voyance.');
        }
    });
}

function openInNewTab(url) {
  var win = window.open(url, '_blank');
  win.focus();
}

function recupererInfoClientPourEmp() {
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
        remplirChampMedium(data);
    });
};


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
        remplirChampEmploye(data);
        if (data.busy == true) {
            recupererInfoClientPourEmp();
            recupererInfoMedium();
        } 
        else {
            $('#infoClient').html('Aucun client pour le moment');
            $('#consultHisto').attr('href', '#');
        }
    });
};

function remplirChampClient(data) {
    if (data.id > 0){
        var today = new Date();
        var year = Number(data.dateDeNaissance.substr(6, 10)); 
        var age = today.getFullYear() - year;
        $('#infoClient').html(data.civilite + '. ' + data.prenom + ' ' + data.nom + ' | '
                + age + 'ans | #' + data.id);
    }
    else {
        $('#infoClient').html('Problème sur le serveur.');
    }
}

function remplirChampMedium(data) {
    if (data.id>0){
        $('#infoMedium').html('Médium demandé: ' + data.nom + ' | '
            + data.talent + ' | ');
    }
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

function remplirChampEmploye(data) {
    $('.possibilite').prepend(data.nom);
}

function genererPrediction () {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'GenererPrediction',
            amour: $('#amour').val(),
            sante: $('#sante').val(),
            travail: $('#travail').val()
        },
        dataType:'json'
    })
    .done(function(data){
        if (data.success == true){
            openInNewTab("prediction.html"); 
        }
        else {
            alert('Aucun client pour le moment.');
        }
    });
}