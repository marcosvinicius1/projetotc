package br.com.sigha.Dao;

import br.com.sigha.Beans.UnidadeBeans;
import br.com.sigha.conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadeDao {

    // private Connection conexao;
    public UnidadeDao() throws SQLException {
        //   conexao=new ConexaoBanco().getConnect();
    }

    public void cadastrarUnidade(UnidadeBeans unidade) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "insert into unidade(razao, fantasia, endereco, bairro, cep, cidade, numero, complemento, telefone, logo,cnpj) values(?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, unidade.getRazao());
            stmt.setString(2, unidade.getFantasia());
            stmt.setString(3, unidade.getEndereco());
            stmt.setString(4, unidade.getBairro());
            stmt.setString(5, unidade.getCep());
            stmt.setString(6, unidade.getCidade());
            stmt.setString(7, unidade.getNumero());
            stmt.setString(8, unidade.getComplemento());
            stmt.setString(9, unidade.getTelefone());
            stmt.setString(10, unidade.getLogo());
            stmt.setString(11, unidade.getCnpj());
            stmt.execute();
            stmt.close();

            conexao.close();
        }
    }

    public void alterarUnidade(UnidadeBeans unidade) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "update unidade set razao = ?, fantasia = ?, endereco = ?, bairro = ?, cep = ?, cidade = ?, numero = ?, complemento = ?, telefone = ?, logo = ?,cnpj=? where id = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, unidade.getRazao());
            stmt.setString(2, unidade.getFantasia());
            stmt.setString(3, unidade.getEndereco());
            stmt.setString(4, unidade.getBairro());
            stmt.setString(5, unidade.getCep());
            stmt.setString(6, unidade.getCidade());
            stmt.setString(7, unidade.getNumero());
            stmt.setString(8, unidade.getComplemento());
            stmt.setString(9, unidade.getTelefone());
            stmt.setString(10, unidade.getLogo());
            stmt.setString(11, unidade.getCnpj());
            stmt.setInt(12, unidade.getId());

            stmt.execute();
            stmt.close();

            conexao.close();
        }
    }

    public void excluirUnidade(int id) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "delete from unidade where id = ?";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();

            conexao.close();
        }
    }

    public List<UnidadeBeans> consultarUnidade() throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            List<UnidadeBeans> lista = new ArrayList<>();
            String sql = "select * from unidade";

            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                UnidadeBeans a = new UnidadeBeans();
                a.setBairro(rs.getString("bairro"));
                a.setCep(rs.getString("cep"));
                a.setCidade(rs.getString("cidade"));
                a.setComplemento(rs.getString("complemento"));
                a.setEndereco(rs.getString("endereco"));
                a.setFantasia(rs.getString("fantasia"));
                a.setId(rs.getInt("id"));
                a.setLogo(rs.getString("logo"));
                a.setNumero(rs.getString("numero"));
                a.setRazao(rs.getString("razao"));
                a.setTelefone(rs.getString("telefone"));
                a.setCnpj(rs.getString("cnpj"));
                lista.add(a);
            }
            conexao.close();
            return lista;
        }
    }

    public UnidadeBeans consultarUnidade(int idunidade) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            UnidadeBeans unidade = new UnidadeBeans();
            String sql = "select * from unidade where id=?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idunidade);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                unidade.setBairro(rs.getString("bairro"));
                unidade.setCep(rs.getString("cep"));
                unidade.setCidade(rs.getString("cidade"));
                unidade.setComplemento(rs.getString("complemento"));
                unidade.setEndereco(rs.getString("endereco"));
                unidade.setFantasia(rs.getString("fantasia"));
                unidade.setId(rs.getInt("id"));
                unidade.setLogo(rs.getString("logo"));
                unidade.setNumero(rs.getString("numero"));
                unidade.setRazao(rs.getString("razao"));
                unidade.setTelefone(rs.getString("telefone"));
                unidade.setCnpj(rs.getString("cnpj"));                
            }
            stmt.close();
            rs.close();
            return unidade;
        }
    }
}
