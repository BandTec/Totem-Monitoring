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
    var admin = req.body.checkbox;
    var fk = req.body.fk;

    if (nome == undefined || email == undefined || cpf == undefined || senha == undefined || fk == undefined || admin == undefined) {
      throw new Error(`Algo de errado não está certo: ${nome} / ${email} / ${cpf} / ${senha} / ${fk} / ${admin}`);
    }
    else {
      return banco.sql.query(`insert into tb_user (nome, email_user, cpf_user, senha_user, adm, fk_aeroporto) values ('${nome}', '${email}', '${cpf}', '${senha}', ${admin}, ${fk} )`);
    }
  }).then(consulta => {

    res.send(true);

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

    var fk_aero = req.body.aeroporto;

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
    var id_usuario = req.body.user;
    var status = req.body.status;
    console.log(JSON.stringify(req.body));

    if (id_usuario == undefined) {
      throw new Error(`Algo de errado não está certo: ${id_usuario}`);
    } else {
      return banco.sql.query(`update tb_user set ativo=${status} where id_user = ${id_usuario}`)
    }
  }).then(consulta => {

    console.log('Update feito');
    res.send(true);

  }).catch(err => {

    var erro = `Erro na atualização: ${err}`;
    console.error(erro);
    res.status(500).send(erro);

  }).finally(() => {
    banco.sql.close();
  });
});

router.post('/deletar_user', function (req, res, next) {

  banco.conectar().then(() => {
    var id_usuario = req.body.user;
    console.log(JSON.stringify(req.body));

    if (id_usuario == undefined) {
      throw new Error(`Algo de errado não está certo: ${id_usuario}`);
    } else {
      return banco.sql.query(`delete from tb_user where id_user = ${id_usuario}`)
    }
  }).then(consulta => {

    console.log('Conta deletada');
    res.send(true);

  }).catch(err => {

    var erro = `Erro no update: ${err}`;
    console.log(erro);
    res.status(500).send(erro);

  }).finally(() => {
    banco.sql.close();
  });
});

router.post('/busca_totens', function (req, res, next) {
  console.log('Entrou para buscar os totens');

  banco.conectar().then(() => {
    var id_totem = req.body.aeroporto;
    console.log(JSON.stringify(req.body));

    if (id_totem == undefined) {
      throw new Error(`Algo de errado não está certo: ${id_totem}`);
    } else {
      return banco.sql.query(`select * from tb_totem where fk_aeroporto2 = ${id_totem}`)
    }

  }).then(consulta => {

    if (consulta.recordset.length >= 1) {
      res.send(consulta.recordset);
    } else {
      res.sendStatus(404);
    }

  }).catch(err => {

    var erro = `Erro no update: ${err}`;
    console.log(erro);
    res.status(500).send(erro);

  }).finally(() => {
    banco.sql.close();
  });
});


module.exports = router;
