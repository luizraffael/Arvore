/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AVL;

/**
 *
 * @author Luiz Rafael
 */
public class ArvoreAVL {
    private static class ARVORE {
 
        public int num, alt_d, alt_e;
        public ARVORE direita, esquerda;
    }
 //metodo para inserir na arvore
    public static ARVORE inserir(ARVORE aux, int num) {
         // o objeto novo é um objeto auxiliar
        ARVORE novo;
        if (aux == null) {                      //se aux for null, novo recebe uma nova arvore
            novo = new ARVORE();                // com altura 0
            novo.num = num;
            novo.alt_d = 0;
            novo.alt_e = 0;
            novo.esquerda = null;
            novo.direita = null;
            aux = novo;
        } else if (num < aux.num) {                             //então, se numero for menor, insere na esquerda  
            aux.esquerda = inserir(aux.esquerda, num);          //faz a verificação para o balanceamento
            if (aux.esquerda.alt_d > aux.esquerda.alt_e) {         //se altura da esquerda|direita for maior que esqrda|esqrda
                aux.alt_e = aux.esquerda.alt_d + 1;                //altura da esquerda recebe altuda da direita +1 para ficar balanceado 
            } else {
                aux.alt_e = aux.esquerda.alt_e + 1;
            }
            aux = balanceamento(aux);
        } else {                                //senão, insere na direita, com a mesma verificação feita acima
            aux.direita = inserir(aux.direita, num);
            if (aux.direita.alt_d > aux.direita.alt_e) {
                aux.alt_d = aux.direita.alt_d + 1;
            } else {
                aux.alt_d = aux.direita.alt_e + 1;
            }
            aux = balanceamento(aux);       //
        }
        return aux;
    }
 //o metodo balanceamento, como o proprio nome já diz, serve para balancear a arvore
    public static ARVORE balanceamento(ARVORE aux) {
        int d, df;
        d = aux.alt_d - aux.alt_e;
        if (d == 2) {
            df = aux.direita.alt_d - aux.direita.alt_e;
            if (df >= 0) {
                aux = rotacao_esquerda(aux);
            } else {
                aux.direita = rotacao_direita(aux.direita);
                aux = rotacao_esquerda(aux);
            }
        } else if (d == -2) {
            df = aux.esquerda.alt_d - aux.esquerda.alt_e;
            if (df <= 0) {
                aux = rotacao_direita(aux);
            } else {
                aux.esquerda = rotacao_esquerda(aux.esquerda);
                aux = rotacao_direita(aux);
            }
        }
        return aux;
    }
 
    public static ARVORE rotacao_esquerda(ARVORE aux) {
        ARVORE aux1, aux2;
        aux1 = aux.direita;
        aux2 = aux1.esquerda;
        aux.direita = aux2;
        aux1.esquerda = aux;
        if (aux.direita == null) {
            aux.alt_d = 0;
        } else if (aux.direita.alt_e > aux.direita.alt_d) {
            aux.alt_d = aux.direita.alt_e + 1;
        } else {
            aux.alt_d = aux.direita.alt_d + 1;
        }
 
        if (aux1.esquerda.alt_e > aux1.esquerda.alt_d) {
            aux1.alt_e = aux1.esquerda.alt_e + 1;
        } else {
            aux1.alt_e = aux1.esquerda.alt_d + 1;
        }
        return aux1;
    }
 
    public static ARVORE rotacao_direita(ARVORE aux) {
        ARVORE aux1, aux2;
        aux1 = aux.esquerda;
        aux2 = aux1.direita;
        aux.esquerda = aux2;
        aux1.direita = aux;
        if (aux.esquerda == null) {
            aux.alt_e = 0;
        } else if (aux.esquerda.alt_e > aux.esquerda.alt_d) {
            aux.alt_e = aux.esquerda.alt_e + 1;
        } else {
            aux.alt_e = aux.esquerda.alt_d + 1;
        }
 
        if (aux1.direita.alt_e > aux1.direita.alt_d) {
            aux1.alt_d = aux1.direita.alt_e + 1;
        } else {
            aux1.alt_d = aux1.direita.alt_d + 1;
        }
        return aux1;
    }
 
