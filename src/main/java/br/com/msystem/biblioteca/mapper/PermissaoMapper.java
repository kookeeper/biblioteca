package br.com.msystem.biblioteca.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.msystem.biblioteca.vo.Permissao;

@Component
public class PermissaoMapper implements RowMapper<Permissao> {

	public Permissao mapRow(ResultSet rs, int rowNum) throws SQLException {
	  Permissao registro = new Permissao();
    registro.setIdPermissao(rs.getInt("id_permissao"));
    registro.setFuncionalidade(rs.getString("funcionalidade"));
    return registro;
	}
}
