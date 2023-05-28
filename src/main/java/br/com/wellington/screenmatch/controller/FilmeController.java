package br.com.wellington.screenmatch.controller;

import br.com.wellington.screenmatch.domain.filme.DadosCadastroFilmes;
import br.com.wellington.screenmatch.domain.filme.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

        private List<Filme> filmes = new ArrayList<>();

        @GetMapping("/formulario")
        public String carregaPaginaFormulario(){
            return "filmes/formulario";
        }

        @PostMapping()
        public String CadastroFilme(DadosCadastroFilmes dados, Model model){
           var filme = new Filme(dados);
           filmes.add(filme);
            return "redirect:filmes";
        }

        @GetMapping()
        public String carregaPaginaListagem(Model model){ //Model envia variavel para a pagina
            model.addAttribute("lista", filmes);  //th:each="filme : ${lista}" serve para enviar variaveis especificas para pagina
            return "filmes/listagem";
        }
}
