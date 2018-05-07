/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    recupererInfoEmploye();
    
    
    //desactiverLien();
    $('#demarrerVoyance').on('click', function () {
        demarrerVoyance();
        
    });
});


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
        alert(data);
        window.location = "consultation.html";
        
    });
}
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

function recupererInfoMedium() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoMedium',
            //employe: '4'
        },
        dataType:'json'
    })
    .done(function(data){
        alert(data);
        remplirChampMedium(data);
    });
};


function recupererInfoEmploye() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoEmp',
            employe: '4',
        },
        dataType:'json'
    })
    .done(function(data){
        alert(data);
        remplirChampEmploye(data);
        recupererInfoClientPourEmp();
        recupererInfoMedium();
        
    });
};

function remplirChampClient(data) {
    $('#infoClient').html(data.civilite + '. ' + data.prenom + ' ' + data.nom + ' | '
            + data.age + ' | #' + data.id);
}

function remplirChampMedium(data) {
    $('#infoMedium').html('Médium demandé: ' + data.nom + ' | '
            + data.talent + ' | ');
    /*if (data.metier === 'Tarologue'){
        $('#infoMedium').append(data.cartes);
    } 
    else if (data.metier === 'Astrologue') {
        $('#infoMedium').append('Ecole : ' + data.ecole + ' promotion ' + data.promotion);
    }
    else {
        $('#infoMedium').append(data.support);
    } */
}

function remplirChampEmploye(data) {
    $('#infoEmploye').html(data.nom);
}