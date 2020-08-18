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
public class AvlTree {
   private AvlNode raiz = null;  
  
       public AvlTree( ) {  
           raiz = null;  
       }  
         
       public void clear() {  
           raiz = null;  
       }  
       public boolean isEmpty() {  
           return raiz == null;  
       }  
         
       public AvlNode getNoRaiz (){  
           return raiz;  
       }  
         
       /** Retorna a altura da árvore */  
       private static int altura( AvlNode t ) {  
           return t == null ? -1 : t.altura;  
       }  
         
        /** 
        * Retorna o maior valor ente lhs e rhs. 
        */  
       private static int max( int lhs, int rhs ) {  
           return lhs > rhs ? lhs : rhs;  
       }  
         
       /** Retorna o fator de balanceamento da árvore com raiz t */   
       private int getFactor (AvlNode t) {  
           return altura( t.esquerda ) - altura( t.direita );  
       }  
         
       public boolean insert (int x) {  
           raiz = insert (x, raiz);  
           return true;  
       }  
         
       private AvlNode insert (int x, AvlNode t) {  
           if( t == null )  
               t = new AvlNode( x, null, null );  
           else if( x<t.chave ) t.esquerda= insert( x, t.esquerda );  
           else if( x>t.chave) t.direita = insert( x, t.direita );  
           t = balance (t);  
           return t;  
       }  
         
       public AvlNode balance (AvlNode t) {  
           if ( getFactor(t) == 2 ) {  
                   if (getFactor (t.esquerda)>0) t = doRightRotation( t );  
                   else t = doDoubleRightRotation( t );  
           }  
           else if ( getFactor(t) == -2 ) {  
                   if ( getFactor(t.direita)<0 ) t = doLeftRotation( t );  
                   else t = doDoubleLeftRotation( t );  
           }  
           t.altura = max( altura( t.esquerda ), altura( t.direita ) ) + 1;  
           return t;  
       }  
  
       /** Faz Rotação simples a direita */  
       private static AvlNode doRightRotation( AvlNode k2 ) {  
           AvlNode k1 = k2.esquerda;  
           k2.esquerda = k1.direita;  
           k1.direita = k2;  
           k2.altura= max( altura( k2.esquerda ), altura( k2.direita ) ) + 1;  
           k1.altura = max( altura( k1.esquerda ), k2.altura ) + 1;  
           return k1;  
       }  
  
       /** Rotação simples à esquerda */  
       private static AvlNode doLeftRotation( AvlNode k1 ) {  
           AvlNode k2 = k1.direita;  
           k1.direita = k2.esquerda;  
           k2.esquerda = k1;  
           k1.altura = max( altura( k1.esquerda ),altura( k1.direita ) ) + 1;  
           k2.altura = max( altura( k2.direita ), k1.altura ) + 1;  
           return k2;  
       }  
  
       /** Rotação dupla à direita */  
       private static AvlNode doDoubleRightRotation( AvlNode k3 ) {  
           k3.esquerda = doLeftRotation( k3.esquerda);  
           return doRightRotation( k3 );  
      }  
  
       /** Rotação dupla à esquerda */  
       private static AvlNode doDoubleLeftRotation( AvlNode k1 ) {  
           k1.direita = doRightRotation( k1.direita );  
           return doLeftRotation( k1 );  
       }  
         
       public AvlNode search(int el) {  
           return search(raiz,el);  
       }  
       protected AvlNode search(AvlNode p, int el) {  
           while (p != null) {  
               /* se valor procuradp == chave do nó retorna referência ao nó */   
               if (el==p.chave) return p;  
               /* se valor procurado < chave do nó, procurar na sub-árvore esquerda deste nó */  
               else if (el<p.chave) p = p.esquerda;  
               /* se valor procurado > chave do nó, procurar na sub-árvore direita deste nó */  
               else p = p.direita;  
           }  
           // caso chave não foi achada, retorna null  
           return null;  
       }  
         
       public void inorder() {  
           inorder(raiz);  
       }  
       protected void inorder(AvlNode p) {  
           if (p != null) {  
                inorder(p.esquerda);  
                System.out.print(p.chave+" - ");  
                inorder(p.direita);  
           }  
       }  
         
       public void preorder() {  
           preorder(raiz);  
       }  
       protected void preorder(AvlNode p) {  
           if (p != null) {  
                System.out.print(p.chave + " ");  
                preorder(p.esquerda);  
                preorder(p.direita);  
           }  
       }  
         
       public void postorder() {  
           postorder(raiz);  
       }  
     
       protected void postorder(AvlNode p) {  
           if (p != null) {  
                postorder(p.esquerda);  
                postorder(p.direita);  
                System.out.print(p.chave + " ");  
           }  
       }  
         
   protected AvlNode searchFather (int el) {  
       AvlNode p = raiz;  
       AvlNode prev = null;  
       while (p != null && !(p.chave==el)) {  // acha o nó p com a chave el  
           prev = p;                             
           if (p.chave<el)  
                 p = p.direita;  
           else p = p.esquerda;  
       }  
       if (p!=null && p.chave==el) return prev;  
       return null;  
   }  
     
     
   public void displayTree() {  
    if (isEmpty()){  
        System.out.println("Árvore vazia!");  
        return;  
    }             
    String separator = String.valueOf("  |__");  
    System.out.println(this.raiz.chave+"("+raiz.altura+")");  
    displaySubTree(raiz.esquerda,  separator);  
    displaySubTree(raiz.direita, separator);  
}  
private void displaySubTree(AvlNode node, String separator) {  
      
    if (node != null) {  
          
        AvlNode father = this.searchFather(node.chave);  
        if (node.equals(father.esquerda) == true) {  
            System.out.println(separator+node.chave+"("+node.altura+")"+" (ESQ)");  
        }else{  
            System.out.println(separator+node.chave+"("+node.altura+")"+" (DIR)");  
        }             
        displaySubTree(node.esquerda,  "     "+separator);  
        displaySubTree(node.direita, "     "+separator);  
    }  
}  
         
       public static void main (String args[]){  
           AvlTree t = new AvlTree();  
           t.insert(1);  
           t.insert(2);  
           t.insert(3);  
           t.insert(4);  
           t.insert(5);  
           t.insert(6);  
           t.insert(7);  
           t.insert(8);  
           t.insert(9);  
           t.displayTree();  
       }  
   
}
