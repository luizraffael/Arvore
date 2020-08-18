/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arvore;

/**
 *
 * @author Luiz Rafael
 */
public class AvlNode {
   
    protected int altura;       // Altura  
    protected int chave;  
    protected AvlNode esquerda, direita;  
  
    public AvlNode ( int elemento ) {  
        this( elemento, null, null );  
    }  
  
    public AvlNode ( int elemento, AvlNode esq, AvlNode dir ) {  
        chave = elemento;  
        esquerda = esq;  
        direita = dir;  
        altura   = 0;  
    }
}
