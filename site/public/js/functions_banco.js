function verificarAutenticacao() {
  if (sessionStorage.usuario == undefined) {
    window.location.href = '/login.html';
    alert('Você deve estar autenticado');
  } else {
    if (sessionStorage.ativo == 'true') {
      window.location.href = '/dashs/production/index.html';
    }
    else {
      alert('Conta inativada, por favor contate seu superior')
    }
  }
}

function logar() {

  wait();

  var formulario = new URLSearchParams(new FormData(form_login));

  fetch('/usuarios/login', {
    method: "POST",
    body: formulario
  }).then(function (response) {
    if (response.ok) {
      response.json().then(function (resposta) {
        sessionStorage.usuario = resposta.email_user;
        sessionStorage.id_usuario = resposta.id_user;
        sessionStorage.admin = resposta.adm;
        sessionStorage.ativo = resposta.ativo;
        sessionStorage.aeroporto = resposta.fk_aeroporto;
        sessionStorage.nome = resposta.nome;

        verificarAutenticacao();
      });
    } else {
      console.log('Erro de login!');
      alert('Login ou senha inválido.');
      end_wait();
    }
  });

  return false;
}

function cadastrarUsuario() {

  wait();
  fk.value = sessionStorage.aeroporto;
  var formulario = new URLSearchParams(new FormData(formulario_cadastro));

  fetch('../../usuarios/cadastro_usuario', {
    method: "POST",
    body: formulario
  }).then(function (response) {
    if (response.ok) {
      response.json().then(function (resposta) {
        if (resposta == true) {
          window.location.href = 'index.html';
        } else {
          alert('Desculpe, Algo deu errado');
          end_wait();
        }
      });
    } else {
      console.log('Erro de cadastro!');
      end_wait();
    }
  });

  return false;
}

function buscarUsuarios() {

  wait();
  fk.value = sessionStorage.aeroporto;
  var formulario = new URLSearchParams(new FormData(formulario_buscar));
  body_table.innerHTML = "";

  fetch('../../usuarios/users', {
    method: "POST",
    body: formulario
  }).then(function (response) {
    if (response.ok) {
      response.json().then(function (resposta) {
        for (let i = 0; i < resposta.length; i++) {
          var status = '';

          if (resposta[i].ativo) {
            status = 'ATIVO';
            body_table.innerHTML += `<tr>
            <td>${i + 1}</td>
            <td>
              <a>${resposta[i].nome}</a>
              <br>
              </td>
              <td class="project_progress">
                <a>${resposta[i].cpf_user}</a>
              </td>
              <td>
                <span class="btn-success btn-xs"> ${status} </span>
              </td>
              <td>
                <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Deletar </a>
                <button type="button" onclick="inativarUsuario(${resposta[i].id_user})" class="btn btn-xs btn-xs" style="background-color: orange"><i
                class="fa fa-remove "></i> Desativar </button>
              </td>
           </tr>`;

          } else {
            status = 'INATIVO'

            body_table.innerHTML += `<tr>
            <td>${i + 1}</td>
            <td>
              <a>${resposta[i].nome}</a>
              <br>
              </td>
              <td class="project_progress">
                <a>${resposta[i].cpf_user}</a>
              </td>
              <td>
                <span class="btn-success btn-xs"> ${status} </span>
              </td>
              <td>
                <a href="#" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Deletar </a>
                <a href="#" class="btn btn-info btn-xs"><i class="fa fa-male"></i> Ativar </a>
              </td>
           </tr>`;
          }
        }
        sessionStorage.body = body_table.innerHTML;
        end_wait();
      });
    } else {
      console.log('Errossss!');
      end_wait();
    }
  });

  return false;
}

function wait() {
  sendMessageButton.disabled = true;
}

function end_wait() {
  sendMessageButton.disabled = false;
}

function inativarUsuario(id_usuario) {
  console.log(id_usuario)
  var user = new URLSearchParams(id_usuario);

  fetch('../../usuarios/inativar_user', {
    method: "POST",
    body: user
  }).then(function (response) {
    end_wait();
  });

  return false;
}
