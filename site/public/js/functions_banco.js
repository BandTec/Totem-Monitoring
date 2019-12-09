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
  var admin = $('#admin').is(':checked') ? 1 : 0;

  var formulario = new FormData(formulario_cadastro);
  formulario.append('checkbox' , admin);

  var params = new URLSearchParams(formulario);

  fetch('../../usuarios/cadastro_usuario', {
    method: "POST",
    body: params
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
  var params = new URLSearchParams({ 'aeroporto': sessionStorage.aeroporto });
  body_table.innerHTML = "";

  fetch('../../usuarios/users', {
    method: "POST",
    body: params
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
                <span class="label-success btn-xs" style="color: white"> ${status} </span>
              </td>
              <td>
                <button type="button" onclick="deletarUsuario(${resposta[i].id_user})" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Deletar </button>
                <button type="button" onclick="alterarStatus(${resposta[i].id_user}, 0)" class="btn btn-xs btn-xs" style="background-color: orange; color: white"><i
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
                <span class="label-danger btn-xs" style="color: white"> ${status} </span>
              </td>
              <td>
                <button type="button" onclick="deletarUsuario(${resposta[i].id_user})" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Deletar </button>
                <button type="button" onclick="alterarStatus(${resposta[i].id_user}, 1)" class="btn btn-info btn-xs" ><i class="fa fa-male"></i> Ativar </button>
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

function alterarStatus(id_usuario, status) {
  var params = new URLSearchParams( { 'user' : id_usuario, 'status': status });
  $('.btn-xs').prop('disabled', true)

  fetch('../../usuarios/inativar_user', {
    method: "POST",
    body: params
  }).then(function (response) {
    buscarUsuarios();
  });

  return false;
}

function deletarUsuario(id_usuario) {

  swal({
    title: "Você tem certeza?",
    text: "Quando um usuário é deletado, não é possivel recuperá-lo.",
    icon: "warning",
    buttons: true,
    dangerMode: true,
  })
  .then((willDelete) => {
    if (willDelete) {
      var params = new URLSearchParams( { 'user' : id_usuario });
      $('.btn-xs').prop('disabled', true)

      fetch('../../usuarios/deletar_user', {
        method: "POST",
        body: params
      }).then(function (response) {
        buscarUsuarios();
        swal("Pronto! O usuário foi deletado !", {
          icon: "success",
        });
      });
    } else {
      swal("A conta não foi deletada!");
    }
  });
}

function buscarTotens() {
  console.log("Buscando totens");

  var params = new URLSearchParams({ 'aeroporto': sessionStorage.aeroporto });

  fetch('../../usuarios/busca_totens', {
    method: "POST",
    body: params
  }).then(function (response) {
    if (response.ok) {
      response.json().then(function (resposta) {
        for (let i = 0; i < resposta.length; i++) {
          combo_totens.innerHTML += `<option value="${resposta[i].id_totem}">Totem ${i+1}</option>`;
        }
        div_totens.style.display = 'block';
        atualizarGrafico();
      });
    } else {
      console.log('Erro na busca de totens');
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
