var express = require('express');
var router = express.Router();
var banco = require('../app-banco');
// Essas três linhas possuem as "importações" do nosso arquivo


//aqui cmeça o endpoint de nome "/ultimas"
router.get('/ultimas', function (req, res, next) {
  console.log(banco.conexao); //todas essas conexões estão no arquivo app-banco.js
  banco.conectar().then(() => {
    var limite_linhas = 8;
    return banco.sql.query(`select top ${limite_linhas} 
                                        dd_cpu,
                                        dd_memoria
                                        dd_disco,
                                        qtd_processos,
                                        FORMAT(dd_tempo, 'HH:mm:ss') as hora,
                                        fk_totem
                                        from tb_dados order by id_dados desc`); //a query que vai "puxar" nossas informações
  }).then(consulta => {

    console.log(`Resultado da consulta: ${JSON.stringify(consulta.recordset)}`); ///o recordset vai guardar nossos resultados
    res.send(consulta.recordset); //o res.send vai enviar, para quem chamar esse endpoint, a nossa recordset.

  }).catch(err => {

    var erro = `Erro na leitura dos últimos registros: ${err}`;
    console.error(erro);
    res.status(500).send(erro);

  }).finally(() => {
    banco.sql.close();
  });
});
