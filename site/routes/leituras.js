var express = require('express');
var router = express.Router();
var banco = require('../app-banco');
// Essas três linhas possuem as "importações" do nosso arquivo


//aqui cmeça o endpoint de nome "/ultimas"
router.post('/ultimas', function (req, res, next) { //todas essas conexões estão no arquivo app-banco.js

  banco.conectar().then(() => {
    var id_totem = req.body.id_totem;
    var limite_linhas = 6;
    console.log("testando : ", id_totem);

    return banco.sql.query(`select top ${limite_linhas}
                                        dd_cpu,
                                        dd_memoria,
                                        dd_disco,
                                        qtd_processos,
                                        FORMAT(dd_tempo, 'HH:mm:ss') as hora,
                                        fk_totem
                                        from tb_dados
                                        where fk_totem = ${id_totem}
                                        order by id_dados desc`); //a query que vai "puxar" nossas informações
  }).then(consulta => {

    res.send(consulta.recordset); //o res.send vai enviar, para quem chamar esse endpoint, a nossa recordset.

  }).catch(err => {

    var erro = `Erro na leitura dos últimos registros: ${err}`;
    console.error(erro);
    res.status(500).send(erro);

  }).finally(() => {
    banco.sql.close();
  });
});

module.exports = router;
