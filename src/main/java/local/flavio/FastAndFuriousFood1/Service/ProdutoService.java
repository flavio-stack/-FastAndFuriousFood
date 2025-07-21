/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.flavio.FastAndFuriousFood1.Service;

import java.util.List;
import local.flavio.FastAndFuriousFood1.Entities.Produto;
import local.flavio.FastAndFuriousFood1.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







/**
 *
 * @author ppjata
 */
@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;
  
  public Produto salvar(Produto produto) {
      
      return produtoRepository.save(produto);
  }
  
  public List<Produto> findByCategoria(String categoria) {
      return produtoRepository.findByCategoria(categoria);
  }
          public void excluir(Long id ) {
              produtoRepository.deleteById(id);
    }
  }
