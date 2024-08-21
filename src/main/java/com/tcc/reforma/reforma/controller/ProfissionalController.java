package com.tcc.reforma.reforma.controller;

import com.tcc.reforma.reforma.domain.profissao.Profissao;
import com.tcc.reforma.reforma.domain.profissao.ProfissaoUpdateDTO;
import com.tcc.reforma.reforma.domain.profissional.*;
import com.tcc.reforma.reforma.exception.ObjectNotFoundException;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import com.tcc.reforma.reforma.repository.ProfissaoRepository;
import com.tcc.reforma.reforma.repository.ProfissionalRepository;
import com.tcc.reforma.reforma.repository.UsuarioRepository;
import jakarta.persistence.PreRemove;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/profissional")
@CrossOrigin("*")
public class ProfissionalController {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private ProfissionalRepository profRepository;

    @Autowired
    private ProfissaoRepository profissaoRepository;


    @PostMapping("/cadastrar/{id}")
    @Transactional
    public ResponseEntity<Void> cadastrarProfissional(
            @RequestBody @Valid ProfissionalCreateDTO request, @PathVariable Long id) {
        Usuario usuario = userRepository.findById(id).get();
        Profissional profissional = request.toModel(usuario);
        usuario.setProfissional(profissional);
        userRepository.save(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(profissional.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/listarUm/{id}")
    public ResponseEntity<ProfissionalDetailDTO> listaUm(@PathVariable Long id) {
        ProfissionalDetailDTO profissional = new ProfissionalDetailDTO(userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id)));
        return ResponseEntity.ok().body(profissional);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ProfissionalDetailDTO>> listaTodos() {
        List<Usuario> listaUsuarios = userRepository.findAllProfessionals();
        List<ProfissionalDetailDTO> listaProfessional = listaUsuarios.stream().map(ProfissionalDetailDTO::new).toList();
        return ResponseEntity.ok().body(listaProfessional);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizaProfissional(
            @RequestBody @Valid ProfissionalUpdateDTO request, @PathVariable Long id) {
        Profissional profissional = profRepository.findById(id).get();
        request.updateData(profissional);
        profRepository.save(profissional);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/profissao/{id}")
    public ResponseEntity<Void> atualizaProfissao(@RequestBody ProfissaoUpdateDTO request,  @PathVariable Long id) {
        Profissao profissao = profissaoRepository.findById(id).get();
        request.updateData(profissao);
        profissaoRepository.save(profissao);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/avaliarProfissional/{id}")
    public ResponseEntity<Void> avaliaProfissional(@RequestBody @Valid InsertScoreDTO request, @PathVariable Long id){
        Profissional profissional = profRepository.findById(id).get();
        request.updateDateProfissional(profissional);
        profRepository.save(profissional);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/{idUsuario}/deletar/{idProfissional}")
    public ResponseEntity<Void> deletaProfessional(@PathVariable Long idUsuario, @PathVariable Long idProfissional) {
        Usuario usuario = userRepository.findById(idUsuario).get();
        preDelete(usuario);
        profRepository.deleteById(idProfissional);
        return ResponseEntity.noContent().build();
    }

    @PreRemove
    public void preDelete(Usuario usuario){
        usuario.setProfissional(null);
    }


}
