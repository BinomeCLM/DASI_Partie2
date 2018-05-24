/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    recupererInfoClientPourConsultation();
    recupererInfoEmploye();
    
    $('#terminer').on('click', function () { 
        terminerConsultation();
    });
    
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

function recupererInfoClientPourConsultation() {
    $.ajax({
        url:'./ActionServlet', 
        type:'POST',
        data: {
            action:'RecupererInfoClientPourConsultation'
        },
        dataType:'json'
    })
    .done(function(data){
        remplirChampClient(data);
    });
};

function terminerConsultation() {
    $.ajax({
       url : './ActionServlet',
       type: 'POST',
       data : {
           action : 'StopPrestation' 
       },
        dataType: 'json'
    })
    .done(function(data){
        if (data.success === true){
            window.close();        
        }else{
            alert("error: Problème rencontré sur le serveur.");
        }
        
    });
}

function remplirChampClient(data) {
    $('#infoClient').html(data.prenom + ' ' + data.nom + ' #' + data.id);
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
        if (data.id>0){
            remplirChampEmploye(data);
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