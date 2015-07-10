package br.com.msystem.biblioteca.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import br.com.msystem.biblioteca.dao.EmprestarMaterialBibliograficoDao;
import br.com.msystem.biblioteca.exception.SystemException;
import br.com.msystem.biblioteca.vo.EmprestarMaterialBibliografico;
import br.com.msystem.biblioteca.vo.ReservarMaterialBibliografico;

@Controller
public class EmprestarMaterialBibliograficoService extends GenericService<EmprestarMaterialBibliografico> {

  @Autowired
  MaterialBibliograficoService materialService;
  
  @Autowired
  ReservarMaterialBibliograficoService reservarService;

	@Autowired
	public EmprestarMaterialBibliograficoService(EmprestarMaterialBibliograficoDao daoP) {
		super.dao = daoP;
	}

	@Override
	public int insertOrUpdate(final EmprestarMaterialBibliografico registro) throws SystemException {
		try {
			if (registro.getIdEmprestarMaterialBibliografico() == 0) {
				return dao.insert(registro);
			} else {
				return dao.update(registro);
			}
		} catch (DataIntegrityViolationException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}
	
	public int emprestar(ReservarMaterialBibliografico reserva) throws SystemException {
	  
    Calendar cal = Calendar.getInstance();
    cal.setTime(reserva.getDataInicioReserva());
    cal.add(Calendar.DAY_OF_MONTH, reserva.getPeriodoReserva());

    EmprestarMaterialBibliografico registro = new EmprestarMaterialBibliografico();
    registro.setIdEmprestarMaterialBibliografico(0);
    registro.setMaterialBibliografico(reserva.getMaterialBibliografico());
    registro.setUsuario(reserva.getUsuario());
    registro.setDataEmprestimo(reserva.getDataInicioReserva());
    registro.setDataDevolucaoPrevista(cal.getTime());

	  Map<String, String> filtro = new HashMap<String, String>();
	  filtro.put("id_material_bibliografico", registro.getMaterialBibliografico().getIdMaterialBibliografico().toString());
    List<EmprestarMaterialBibliografico> listaEmprestimo = this.listar(filtro, false);
    
    for (EmprestarMaterialBibliografico emprestimo: listaEmprestimo) {
      if (emprestimo.getDataDevolucaoEfetiva() == null) {
        throw new SystemException("error.emprestarMaterialBibliografico.indisponivel", new Throwable());
      }
    }
	  int result = dao.insert(registro);
	  
	  reserva.setStatusReservaBoolean(false);
	  reservarService.insertOrUpdate(reserva);

	  return result;
	}
	
	public List<EmprestarMaterialBibliografico> listarEmprestimoPendente() {
	  return ((EmprestarMaterialBibliograficoDao)dao).listarEmprestimoPendente();
	}
}
