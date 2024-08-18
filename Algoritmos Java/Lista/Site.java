package Lista;
import java.util.Scanner;

public class Site {
    private static Lista listaDominios;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int totalDeEntradas = Integer.parseInt(scan.nextLine());
        listaDominios = new Lista(totalDeEntradas);
        int linhasLidas = 0;

        String nomeBusca = " ";
        while (!nomeBusca.equals("FIM")) {
            if (linhasLidas < totalDeEntradas) {
                String[] params = scan.nextLine().split(" - ");
                String nome = params[0].trim();
                String endereco = params[1].trim();
                Dominio dominio = new Dominio(nome, endereco);
                listaDominios.inserir(dominio, linhasLidas++);
            } else {
                nomeBusca = scan.nextLine().trim();
                if(!nomeBusca.equals("FIM")){
                    buscarDominio(nomeBusca);
                }
            }
        }

        listaDominios.imprimirTodos();
        scan.close();
    }

    private static void buscarDominio(String nomeBusca) {
        Dominio dominio = listaDominios.buscarItem(nomeBusca);
        if(dominio.getNome() != null){
            dominio.incrementarAcessos();
            listaDominios.realocarNoInicio(dominio);
            System.out.println(dominio.getEndereco());
        }
    }
}

class Dominio {
    private String nome;
    private String endereco;
    private int acessos;

    public Dominio(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.acessos = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getAcessos() {
        return acessos;
    }

    public void incrementarAcessos() {
        this.acessos++;
    }

    @Override
    public String toString() {
        return "Nome: [" + nome + "]\nEndereço: [" + endereco + "]\nNúmero de acessos: " + acessos;
    }
}


class Lista {
    private Dominio[] lista;
    private final int primeiro;
    private int ultimo;

    public Lista(int tamanho) {
        lista = new Dominio[tamanho];
        this.primeiro = this.ultimo = 0;
    }

    public boolean vazia() {
        return (this.primeiro == this.ultimo);
    }

    public boolean cheia() {
        return (this.ultimo == this.lista.length); 
    }

    public int getSize(){
        return this.ultimo;
    }

    public void inserir(Dominio novo, int posicao) {
        if (cheia())
            throw new IllegalStateException("Não foi possível inserir o item na lista: "
                    + "a lista está cheia!");

        if ((posicao < 0) || (posicao > this.ultimo)) 
            throw new IndexOutOfBoundsException ("Não foi possível inserir o item na lista: "
                    + "a posição informada é inválida!");

        for (int i = this.ultimo; i > posicao; i--)
            lista[i] = lista[i-1];

        lista[posicao] = novo;

        this.ultimo++;
    }

    public void realocarNoInicio(Dominio novo) {
        Dominio[] tempList = new Dominio[lista.length];
        tempList[0] = novo;
        int index = 0;
        Boolean elementoIterado  = false;

		for (int i = 0; i < this.ultimo; i++){
            if(!lista[i].getNome().equals(novo.getNome()) || (lista[i].getNome().equals(novo.getNome()) && elementoIterado)){
                tempList[++index] = lista[i];
            }else{
                elementoIterado = true;
            }
        }

        lista = tempList;
	}

    public Dominio buscarItem(String item) {
        if (vazia())
            throw new IllegalStateException("Não foi possível encontrar o item na lista: "
                    + "a lista está vazia!");

        for (int i = 0; i < this.ultimo; i++) {
                if (lista[i].getNome().equals(item)) {
                    return lista[i];
                }
        }

        System.out.println("O item procurado nao foi encontrado!");
        return new Dominio(null, null);
    }

    public Dominio remover(int posicao) {
        Dominio removido;

        if (vazia())
            throw new IllegalStateException("Não foi possível remover o item da lista: "
                    + "a lista está vazia!");

        if ((posicao < 0) || (posicao >= this.ultimo))
            throw new IndexOutOfBoundsException ("Não foi possível remover o item da lista: "
                    + "a posição informada é inválida!");

        removido = lista[posicao];

        this.ultimo--;

        for (int i = posicao; i < this.ultimo; i++)
            lista[i] = lista[i+1];

        return removido;
    }

    public void imprimirTodos() {
        for(int i = 0; i <= lista.length - 1; i++){
            System.out.println("Nome: [" + lista[i].getNome() + "]\nEndereco: [" + lista[i].getEndereco() + "]\nNumero de acessos: " + lista[i].getAcessos());
        }
    }
}