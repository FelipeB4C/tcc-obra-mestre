package com.tcc.reforma.reforma.controller;

import com.tcc.reforma.reforma.domain.interessado.Interessado;
import com.tcc.reforma.reforma.domain.profissional.Profissional;
import com.tcc.reforma.reforma.domain.profissional.ProfissionalDetailDTO;
import com.tcc.reforma.reforma.domain.projeto.Projeto;
import com.tcc.reforma.reforma.domain.projeto.ProjetoCreateDTO;
import com.tcc.reforma.reforma.domain.projeto.ProjetoDetailDTO;
import com.tcc.reforma.reforma.domain.projeto.ProjetoUpdateDTO;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import com.tcc.reforma.reforma.repository.InteressadoRepository;
import com.tcc.reforma.reforma.repository.ProfissionalRepository;
import com.tcc.reforma.reforma.repository.ProjetoRepository;
import com.tcc.reforma.reforma.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/projeto")
@CrossOrigin("*")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private InteressadoRepository interessado;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("usuario/{id}/cadastrar")
    @Transactional
    public ResponseEntity<Void> cadastraProjeto(@RequestBody @Valid ProjetoCreateDTO request, @PathVariable Long id){
        var usuario = usuarioRepository.findById(id).get();
        var projeto = request.toModel(usuario);
        projetoRepository.save(projeto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(projeto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idProject}/inserir/{idProfessional}")
    @Transactional
    public ResponseEntity<Void> inserirProfissional(@PathVariable Long idProject, @PathVariable Long idProfessional){
        var profissional = profissionalRepository.findById(idProfessional).get();
        var projeto = projetoRepository.findById(idProject).get();
        projeto.setProfissional(profissional);
        projetoRepository.save(projeto);
        profissional.setProjetosTrabalhados(projeto);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("{idProject}/interessado/{idProfissional}")
    public ResponseEntity<Void> interessado(@PathVariable Long idProject, @PathVariable Long idProfissional) {
        var projeto = projetoRepository.findById(idProfissional).get();
        var profissional = profissionalRepository.findById(idProject).get();
        interessado.save(new Interessado(projeto, profissional));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listarUm/{id}")
    public ResponseEntity<ProjetoDetailDTO> listaUm(@PathVariable Long id){
        var projeto = projetoRepository.findById(id).get();
        var profissional = usuarioRepository.findByIdProfissional(projeto.getProfissional());
        var projetoDto = new ProjetoDetailDTO(projeto, profissional);
        projetoDto.setProfissionaisInteressados(listaInteressados(projeto));
        return ResponseEntity.ok().body(projetoDto);
    }

    @GetMapping("/listarTodosDisp")
    public ResponseEntity<List<ProjetoDetailDTO>> listaTodosDisponiveis(){
        var projeto = projetoRepository.projetosDisponiveis();
        var list = projeto.stream().map(ProjetoDetailDTO::new).toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ProjetoDetailDTO>> findAll(){
        var projeto = projetoRepository.findAll();
        List<ProjetoDetailDTO> list = new ArrayList<>();
        for (Projeto p: projeto ) {
            Usuario profissional = usuarioRepository.findByIdProfissional(p.getProfissional());
            list.add(new ProjetoDetailDTO(p, profissional));
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/listarProjProf/{id}")
    public ResponseEntity<List<ProjetoDetailDTO>> listaProjetosProfissionais(@PathVariable Long id){
        var profissional = profissionalRepository.findById(id).get();
        var listaProjetos = profissional.getProjetosTrabalhados();
        List<ProjetoDetailDTO> listDto = listaProjetos.stream()
                .map(ProjetoDetailDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/listarProjCli/{id}")
    public ResponseEntity<List<ProjetoDetailDTO>> listaProjetosCriados(@PathVariable Long id){
        var cliente = usuarioRepository.findById(id).get();
        var listaProjetos = cliente.getProjetosCriados();
        List<ProjetoDetailDTO> listDto = listaProjetos.stream()
                .map(ProjetoDetailDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizaProjeto(@RequestBody @Valid ProjetoUpdateDTO request, @PathVariable Long id){
        var projeto = projetoRepository.findById(id).get();
        request.updateData(projeto);
        projetoRepository.save(projeto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/concluido")
    public ResponseEntity<Void> projetoConcluido(@PathVariable Long id){
        var projeto = projetoRepository.findById(id).get();
        projeto.setConcluido(true);
        projetoRepository.save(projeto);
        return ResponseEntity.noContent().build();
    }


    public List<ProfissionalDetailDTO> listaInteressados(Projeto projeto){

        var interessados = interessado.findByIdProjeto(projeto);

        List<Usuario> listProfissionais = interessados
                .stream()
                .map(p -> usuarioRepository.findByIdProfissional(p.getId().getProfissional())).toList();

        List<ProfissionalDetailDTO> listDto = listProfissionais
                .stream()
                .map(ProfissionalDetailDTO::new).toList();

        return listDto;
    }


}
