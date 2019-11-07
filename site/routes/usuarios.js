var express = require('express');
var router = express.Router();
var banco = require('../app-banco');

router.post('/login', function (req, res, next) {

  banco.conectar().then(() => {
    console.log(`Chegou p/ login: ${JSON.stringify(req.body)}`);

    var cpf = req.body.cpf;
    var senha = req.body.senha;

    if (cpf == undefined || senha == undefined) {
      throw new Error(`Algo de errado não está certo: ${cpf} / ${senha}`);
    }
    else {
      return banco.sql.query(`select * from tb_user where cpf_user='${cpf}' and senha_user='${senha}'`);
    }
  }).then(consulta => {

    console.log(`Usuários encontrados: ${JSON.stringify(consulta.recordset)}`);

    if (consulta.recordset.length == 1) {
      res.send(consulta.recordset[0]);
    } else {
      res.sendStatus(404);
    }

  }).catch(err => {

    var erro = `Erro no login: ${err}`;
    console.error(erro);
    res.status(500).send(erro);

  }).finally(() => {
    banco.sql.close();
  });

});

router.post('/cadastro_usuario', function (req, res, next) {

  banco.conectar().then(() => {
    console.log(`Chegou p/ Cadastro: ${JSON.stringify(req.body)}`);

    var nome = req.body.nome;
    var email = req.body.email;
    var cpf = req.body.cpf;
    var senha = req.body.password;
    var fk = req.body.fk;

    if (nome == undefined || email == undefined || cpf == undefined || senha == undefined || fk == undefined) {
      throw new Error(`Algo de errado não está certo: ${nome} / ${email} / ${cpf} / ${senha} / ${fk}`);
    }
    else {
      return banco.sql.query(`insert into tb_user (nome, email_user, cpf_user, senha_user, adm, fk_aeroporto) values ('${nome}', '${email}', '${cpf}', '${senha}', 0, ${fk} )`);
    }
  }).then(consulta => {

    if (consulta.recordset.length == 1) {
      res.send(consulta.recordset[0]);
    } else {
      res.sendStatus(404);
    }

  }).catch(err => {

    var erro = `Erro no cadastro: ${err}`;
    console.error(erro);
    res.status(500).send(erro);

  }).finally(() => {
    banco.sql.close();
  });

});

router.post('/users', function (req, res, next) {

  banco.conectar().then(() => {
    console.log(`Chegou p/ busca: ${JSON.stringify(req.body)}`);

    var fk_aero = req.body.fk;

    if (fk_aero == undefined) {
      throw new Error(`O SessionStorage não está funcionando: ${fk_aero}`);
    } else {
      return banco.sql.query(`select * from tb_user where fk_aeroporto = ${fk_aero}`)
    }
  }).then(consulta => {

    console.log(`Usuários encontrados: ${JSON.stringify(consulta.recordset)}`);

    if (consulta.recordset.length >= 1) {
      res.send(consulta.recordset);
    } else {
      res.sendStatus(404);
    }

  }).catch(err => {

    var erro = `Erro na busca dos usuários: ${err}`;
    console.error(erro);
    res.status(500).send(erro);

  }).finally(() => {
    banco.sql.close();
  });

});

router.post('/inativar_user', function (req, res, next) {

  banco.conectar().then(() => {
    var id = req.body.user_id
    console.log(req.body)

    if (id == undefined) {
      throw new Error(`Algo de errado não está certo: ${id}`);
    } else {
      return banco.sql.query(`update tb_user set ativo=0 where id_user = "${id}"`)
    }
  }).catch(err => {

    var erro = `Erro na atualização: ${err}`;
    console.error(erro);
    res.status(500).send(erro);

  }).finally(() => {
    banco.sql.close();
  });

});

module.exports = router;
