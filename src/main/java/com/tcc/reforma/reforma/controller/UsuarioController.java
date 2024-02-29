package com.tcc.reforma.reforma.controller;

import com.tcc.reforma.reforma.domain.profissional.InsertScoreDTO;
import com.tcc.reforma.reforma.domain.projeto.Projeto;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import com.tcc.reforma.reforma.domain.usuario.UsuarioCreateDTO;
import com.tcc.reforma.reforma.domain.usuario.UsuarioUpdateDTO;
import com.tcc.reforma.reforma.repository.ProjetoRepository;
import com.tcc.reforma.reforma.repository.UsuarioRepository;
import com.tcc.reforma.reforma.service.S3Service;
import jakarta.persistence.PreRemove;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private S3Service s3Service;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<Void> cadastraUsuario(@RequestBody @Valid UsuarioCreateDTO request) {
        Usuario usuario = request.toModel(request);
        userRepository.save(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @PostMapping("/fotoPerfil")
    public ResponseEntity<?> cadastrarFotoPerfil(@RequestParam(name="file") MultipartFile file, Long id){
            URI uri = s3Service.uploadFile(file);
            return ResponseEntity.created(uri).build();
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizaUsuario(@RequestBody @Valid UsuarioUpdateDTO request, @PathVariable Long id) {
        Usuario usuario = userRepository.findById(id).get();
        request.updateData(usuario);
        userRepository.save(usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/avaliarCliente/{id}")
    public ResponseEntity<Void> avaliaCliente(@RequestBody @Valid InsertScoreDTO request, @PathVariable Long id) {
        Usuario usuario = userRepository.findById(id).get();
        request.updateDateCliente(usuario);
        userRepository.save(usuario);
        return ResponseEntity.noContent().build();
    }

   @Transactional
   @DeleteMapping("/deletar/{id}")
   public ResponseEntity<Void> deletaUsuario(@PathVariable Long id){
      Usuario usuario = userRepository.findById(id).get();
       preDelete(usuario);
        userRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }

    @PreRemove
    public void preDelete(Usuario usuario){

        List<Projeto> listaProjetos = new ArrayList<>();

        if(usuario.getProfissional() != null){
            if(!usuario.getProfissional().getProjetosTrabalhados().isEmpty()){
                for (Projeto p: usuario.getProfissional().getProjetosTrabalhados()){
                    if(p.getCliente() != null){
                        p.setProfissional(null);
                    } else {
                        listaProjetos.add(p);
                    }
                }
                usuario.getProfissional().setNullProjetosTrabalhados();
            }
        }

        if(!usuario.getProjetosCriados().isEmpty()) {
            for (Projeto p: usuario.getProjetosCriados()){
                if(p.getProfissional() != null){
                    p.setCliente(null);
                } else {
                    listaProjetos.add(p);
                }
            }
            usuario.setNullProjetosCriados();
        }
        projetoRepository.deleteAll(listaProjetos);
    }


}
