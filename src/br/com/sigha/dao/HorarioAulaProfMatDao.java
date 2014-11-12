/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.dao;

import br.com.sigha.beans.HorarioAulaProfMatBeans;
import br.com.sigha.util.LogsTxt;
import br.com.sigha.conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MarcosVinicius
 */
public class HorarioAulaProfMatDao {

    public HorarioAulaProfMatDao() {
    }

    public List<HorarioAulaProfMatBeans> RetornaHorarioProfMat(String anoletivo, Date datageracao) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select h.id,s.nome curso,h.periodo,H.inicio,h.termino,"
                    + "coalesce(p.nome,'') professorsegunda,coalesce(m.nome,'') materiasegunda,"
                    + "coalesce(pt.nome,'') professorterca,coalesce(mt.nome,'') materiaterca,"
                    + "coalesce(pq.nome,'') professorquarta,coalesce(mq.nome,'') materiaquarta,"
                    + "coalesce(pqu.nome,'') professorquinta,coalesce(mqu.nome,'') materiaquinta,"
                    + "coalesce(ps.nome,'') professorsexta,coalesce(ms.nome,'') materiasexta,"
                    + "coalesce(psb.nome,'') professorsabado,coalesce(msb.nome,'') materiasabado,"
                    + "coalesce(pd.nome,'') professordomingo,coalesce(md.nome,'') materiadomingo,"
                    + "h.anoletivo,h.datageracao "
                    + "from horarioaula h "
                    + "left join professormateria pm on (h.segunda=pm.id) "
                    + "left join professormateria pmt on(h.terca=pmt.id) "
                    + "left join professormateria pmq on(h.quarta=pmq.id) "
                    + "left join professormateria pmqu on(h.quinta=pmqu.id) "
                    + "left join professormateria pms on(h.sexta=pms.id) "
                    + "left join professormateria pmsb on(h.sabado=pmsb.id) "
                    + "left join professormateria pmd on(h.domingo=pmd.id) "
                    + "left join professor p on(p.id=pm.idprofessor) "
                    + "left join materia m on(m.id=pm.idmateria) "
                    + "left join professor pt on(pt.id=pmt.idprofessor) "
                    + "left join materia mt on(mt.id=pmt.idmateria) "
                    + "left join professor pq on(pq.id=pmq.idprofessor) "
                    + "left join materia mq on(mq.id=pmq.idmateria) "
                    + "left join professor pqu on(pqu.id=pmqu.idprofessor) "
                    + "left join materia mqu on(mqu.id=pmqu.idmateria) "
                    + "left join professor ps on(ps.id=pms.idprofessor) "
                    + "left join materia ms on(ms.id=pms.idmateria) "
                    + "left join professor psb on(psb.id=pmsb.idprofessor) "
                    + "left join materia msb on(msb.id=pmsb.idmateria) "
                    + "left join professor pd on(pd.id=pmd.idprofessor) "
                    + "left join materia md on(md.id=pmd.idmateria) "
                    +"left join curso s on(h.idcurso=s.id) "
                    + "where h.anoletivo='"+anoletivo+"' and h.datageracao='"+new SimpleDateFormat("yyyy.MM.dd").format(datageracao)+"'";
            PreparedStatement pst = conexao.prepareStatement(sql);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            //pst.setString(1, anoletivo);
            //pst.setString(2, new SimpleDateFormat("yyyy.MM.dd").format(datageracao));
            ResultSet rs = pst.executeQuery(sql);
            List<HorarioAulaProfMatBeans> hapmb = new ArrayList<>();
            while (rs.next()) {
                HorarioAulaProfMatBeans hapm = new HorarioAulaProfMatBeans();
                hapm.setId(rs.getInt("id"));
                hapm.setCurso(rs.getString("curso"));
                hapm.setPeriodo(rs.getInt("periodo"));
                hapm.setInicio(rs.getString("inicio"));
                hapm.setTermino(rs.getString("termino"));
                hapm.setProfessorsegunda(rs.getString("professorsegunda"));
                hapm.setMateriasegunda(rs.getString("materiasegunda"));
                hapm.setProfessorterca(rs.getString("professorterca"));
                hapm.setMateriaterca(rs.getString("materiaterca"));
                hapm.setProfessorquarta(rs.getString("professorquarta"));
                hapm.setMateriaquarta(rs.getString("materiaquarta"));
                hapm.setProfessorquinta(rs.getString("professorquinta"));
                hapm.setMateriaquinta(rs.getString("materiaquinta"));
                hapm.setProfessorsexta(rs.getString("professorsexta"));
                hapm.setMateriasexta(rs.getString("materiasexta"));
                hapm.setProfessorsabado(rs.getString("professorsabado"));
                hapm.setMateriasabado(rs.getString("materiasabado"));
                hapm.setProfessordomingo(rs.getString("professordomingo"));
                hapm.setMateriadomingo(rs.getString("materiadomingo"));
                hapm.setAnoletivo(rs.getString("anoletivo"));
                hapm.setDatageracao(rs.getDate("datageracao"));
                hapmb.add(hapm);
            }
            rs.close();
            pst.close();
            return hapmb;
        }

    }
}
