
var ctx = myChart.getContext('2d');

window.onload = function(){
    atualizarGrafico();
};

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
            lineTension: 10.0,
            fill: false,
            label: "CPU"
        }]
    },
    options: {
        responsive: true,
        hoverMode: 'index',
        stacked: false,
        scales: {
            
        }
}
});


//funções de atualizar o gráfico
function atualizarGrafico(){
    console.log("ENTROU PRA ATUALIZAR");
	//LEMBRA DO ENDÓINT? ENTÃO, A GENTE CHAMA ELE AQUI-> 
    fetch('../../leituras/ultimas', {cache: 'no-store'}).then(function (response){
        console.log(response);
        if(response.ok){
            console.log('Conexão ta Funfando');
            response.json().then(function (resposta){ 
                resposta.reverse();
                for(i=0; i<resposta.length;i++){
                    var registro = resposta[i];
                    //ifizinho para nao deixar a temperaturazinha ultrapassar 6 registros
                    if(teste.data.datasets[0].data.length >= 6){
                        //horas
                        teste.data.labels.shift();
                        teste.data.labels.push(registro.hora);
                        //temperatura e umidade
                        teste.data.datasets[0].data.shift();
                        teste.data.datasets[0].data.push(registro.dd_cpu);
                    }
                    else{
                        teste.data.labels.push(registro.hora);
                        teste.data.datasets[0].data.push(registro.dd_cpu);
                    }

                    
                    //dar update nas tabelas
                    teste.update();
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