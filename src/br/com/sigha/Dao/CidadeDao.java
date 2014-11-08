/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Dao;

import br.com.sigha.Beans.CidadeBeans;
import br.com.sigha.conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MarcosVinicius
 */
public class CidadeDao {

    public List<CidadeBeans> BuscaCidade(String distrito) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select CIDADE.ID,CIDADE.NOME CIDADE,CIDADE.distrito,ESTADO.NOME ESTADO,ESTADO.UF,PAIS.NOME PAIS FROM CIDADE \n"
                    + "LEFT JOIN ESTADO ON(CIDADE.ESTADO=ESTADO.id)\n"
                    + "LEFT JOIN PAIS ON (ESTADO.pais=PAIS.ID)\n"
                    + "WHERE CIDADE.nome LIKE '"+distrito+"%'\n"
                    + "ORDER BY CIDADE.NOME,DISTRITO";
            PreparedStatement pst= conexao.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            List<CidadeBeans> lcb=new ArrayList<>();
            while(rs.next()){
                CidadeBeans cb= new CidadeBeans();
                cb.setId(rs.getInt("id"));
                cb.setCidade(rs.getString("cidade"));
                cb.setDistrito(rs.getString("distrito"));
                cb.setEstado(rs.getString("estado"));
                cb.setUf(rs.getString("uf"));
                cb.setPais(rs.getString("pais"));
                lcb.add(cb);
            }
            rs.close();
            pst.close();
            return lcb;
        }        
    }
}
