package conexao.banco;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Massarusao
 */
public class Conexao {

    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        Conexao testeConec = new Conexao();
        System.out.println(testeConec.selecionarTudo("tb_totem"));
    }

    public Conexao() {
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

    public void inserirDadosHW(Double cpu, Double memoria, Double disco) {
        System.out.println("ENTROU");
        String cpuHelp = String.format("%.2f", cpu);
        cpuHelp = cpuHelp.replace(",", ".");
        jdbcTemplate.update("insert into tb_dados (dd_cpu, dd_memoria, dd_disco, dd_tempo, status_totem, fk_totem) values (?,?,?,?,?,?)",
                Double.valueOf(cpuHelp),
                memoria,
                disco,
                LocalDateTime.now(),
                1,
                2);
    }
}
