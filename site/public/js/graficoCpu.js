var ctx = myChart.getContext('2d');
var ctx2 = disco.getContext('2d');
var ctx3 = processos.getContext('2d');
var ctx4 = memoria.getContext('2d');


window.onload = function(){
    atualizarGrafico();
};

// GRÁFICO DE CPU
var teste = new Chart(ctx, {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            data: [],
            backgroundColor: "#af2828",
            borderColor: "#af2828",
            borderWidth: 3,
            pointStyle: 'circle',
            pointRadius: 5,
            pointHoverRadius: 7,
            lineTension: 0,
            fill: false,
            label: "CPU"
        },{
            data: [],
            backgroundColor: "#000000",
            borderColor: "#000000",
            borderWidth: 3,
            pointStyle: 'circle',
            pointRadius: 5,
            pointHoverRadius: 7,
            lineTension: 0,
            fill: false,
            label: "CPU"
        }
    ]
    },
    
    options: {
        responsive: true,
        hoverMode: 'index',
        stacked: false,
        scales: {

            yAxes: [{
                ticks:{
                    min: 0,
                    max: 100,
                    maxTicksLimit: 10
                }
            }]
            
        }
}
});




//funções de atualizar o gráfico
function atualizarGrafico(){
    console.log("ENTROU PRA ATUALIZAR");
	//LEMBRA DO ENDÓINT? ENTÃO, A GENTE CHAMA ELE AQUI-> 
    fetch('../../leituras/ultimas', {
        cache: 'no-store'   
    }).then(function (response){
        // console.log(response);
        if(response.ok){
            console.log('Conexão ta Funfando');
            response.json().then(function (resposta){ 
                resposta.reverse();
                for(i=0; i<resposta.length;i++){
                    console.log(resposta);
                    var registro = resposta[i];
                    //ifizinho para nao deixar a temperaturazinha ultrapassar 6 registros
                    if(teste.data.datasets[0].data.length >= 6){
                        //horas
                        //CPU
                        teste.data.labels.shift();
                        teste.data.datasets.forEach((dataset) => {
                            dataset.data.shift();
                        });  
                        teste.data.labels.push(registro.hora);
                        teste.data.datasets.forEach((dataset) => {
                        dataset.data.push(registro.dd_cpu);
                    });  
                    teste.update();
                        }
                    else{
                        teste.data.labels.push(registro.hora);
                        teste.data.datasets[0].data.push(registro.dd_cpu);
                    }

                    if(teste2.data.datasets[0].data.length >= 6){
                        //horas
                        //DISCO
                        teste2.data.labels.shift();
                        teste2.data.datasets.forEach((dataset) => {
                            dataset.data.shift();
                        });  
                        teste2.data.labels.push(registro.hora);
                        teste2.data.datasets.forEach((dataset) => {
                        dataset.data.push(registro.dd_disco);
                    });  
                    teste2.update();
                        }
                    else{
                        teste2.data.labels.push(registro.hora);
                        teste2.data.datasets[0].data.push(registro.dd_disco);
                    }

                    if(!teste3){
                        teste3 = novoTeste3();
                    }

                    if(teste3.data.datasets[0].data.length >= 6){
                        //horas
                        //DISCO
                        teste3.data.labels.shift();
                        teste3.data.datasets.forEach((dataset) => {
                            dataset.data.shift();
                        });  
                        teste3.data.labels.push(registro.hora);
                        teste3.data.datasets.forEach((dataset) => {
                            dataset.data.push(registro.qtd_processos);
                        });  
                        teste3.update();
                    } else{
                        teste3.data.labels.push(registro.hora);
                        teste3.data.datasets[0].data.push(registro.qtd_processos);
                    }


                    if(teste4.data.datasets[0].data.length >= 6){
                        //horas
                        //MEMÓRIA
                            teste4.data.labels.shift();
                            teste4.data.datasets.forEach((dataset) => {
                                dataset.data.shift();
                            });  
                            teste4.data.labels.push(registro.hora);
                            teste4.data.datasets.forEach((dataset) => {
                            dataset.data.push(registro.dd_memoria);
                        });  
                        teste4.update();
                    }
                    else{
                        teste4.data.labels.push(registro.hora);
                        teste4.data.datasets[0].data.push(registro.dd_memoria);
                    }
                    
                    //dar update nas tabelas
                }
            });
        }
        else{
            console.log('Conexão NÃO TA FUNFANDO');
        }
        
       

    }).catch(function (error){
        console.error(`O erro é: ${error.message}` );
    });

    setTimeout('atualizarGrafico()',6000);
}

var teste2 = new Chart(ctx2, {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            data: [],
            backgroundColor: "#af2828",
            borderColor: "#af2828",
            borderWidth: 3,
            pointStyle: 'circle',
            pointRadius: 5,
            pointHoverRadius: 7,
            lineTension: 0,
            fill: false,
            label: "DISCO"
        }]
    },
    
    options: {
        responsive: true,
        hoverMode: 'index',
        stacked: false,
        scales: {

            yAxes: [{
                ticks:{
                    min: 0,
                    max: 100,
                    maxTicksLimit: 10
                }
            }]
            
        }
}
});

var teste3 = new Chart(ctx3, {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            data: [],
            backgroundColor: "#af2828",
            borderColor: "#af2828",
            borderWidth: 3,
            pointStyle: 'circle',
            pointRadius: 5,
            pointHoverRadius: 7,
            lineTension: 0,
            fill: false,
            label: "PROCESSOS"
        }]
    },
    
    options: {
        responsive: true,
        hoverMode: 'index',
        stacked: false,
        scales: {

            yAxes: [{
                ticks:{
                    min: 0,
                    max: 1000,
                    maxTicksLimit: 10
                }
            }]
            
        }
}
});




var teste4 = new Chart(ctx4, {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            data: [],
            backgroundColor: "#af2828",
            borderColor: "#af2828",
            borderWidth: 3,
            pointStyle: 'circle',
            pointRadius: 5,
            pointHoverRadius: 7,
            lineTension: 0,
            fill: false,
            label: "MEMÓRIA"
        }]
    },
    
    options: {
        responsive: true,
        hoverMode: 'index',
        stacked: false,
        scales: {

            yAxes: [{
                ticks:{
                    min: 0,
                    max: 100,
                    maxTicksLimit: 10
                }
            }]
            
        }
}
});