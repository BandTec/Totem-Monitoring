package conexao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import monitoramento.Totem;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Massarusao
 */
public class TesteConexao {

    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        TesteConexao testeConec = new TesteConexao();
        System.out.println(testeConec.selecionarTudo("tb_totem"));
        testeConec.inserirDados();
    }

    public TesteConexao() {
        ConexaoVO conec = new ConexaoVO();
        conec.criarConexao();
        jdbcTemplate = new JdbcTemplate(conec.getDataSource());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List selecionarTudo(String tabela) {
        List<Map<String, Object>> lista = jdbcTemplate.queryForList("select * from " + tabela);
        return lista;
    }

    public void inserirDados() {
        Totem totem = new Totem();
        totem.capturarDados();

        jdbcTemplate.update("insert into tb_dados (dd_cpu, dd_memoria, dd_disco, "
                + "dd_tempo, fk_totem) values (?,?,?,?,?)", totem.getCpu(),
                totem.getMemoria(),
                totem.getDisco(),
                totem.getTempo(), 2);
    }
}
