package br.com.wellington.screenmatch.controller;

import br.com.wellington.screenmatch.domain.filme.DadosCadastroFilmes;
import br.com.wellington.screenmatch.domain.filme.Filme;
import br.com.wellington.screenmatch.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.model.ICloseElementTag;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

        @Autowired
        private FilmeRepository repository;

        @GetMapping("/formulario")
        public String carregaPaginaFormulario(){
            return "filmes/formulario";
        }

        @PostMapping()
        public String CadastroFilme(DadosCadastroFilmes dados){
           var filme = new Filme(dados);
           repository.save(filme);
            return "redirect:filmes";
        }

        @GetMapping()
        public String carregaPaginaListagem(Model model){ //Model envia variavel para a pagina
            model.addAttribute("lista", repository.findAll());  //th:each="filme : ${lista}" serve para enviar variaveis especificas para pagina
            return "filmes/listagem";
        }

        public String deletar(@PathVariable Long id){
            repository.deleteAllById(id);
        }
}
