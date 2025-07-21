/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.flavio.FastAndFuriousFood1.Controller;

import java.util.List;
import java.util.Optional;
import local.flavio.FastAndFuriousFood1.Entities.Produto;
import local.flavio.FastAndFuriousFood1.Repository.ProdutoRepository;
import local.flavio.FastAndFuriousFood1.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author ppjata
 */
@RestController
@RequestMapping("/fastfurious")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    
     @GetMapping("/produto") 
            public List<Produto> listas(){
                return produtoRepository.findAll();
            }
    
    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> buscar (@PathVariable long id) {
    
        Optional<Produto> produto = produtoRepository.findById(id);
        
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        }else {
            return ResponseEntity.notFound().build();
        }      
    }
     @PostMapping("/produto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }
    
     @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> atualizar( @PathVariable Long id,
                                             @RequestBody Produto produto) {
        
        if(!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
       produto.setId(id);
       produto = produtoService.salvar(produto);
        return ResponseEntity.ok(produto);
        
    }
     @DeleteMapping("/produto/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        
        if(!produtoRepository.existsById(id)) { 
            return ResponseEntity.notFound().build();
    }
    
   produtoService.excluir(id);
    return ResponseEntity.noContent().build();
}
    
    @GetMapping("/produto/categoria/{categoria}")
    public List<Produto> findByCategoria(@PathVariable String categoria) {
        return produtoService.findByCategoria(categoria);
    
    }
}
   
