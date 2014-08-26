/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Dao;

import br.com.sigha.Beans.AuxVerificaProfrHorAulaBeans;
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
public class AuxVerificaProfHorAulaDao {

   // private Connection conexao;
    public AuxVerificaProfHorAulaDao() throws SQLException {
        //   this.conexao = new ConexaoBanco().getConnect();
    }

    public List<AuxVerificaProfrHorAulaBeans> VerificaProfessorHorario(int idprofessor) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select h.id,h.idprofessor,h.idmateria from (select h.segunda,h.terca,h.quarta,h.quinta,h.sexta,h.sabado,h.domingo,s.id,s.idprofessor,s.idmateria from horarioaula h,professormateria s\n"
                    + "where  h.segunda=s.id or h.terca=s.id or h.quarta=s.id or h.quinta=s.id\n"
                    + "or h.sexta=s.id or h.sabado=s.id or h.domingo=s.id group by s.id) as h where h.idprofessor=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, idprofessor);
            ResultSet rs = pstm.executeQuery();
            List<AuxVerificaProfrHorAulaBeans> lauxver = new ArrayList<>();
            while (rs.next()) {
                AuxVerificaProfrHorAulaBeans auxver = new AuxVerificaProfrHorAulaBeans();
                auxver.setIdmateria(rs.getInt("idmateria"));
                auxver.setIdprofessor(rs.getInt("idprofessor"));
                lauxver.add(auxver);
            }
            rs.close();
            pstm.close();
            
            return lauxver;
        }
    }

   
}
