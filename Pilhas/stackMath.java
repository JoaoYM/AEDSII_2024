// package AEDSII_2024.Pilhas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.NoSuchElementException;


public class stackMath{
    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        String  line = scan.nextLine();
        while(!line.equals("FIM")){
            polishNotation(line);
            line = scan.nextLine();
        }
        scan.close();
    }

    private static void polishNotation(String expressao) {
        Pilha<String> pilhaGenerica = new Pilha<>();
        ArrayList<String> saida = new ArrayList<>();

        Map<String, Integer> validTerms = new HashMap<>();
        validTerms.put("0", 0);
        validTerms.put("1", 1);
        validTerms.put("2", 2);
        validTerms.put("3", 3);
        validTerms.put("4", 4);
        validTerms.put("5", 5);
        validTerms.put("6", 6);
        validTerms.put("7", 7);
        validTerms.put("8", 8);
        validTerms.put("9", 9);
        validTerms.put("+", 10);
        validTerms.put("-", 10);
        validTerms.put("*", 11);
        validTerms.put("/", 11);
    
        String[] partes = expressao.replaceAll("\\s+", "").split("(?!^)");
    
        for (String parte : partes) {
            if (validTerms.containsKey(parte)) {
                int term = validTerms.get(parte);
                if (term <= 9) {
                    saida.add(0, parte + " ");
                } else {
                    while (!pilhaGenerica.vazia() && validTerms.containsKey(pilhaGenerica.consultarTopo()) &&
                            validTerms.get(pilhaGenerica.consultarTopo()) >= term) {
                        saida.add(0, pilhaGenerica.desempilhar() + " ");
                    }
                    pilhaGenerica.empilhar(parte);
                }
            }else if (Character.isLetter(parte.charAt(0))){
                saida.add(0, parte + " ");
            }else if (parte.equals("(")) {
                pilhaGenerica.empilhar(parte);
            } else if (parte.equals(")")) {
                while (!pilhaGenerica.vazia() && !pilhaGenerica.consultarTopo().equals("(")) {
                    saida.add(0, pilhaGenerica.desempilhar() + " ");
                }
                if (!pilhaGenerica.vazia()) {
                    pilhaGenerica.desempilhar(); 
                }
            }
        }
    
        while (!pilhaGenerica.vazia()) {
            saida.add(0, pilhaGenerica.desempilhar() + " ");
        }

        for(int i = saida.size() - 1; i >= 0; i--){
             System.out.print(saida.get(i));
        }

        System.out.println();
    }
}

class Pilha<E> {

	private Celula<E> topo;
	private Celula<E> fundo;

	public Pilha() {

		Celula<E> sentinela = new Celula<E>();
		fundo = sentinela;
		topo = sentinela;

	}

	public boolean vazia() {
		return fundo == topo;
	}

	public void empilhar(E item) {

		topo = new Celula<E>(item, topo);
	}

	public E desempilhar() {

		E desempilhado = consultarTopo();
		topo = topo.getProximo();
		return desempilhado;

	}

	public E consultarTopo() {

		if (vazia()) {
			throw new NoSuchElementException("Nao há nenhum item na pilha!");
		}

		return topo.getItem();

	}

}


class Celula<T> {

	private final T item;
	private Celula<T> proximo;

	public Celula() {
		this.item = null;
		setProximo(null);
	}

	public Celula(T item) {
		this.item = item;
		setProximo(null);
	}

	public Celula(T item, Celula<T> proximo) {
        this.item = item;
        this.proximo = proximo;
    }
	
	public T getItem() {
		return item;
	}

	public Celula<T> getProximo() {
		return proximo;
	}

	public void setProximo(Celula<T> proximo) {
		this.proximo = proximo;
	}
}


