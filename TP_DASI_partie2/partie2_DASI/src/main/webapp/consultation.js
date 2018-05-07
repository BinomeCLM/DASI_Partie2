/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    recupererInfoClientPourEmp();
    
    // ajout d'un "handler" sur le clic du bouton de Confirmer Inscription
    $('#terminer').on('click', function () {
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Accéder au service"');
        terminerConsultation();
    });
});

function recupererInfoClientPourEmp() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoClientPourEmp',
            //employe: '4'

           
        },
        dataType:'json'
    })
    .done(function(data){
        alert(data);
        remplirChampClient(data);
    });
};

function remplirChampClient(data) {
    $('#infoClient').html(data.prenom + ' ' + data.nom + ' #' + data.id);
}

