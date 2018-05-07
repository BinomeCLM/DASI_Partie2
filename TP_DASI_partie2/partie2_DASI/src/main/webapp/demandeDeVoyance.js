/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    var url = new URL(window.location.href);
    var idEmploye=url.searchParams.get("idEmploye");
    recupererInfoEmploye(idEmploye);
    
    
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
        openInNewTab("consultation.html");
//        window.location = "consultation.html";
        
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


function recupererInfoEmploye(idEmploye) {
    
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoEmp',
            employe: idEmploye, 
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
    var today = new Date();
    var yearr = Number(data.dateDeNaissance.substr(6, 10)); 
    var age = today.getFullYear() - yearr;
    alert(yearr);
//    alert(today.getFullYear());
 //   alert(age);
    $('#infoClient').html(data.civilite + '. ' + data.prenom + ' ' + data.nom + ' | '
            + age + 'ans | #' + data.id);
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