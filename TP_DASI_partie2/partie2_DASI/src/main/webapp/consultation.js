/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    recupererInfoClientPourConsultation();
    
    // ajout d'un "handler" sur le clic du bouton de Terminer la prestatoin
    $('#terminer').on('click', function () { 
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Terminer consultation!"');
        terminerConsultation();
    });
});

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
        //var t="true";
        if (data){
            alert("L'onglet va se fermer");
            window.close();        
        }else{
            alert("error " + data);
            alert("error: StopPrest");
        }
        // On retourne sur la demandeDeVoyance plutot non ?
        // window.location = "consultation.html";
        window.location = "demandeDeVoyance.html";
        
    });
}

function remplirChampClient(data) {
    $('#infoClient').html(data.prenom + ' ' + data.nom + ' #' + data.id);
}

