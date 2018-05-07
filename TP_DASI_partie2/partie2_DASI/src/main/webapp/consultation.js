/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    recupererInfoClientPourConsultation();
    
    // ajout d'un "handler" sur le clic du bouton de Confirmer Inscription
    $('#terminer').on('click', function () {
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Terminer consultation"');
        terminerConsultation();
    });
});

function recupererInfoClientPourConsultation() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoClientPourConsultation', 
            //employe: '4'

           
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
  //      alert(data);
//        window.close();
//        window.location = "consultation.html";
        
    });
}

function remplirChampClient(data) {
    $('#infoClient').html(data.prenom + ' ' + data.nom + ' #' + data.id);
}