    public static void exibiremordem(ARVORE aux) {
        if (aux != null) {
            exibiremordem(aux.esquerda);
            System.out.print(aux.num + ", ");
            exibiremordem(aux.direita);
        }
    }
 
    public static void exibirpreordem(ARVORE aux) {
        if (aux != null) {
            System.out.print(aux.num + ", ");
            exibirpreordem(aux.esquerda);
            exibirpreordem(aux.direita);
        }
    }
 
    public static void exibirposordem(ARVORE aux) {
        if (aux != null) {
            exibirposordem(aux.esquerda);
            exibirposordem(aux.direita);
            System.out.print(aux.num + ", ");
        }
    }
 /* A remoção de um nó em uma árvore AVL pode também provocar seu 
    desbalanceamento. Para rebalancear a árvore usamos um processo 
    semelhante ao que foi usado na inserção, através do uso de rotações*/
    public static ARVORE excluir(ARVORE aux, int num) {
        ARVORE p, p2;
        if (aux.num == num) {                                   
            if (aux.esquerda == aux.direita) {
                return null;
            } else if (aux.esquerda == null) {
                return aux.direita;
            } else if (aux.direita == null) {
                return aux.esquerda;
            } else {
                p2 = aux.direita;
                p = aux.direita;
                while (p.esquerda != null) {
                    p = p.esquerda;
                }
                p.esquerda = aux.esquerda;
                return p2;
            }
        } else if (aux.num < num) {
            aux.direita = excluir(aux.direita, num);
        } else {
            aux.esquerda = excluir(aux.esquerda, num);
        }
        return aux;
    }
    
    /* Depois de excluir parar a função atualizar para recalcular os a profundidade dos nó 
    para que possa ser balanceada*/
    public static ARVORE atualizar(ARVORE aux) {        
        if (aux != null) {
            aux.esquerda = atualizar(aux.esquerda);
            if (aux.esquerda == null) {
                aux.alt_e = 0;
            } else if (aux.esquerda.alt_e > aux.esquerda.alt_d) {
                aux.alt_e = aux.esquerda.alt_e + 1;
            } else {
                aux.alt_e = aux.esquerda.alt_d + 1;
            }
            aux.direita = atualizar(aux.direita);
            if (aux.direita == null) {
                aux.alt_e = 0;
            } else if (aux.direita.alt_e > aux.direita.alt_d) {
                aux.alt_d = aux.direita.alt_e + 1;
            } else {
                aux.alt_d = aux.direita.alt_d + 1;
            }
            aux = balanceamento(aux);
        }
        return aux;
    }
    //consulta, se existe, retorna verdadeiro
    public static boolean consultar(ARVORE aux, int num, boolean loc) {
        if (aux != null && loc == false) {
            if (aux.num == num) {
                loc = true;
            } else if (num < aux.num) {
                loc = consultar(aux.esquerda, num, loc);
            } else {
                loc = consultar(aux.direita, num, loc);
            }
        }
        return loc;
    }
 //metodo PRINCIPAL  (teste)
    public static void main(String[] args) {
 
        ARVORE a = null;
 
        a = inserir(a, 1);
        a = inserir(a, 2);
        a = inserir(a, 3);
        a = inserir(a, 4);
        a = inserir(a, 5);
 
 
        System.out.print("EM : ");
        exibiremordem(a);
        System.out.println();
        System.out.print("PRE : ");
        exibirpreordem(a);
        System.out.println();
        System.out.print("POS : ");
        exibirposordem(a);
        System.out.println();
 
        int num = 2;
        if (consultar(a, num, false)) {
            a = excluir(a, num);
            a = atualizar(a);
        }
 
        System.out.println();
        System.out.print("EM : ");
        exibiremordem(a);
        System.out.println();
        System.out.print("PRE : ");
        exibirpreordem(a);
        System.out.println();
        System.out.print("POS : ");
        exibirposordem(a);
        System.out.println();
 
        a = inserir(a, 2);
 
        System.out.println();
        System.out.print("EM : ");
        exibiremordem(a);
        System.out.println();
        System.out.print("PRE : ");
        exibirpreordem(a);
        System.out.println();
        System.out.print("POS : ");
        exibirposordem(a);
        System.out.println();
    }

}
