/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    recupererInfoEmploye();
    obtenirListeEmploye();
    obtenirListeMedium();
});

function obtenirListeEmploye() {
    $.ajax({
        url:'./ActionServlet', 
        type:'POST',
        data: {
            action:'ObtenirListeEmploye'
        },
        dataType:'json'
    })
    .done(function(data){
        
    });
};

function obtenirListeMedium() {
    $.ajax({
        url:'./ActionServlet', 
        type:'POST',
        data: {
            action:'ObtenirListeMedium'
        },
        dataType:'json'
    })
    .done(function(data){
        
    });
};

// Pour la navBar
function remplirChampEmploye(data) {
    $('.possibilite').prepend(data.nom);
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
        alert('On recupere les infos de lemploye');
        remplirChampEmploye(data);
    });
};

function afficherGraphiqueHisto1(dataMed) {
    var labels = "[";
    var data = "[";
    for (i=0; i<data.length(); i++){
        labels = labels + "'" + dataMed[i].med + "'";
        data = data + dataMed[i].nbVoy;
    };
    labels = labels + ']';
    data = data + ']';
    var lineChartData = {
        labels, data
    };
    
    buildBarChart('resNbVoyMed', lineChartData);
};

function buildBarChart(container, graphData) {

    Highcharts.chart(container, {

        chart: {
            type: 'column'
        },
        title: {
            text: 'Total de voyance demandées par médium'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: graphData.labels
        },
        yAxis: {
            title: {
                text: 'Nombre total de voyance demandé'
            }
        },
        legend: {
            enabled: false
        },
        credits: {
            enabled: false
        },
        series: [{name: 'Données', data: graphData.data}]
    });
}