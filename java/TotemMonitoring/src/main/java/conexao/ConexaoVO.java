package conexao;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConexaoVO {

    BasicDataSource dataSource = new BasicDataSource();

    public void criarConexao() {

        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://servidor01191050.database.windows.net:1433;database=Banco_Projeto;user=GF01191050@servidor01191050;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        dataSource.setUsername("GF01191050");
        dataSource.setPassword("#Gf47652050880");
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
    
}
