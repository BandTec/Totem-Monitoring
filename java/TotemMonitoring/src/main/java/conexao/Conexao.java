package conexao;

import java.time.LocalDateTime;
import java.util.List;
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
        return jdbcTemplate.queryForList("select * from ?", tabela);
    }
    
    public void addAcesso() {
        Integer acessos = jdbcTemplate.queryForObject("select qtd_acessos from tb_totem where id_totem = 4", new Object[] {}, Integer.class);
        acessos++;
        jdbcTemplate.update("update tb_totem set qtd_acessos = ? where id_totem = 4", acessos);
        System.out.println("acessos: " + acessos.toString());
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

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
