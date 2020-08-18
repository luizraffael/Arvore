package RaulJoaquim;
/*TestaArvoreBinaria.java
 * Codigo que testa a implementacao da classe ArvoreBinaria
 * 
 * A arvore que sera criada e testada segue abaixo:
		 * 				7
		 * 			  /	  \
		 * 		     4    10
		 *          / \     \
		 *         2   5    15
		 *              \   / \
		 *              6  11 20 
 *
 * Sera tambem executada uma operacao com JOptionPane, controlada 
 * pelo usuario                        
 * Desenvolvedor: Raul Joaquim Camara dos Santos
 * DATA: 02/04/08
 * */
//*****************************************************************************//

//Classe que testa as classes ArvoreBinaria e Node 
public class TestaArvoreBinaria {
	public static void main(String[] args){
		
		//criacao da arvore binaria
		ArvoreBinaria Tree = new ArvoreBinaria();
		//criacao de um no com info = 5
		System.out.println("Criando no...");
		Node Elem = new Node(5);
		System.out.println("informacao do no criado: " + Elem.info);
		
		//criacao de no com filhos
		System.out.println("Criando no com filhos...");
		Node Elem2 = new Node (10); //novo no
		Node Elem3 = new Node(6,Elem,Elem2);
		System.out.println("informacao do no criado: " + Elem3.info);
		System.out.println("informacao do filho esquerdo: " + Elem3.esq.info);
		System.out.println("informacao do filho direito: " + Elem3.dir.info);

		System.out.print("Buscando o elemento 7... ");
		if (Tree.buscarElemento(7) == null){//busca de elemento na arvore vazia
			System.out.println("Elemento nao existe na arvore");
		}
		else System.out.println("Achou o elemento " + 7);
		
		System.out.println("Inserindo  7...");
		Tree.inserirElemento(7);
		
		System.out.print("Buscando o elemento 7... ");
		if (Tree.buscarElemento(7) == null){//busca de elemento na raiz da arvore
			System.out.println("Elemento nao existe na arvore");
		}
		else System.out.println("Achou o elemento " + Tree.buscarElemento(7).info);
		
		System.out.println("Inserindo  10... ");
		Tree.inserirElemento(10);
		System.out.println("Inserindo  15... ");
		Tree.inserirElemento(15);
		System.out.println("Inserindo  4... ");
		Tree.inserirElemento(4);
		System.out.println("Inserindo  2... ");
		Tree.inserirElemento(2);
		System.out.println("Inserindo  20... ");
		Tree.inserirElemento(20);
		System.out.println("Inserindo  5... ");
		Tree.inserirElemento(5);
		System.out.println("Inserindo  6... ");
		Tree.inserirElemento(6);
		System.out.println("Inserindo  11... ");
		Tree.inserirElemento(11);
		System.out.println("Buscando o elemento 11... ");
		if (Tree.buscarElemento(11) == null){//busca de elemento na arvore
			System.out.println(" Elemento nao existe na arvore");
		}
		else System.out.println("Achou o elemento " + Tree.buscarElemento(11).info);
		
		//Retorna o tamanho da arvore
		System.out.println("O tamanho da arvore e': " + Tree.tamanho());
		//Testa a altura da arvore
		System.out.println("A altura da arvore e': " + Tree.altura());
		//A raiz da arvore
		System.out.println("A raiz da arvore e': " + Tree.raiz().info);
		

		System.out.println("Exibindo arvore em pre ordem: " + Tree.exibirArvorePreOrdem());
		//repetindo elemento
		System.out.println("Tenta inserir elemento repetido(4): ");
		Tree.inserirElemento(4);
		System.out.println("Exibindo arvore em pre ordem: " + Tree.exibirArvorePreOrdem());
		System.out.println("Exibindo arvore em ordem: " + Tree.exibirArvoreInOrdem());
		System.out.println("Exibindo arvore em pos ordem: " + Tree.exibirArvorePosOrdem());
		

		//removendo no sem filhos
		System.out.println("Removendo o no 2(no sem filhos)... ");
		Tree.removerElemento(2); 
		//removendo no com um filho
		System.out.println("Removendo o no 5(no com um filho)... ");
		Tree.removerElemento(5); 
		//removendo no com 2 filhos
		System.out.println("Removendo o no 15(no com 2 filhos)... ");
		Tree.removerElemento(7);
		System.out.println("Removendo todos os elementos... ");
		
		Tree.removerTodosElementos();
		System.out.println("Exibindo arvore em pre ordem: " + Tree.exibirArvorePreOrdem());
		
		System.out.println("\nARVORE BINARIA COM OPTION PANE");
		//Teste de execucao de uma arvore com Option Pane
		ArvoreBinaria Tree2 = new ArvoreBinaria();
		
		Tree2.start();
	}

}//;~
