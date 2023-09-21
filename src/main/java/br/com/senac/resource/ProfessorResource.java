package br.com.senac.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.dto.ProfessorDTO;
import br.com.senac.entity.Professor;
import br.com.senac.service.ProfessorService;

@RestController
@RequestMapping("professores")
public class ProfessorResource {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping
	public ResponseEntity<ProfessorDTO> cadastrarAluno(@RequestBody ProfessorDTO professorDTO) {
		
		Professor professor = mapper.map(professorDTO,Professor.class);
		
		professor = professorService.salvar(professor);
		
		ProfessorDTO professorNovo = mapper.map(professor, ProfessorDTO.class);
		
		return ResponseEntity.ok().body(professorNovo);
		
	}
	
	
	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> buscarTodosAlunos() {
		List<Professor> listaProfessor = professorService.buscarTodosProfessor();
		
		List<ProfessorDTO> listaProfessorDTO = listaProfessor.stream().map(professor -> 
				mapper.map(professor, ProfessorDTO.class)).collect(Collectors.toList());
				
		
		return ResponseEntity.ok().body(listaProfessorDTO);
		
		
	}
	
	
	
	
}
