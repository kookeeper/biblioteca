package br.com.msystem.biblioteca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.mapper.MaterialBibliograficoMapper;
import br.com.msystem.biblioteca.util.CacheUtil;
import br.com.msystem.biblioteca.vo.MaterialBibliografico;

@Controller
public class MaterialBibliograficoDao extends GenericDao<MaterialBibliografico> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2418047356173215959L;

	@Autowired
	public MaterialBibliograficoDao(MaterialBibliograficoMapper mapperP) {
		super.mapper = mapperP;
	}

	@Override
	public int update(MaterialBibliografico registro) {
		limparCache();
		logger.info("Atualizando material bibliografico.");
		return getSimpleJdbcTemplate().update(
				"update material_bibliografico set "
						+ "id_tipo_material_bibliografico = ?, "
						+ "id_local_material_bibliografico = ?, "
						+ "codigo_material_bibliografico = ?, " + "autor = ?, " + "titulo = ?, " + "edicao = ?, "
						+ "data_aquisicao = ?, " + "editora = ?, "
						+ "resenha = ? "
						+ "where id_material_bibliografico = ?",
				new Object[] {
						registro.getTipoMaterialBibliografico()
								.getIdTipoMaterialBibliografico(),
						registro.getLocalMaterialBibliografico()
								.getIdLocalMaterialBibliografico(),
						registro.getCodigoMaterialBibliografico(),
						registro.getAutor(), registro.getTitulo(),
						registro.getEdicao(), registro.getDataAquisicao(),
						registro.getEditora(), registro.getResenha(),
						registro.getIdMaterialBibliografico() });
	}

	@Override
	public int insert(MaterialBibliografico registro) {
		limparCache();
		logger.info("Inserindo tipo de material bibliografico.");

		CacheUtil.getCache("").removeAll();

		return getSimpleJdbcTemplate()
				.update("insert into material_bibliografico "
						+ "(id_tipo_material_bibliografico, id_local_material_bibliografico, codigo_material_bibliografico, autor, titulo, edicao, data_aquisicao, editora, resenha) "
						+ "values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)",
						new Object[] {
								registro.getTipoMaterialBibliografico()
										.getIdTipoMaterialBibliografico(),
								registro.getLocalMaterialBibliografico()
										.getIdLocalMaterialBibliografico(),
								registro.getCodigoMaterialBibliografico(),
								registro.getAutor(), registro.getTitulo(),
								registro.getEdicao(),
								registro.getDataAquisicao(),
								registro.getEditora(), registro.getResenha() });
	}

	public List<MaterialBibliografico> listarDisponivel() {
		logger.info("Listando material bibliografico disponivel para emprestimo da tabela.");

		String sql = "SELECT mb.*, "
				+ "(select count(*) from bloquear_material_bibliografico bmb where bmb.id_material_bibliografico = mb.id_material_bibliografico and bmb.status_bloqueio = 'ativo') bloqueado, "
				+ "(select count(*) from emprestar_material_bibliografico emb where emb.id_material_bibliografico = mb.id_material_bibliografico and emb.data_devolucao_efetiva is null) emprestado, "
				+ "(select count(*) from reservar_material_bibliografico rmb where rmb.id_material_bibliografico = mb.id_material_bibliografico and rmb.status_reserva = 'ativo') reservado "
				+ "FROM material_bibliografico mb "
				+ "having bloqueado = 0 and emprestado = 0 and reservado = 0 "
				+ "order by titulo";

		return (List<MaterialBibliografico>) getSimpleJdbcTemplate().query(sql,
				mapper);
	}
}
