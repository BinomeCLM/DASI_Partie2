/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    recupererInfoClientPourConsultation();
    recupererInfoEmploye();
    
    // ajout d'un "handler" sur le clic du bouton de Terminer la prestatoin
    $('#terminer').on('click', function () { 
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Terminer consultation!"');
        terminerConsultation();
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
        alert(data);
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
        dataType: 'text'
    })
    .done(function(data){
        alert(data);
        if (data == "true"){
            alert("L'onglet va se fermer");
            window.close();        
        }else{
            alert("error " + data);
            alert("error: StopPrest");
        }
        //window.location = "demandeDeVoyance.html";
        
    });
}

function remplirChampClient(data) {
    $('#infoClient').html(data.prenom + ' ' + data.nom + ' #' + data.id);
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