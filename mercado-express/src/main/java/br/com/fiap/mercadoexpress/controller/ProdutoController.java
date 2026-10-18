package br.com.fiap.mercadoexpress.controller;

import br.com.fiap.mercadoexpress.model.Produto;
import br.com.fiap.mercadoexpress.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** CRUD de produtos. READ para todos os logados; CREATE/UPDATE/DELETE só ADMIN (ver SecurityConfig). */
@Controller
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    // READ (lista + busca)
    @GetMapping
    public String listar(@RequestParam(required = false) String busca, Model model) {
        model.addAttribute("produtos", produtoService.listar(busca));
        model.addAttribute("busca", busca);
        return "produtos/lista";
    }

    // READ (detalhe)
    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoService.buscar(id));
        return "produtos/detalhe";
    }

    // CREATE (form)
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos/form";
    }

    // UPDATE (form)
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoService.buscar(id));
        return "produtos/form";
    }

    // CREATE / UPDATE (submit) — se vier id é update
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produto") Produto produto, BindingResult result,
                         RedirectAttributes ra) {
        if (result.hasErrors()) return "produtos/form";
        boolean novo = produto.getId() == null;
        produtoService.salvar(produto);
        ra.addFlashAttribute("sucesso", novo ? "Produto cadastrado!" : "Produto atualizado!");
        return "redirect:/produtos";
    }

    // DELETE
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        try {
            produtoService.excluir(id);
            ra.addFlashAttribute("sucesso", "Produto excluído!");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("erro", "Este produto já aparece em pedidos e não pode ser excluído. "
                    + "Zere o estoque em vez de excluir.");
        }
        return "redirect:/produtos";
    }
}
