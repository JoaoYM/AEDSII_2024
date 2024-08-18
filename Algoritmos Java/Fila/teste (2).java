package Fila;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Celula;

import java.util.NoSuchElementException;


public class teste {
    private static Map<String, Integer> parkedCars = new HashMap<>();
    private static ArrayList <Cars> cars          = new ArrayList<>();
    private static Fila<String> filaGenerica      = new Fila<>();
    
    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        String  line = scan.nextLine();
        while(!line.equals("FIM")){
            String[] params = line.split(" ");
            String   placa  = params[1].concat(" " + params[2]);
            parkingManager(placa, params[0]);
            line = scan.nextLine();
        }

        if (!filaGenerica.vazia()){
            filaGenerica.imprimir();
        }
        scan.close();
    }
    

    private static void parkingManager(String placa, String comando){
        Fila<String> filaCopy          = new Fila<>();
        if (!parkedCars.containsKey(placa)){
            if (comando.equals("C")){
                parkedCars.put(placa, cars.size());
                cars.add(new Cars(placa, 0));
                filaGenerica.enfileirar(placa);
                System.out.println("Carro de placa " + placa + " entrou no estacionamento.");
            }else{
                System.out.println("Carro nao encontrado!");
                parkedCars.forEach((key, valor) -> {
                    Cars car = cars.get(parkedCars.get(key));
                    car.setManobrasEfetuadas(car.getManobrasEfetuadas() + 1);
                    cars.set(parkedCars.get(key), car);
                });
            }
        }else{
            // Sair do estacionamento 
            String primeiroVeiculo = filaGenerica.consultarPrimeiro();
            if(!filaGenerica.vazia() && !primeiroVeiculo.isEmpty()){
                while (!placa.equals(primeiroVeiculo)){
                    // Atualizar manobras efetuadas com o carro para a remoção do carro cuja saída é pretendida
                    Cars car = cars.get(parkedCars.get(primeiroVeiculo));
                    car.setManobrasEfetuadas(car.getManobrasEfetuadas() + 1);
                    cars.set(parkedCars.get(primeiroVeiculo), car);
                    // Adicioná-lo à fila de carros removidos para retorná-lo posteriormente à posição original
                    filaCopy.enfileirar(filaGenerica.desenfileirar());
                    primeiroVeiculo = filaGenerica.consultarPrimeiro();
                }
    
                System.out.println("Carro de placa " +  placa + " saiu do estacionamento.");
                System.out.println("Esse carro foi manobrado " + cars.get(parkedCars.get(placa)).getManobrasEfetuadas() + " vezes.");
    
                // Remoção do carro da lista de carros presentes no estacionamento 
                // cars.remove(parkedCars.get(placa).intValue());
                parkedCars.remove(placa);
                while (!filaGenerica.vazia()){
                    String veiculo = filaGenerica.desenfileirar();
                    if(!veiculo.equals(placa)){
                        filaCopy.enfileirar(veiculo);
                    }
                }
    
                // Atualizar posição dos carros na fila
                filaGenerica = filaCopy;
            }
        }
    }
}

class Cars{
    private String nome           = "";
    private int manobrasEfetuadas =  0;

    public Cars(String nome, int manobrasEfetuadas){
        this.nome                 = nome;
        this.manobrasEfetuadas    = manobrasEfetuadas;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getManobrasEfetuadas() {
        return this.manobrasEfetuadas;
    }

    public void setManobrasEfetuadas(int manobrasEfetuadas) {
        this.manobrasEfetuadas = manobrasEfetuadas;
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

class Fila<E> {

	private Celula<E> frente;
	private Celula<E> tras;
	
	Fila() {
		
		Celula<E> sentinela = new Celula<E>();
		frente = tras = sentinela;
	}
	
	public boolean vazia() {
		
		return (frente == tras);
	}
	
	public void enfileirar(E item) {
		
		Celula<E> novaCelula = new Celula<E>(item);
		
		tras.setProximo(novaCelula);
		tras = tras.getProximo();
	}
	
	public E desenfileirar() {
		
		E item = null;
		Celula<E> primeiro;
		
		item = consultarPrimeiro();
		
		primeiro = frente.getProximo();
		frente.setProximo(primeiro.getProximo());
		
		primeiro.setProximo(null);
			
		// Caso o item desenfileirado seja também o último da fila.
		if (primeiro == tras)
			tras = frente;
		
		return item;
	}
	
	public E consultarPrimeiro() {

		if (vazia()) {
			throw new NoSuchElementException("Nao há nenhum item na fila!");
		}

		return frente.getProximo().getItem();

	}
	
	public void imprimir() {
		
		Celula<E> aux;
		
		if (vazia())
			System.out.println("A fila está vazia!");
		else {
			aux = this.frente.getProximo();
			while (aux != null) {
				System.out.println(aux.getItem());
				aux = aux.getProximo();
			}
		} 	
	}
}