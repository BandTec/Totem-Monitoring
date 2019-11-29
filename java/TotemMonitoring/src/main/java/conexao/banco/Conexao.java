package conexao.banco;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Massarusao
 */
public class Conexao {

    private JdbcTemplate jdbcTemplate;

    public Conexao() {
        ConexaoVO conec = new ConexaoVO();
        conec.criarConexao();
        jdbcTemplate = new JdbcTemplate(conec.getDataSource());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirDadosHW(Double cpu, Double memoria, Double disco, Integer qtdProcessos, Integer id_totem) {
        System.out.println("Inserindo dados no banco, HORA: "+ (LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
        String cpuHelp = String.format("%.2f", cpu);
        cpuHelp = cpuHelp.replace(",", ".");
        jdbcTemplate.update("insert into tb_dados (dd_cpu, dd_memoria, dd_disco, qtd_processos, dd_tempo, status_totem, fk_totem) values (?,?,?,?,?,?,?)",
                Double.valueOf(cpuHelp),
                memoria,
                disco,
                qtdProcessos,
                LocalDateTime.now(ZoneId.of("America/Buenos_Aires")),
                1,
                id_totem);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
