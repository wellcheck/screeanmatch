package br.com.wellington.screenmatch.controller;

import br.com.wellington.screenmatch.domain.filme.DadosAlteracaoFilmes;
import br.com.wellington.screenmatch.domain.filme.DadosCadastroFilmes;
import br.com.wellington.screenmatch.domain.filme.Filme;
import br.com.wellington.screenmatch.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.ICloseElementTag;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

        @Autowired
        private FilmeRepository repository;

        @GetMapping("/formulario")
        public String CarregaPaginaFormulario(Long id,  Model model){
            if(id != null){
                var filme = repository.getReferenceById(id);
                model.addAttribute("filme", filme);
            }
            return "filmes/formulario";
        }

        @PostMapping
        @Transactional
        public String CadastroFilme(DadosCadastroFilmes dados){
           var filme = new Filme(dados);
           repository.save(filme);
            return "redirect:filmes";
        }

        @PutMapping
        @Transactional
        public String alteraFilme(DadosAlteracaoFilmes dados){
            var filme = repository.getReferenceById(dados.id());
            filme.atualizaDados(dados);
            return "redirect:/filmes";
        }

        @GetMapping
        public String CarregaPaginaListagem(Model model){ //Model envia variavel para a pagina
            model.addAttribute("lista", repository.findAll());  //th:each="filme : ${lista}" serve para enviar variaveis especificas para pagina
            return "filmes/listagem";
        }

    @DeleteMapping
    @Transactional
    public String DeletarFilme(Long id){
        repository.deleteById(id);
        return "redirect:/filmes";
    }

}
