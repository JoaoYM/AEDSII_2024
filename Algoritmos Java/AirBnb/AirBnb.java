package AirBnb;
import java.io.*;
import java.util.*;

import Acomodacao.Ordenação.Acomodacao;

public class AirBnb{

    public static void main(String[] args) {
        List<Acomodacao> acomodacoes = Acomodacao.readFile();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String linha = scanner.nextLine().trim();
            if (linha.equals("FIM")) {
                break;
            }

            int numAcomodacoes = Integer.parseInt(linha);
            ABB<Acomodacao> arvore = new ABB<>();

            for (int i = 0; i < numAcomodacoes; i++) {
                int roomId = Integer.parseInt(scanner.nextLine().trim());
                Acomodacao acomodacao = encontrarAcomodacaoPorId(acomodacoes, roomId);
                if (acomodacao != null) {
                    arvore.adicionar(acomodacao);
                }
            }

            System.out.println("Numero de folhas da arvore: " + arvore.numFolhas());
            System.out.println("Numero de acomodacoes presentes na arvore: " + arvore.numNos());
            System.out.println("Altura da arvore: " + arvore.obterAltura());
        }
        scanner.close();
    }

    private static Acomodacao encontrarAcomodacaoPorId(List<Acomodacao> acomodacoes, int roomId) {
        for (Acomodacao acomodacao : acomodacoes) {
            if (acomodacao.getRoomId() == roomId) {
                return acomodacao;
            }
        }
        return null;
    }
}

class Acomodacao implements Comparable<Acomodacao> {
    private int roomId;
    private int hostId;
    private String roomType;
    private String country;
    private String city;
    private String neighbourhood;
    private int reviews;
    private double overallSatisfaction;
    private int accommodates;
    private double bedrooms;
    private double price;
    private String propertyType;

    public Acomodacao() {}

    public Acomodacao(int roomId, int hostId, String roomType, String country, String city, String neighbourhood, int reviews,
                      double overallSatisfaction, int accommodates, double bedrooms, double price, String propertyType) {
        this.roomId = roomId;
        this.hostId = hostId;
        this.roomType = roomType;
        this.country = country;
        this.city = city;
        this.neighbourhood = neighbourhood;
        this.reviews = reviews;
        this.overallSatisfaction = overallSatisfaction;
        this.accommodates = accommodates;
        this.bedrooms = bedrooms;
        this.price = price;
        this.propertyType = propertyType;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    // Outros getters...

    public static List<Acomodacao> readFile() {
        String arquivoOrigem = "/tmp/dados_airbnb.txt";
        List<Acomodacao> acomodacoes = new ArrayList<>();

        try {
            BufferedReader leitor = new BufferedReader(new FileReader(arquivoOrigem));
            String linha;

            // Desconsiderar primeira linha
            leitor.readLine();

            // Lê cada linha do arquivo até que encontre a condição de parada
            while ((linha = leitor.readLine()) != null) {
                if (!linha.isEmpty()) {
                    String[] parametros = linha.split("\t");
                    Acomodacao registro = new Acomodacao(
                        Integer.parseInt(parametros[0].trim()),
                        Integer.parseInt(parametros[1].trim()),
                        parametros[2].trim(),
                        parametros[3].trim(),
                        parametros[4].trim(),
                        parametros[5].trim(),
                        Integer.parseInt(parametros[6].trim()),
                        Double.parseDouble(parametros[7].trim()),
                        Integer.parseInt(parametros[8].trim()),
                        Double.parseDouble(parametros[9].trim()),
                        Double.parseDouble(parametros[10].trim()),
                        parametros[11].trim()
                    );
                    acomodacoes.add(registro);
                }
            }
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return acomodacoes;
    }

    @Override
    public int compareTo(Acomodacao o) {
        int comparacao = this.country.compareTo(o.country);
        if (comparacao != 0) return comparacao;

        comparacao = this.city.compareTo(o.city);
        if (comparacao != 0) return comparacao;

        comparacao = this.neighbourhood.compareTo(o.neighbourhood);
        if (comparacao != 0) return comparacao;

        return Integer.compare(this.roomId, o.roomId);
    }
}

class No<T extends Comparable<T>> {

    private T item;       
    private No<T> direita;    
    private No<T> esquerda;   

    public No(T item) {
        this.item = item;
        this.direita = null;
        this.esquerda = null;
    }

    public T getItem() {
        return item;
    }

    public No<T> getDireita() {
        return direita;
    }

    public void setDireita(No<T> direita) {
        this.direita = direita;
    }

    public No<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No<T> esquerda) {
        this.esquerda = esquerda;
    }

    public boolean ehFolha() {
        return this.direita == null && this.esquerda == null;
    }
}

class ABB<E extends Comparable<E>> {

    private No<E> raiz;

    public ABB() {
        raiz = null;
    }

    public boolean vazia() {
        return raiz == null;
    }

    public int numFolhas() {
        return numFolhas(raiz);
    }

    private int numFolhas(No<E> no) {
        if (no == null) return 0;
        if (no.ehFolha()) return 1;
        return numFolhas(no.getEsquerda()) + numFolhas(no.getDireita());
    }

    public int numNos() {
        return numNos(raiz);
    }

    private int numNos(No<E> no) {
        if (no == null) return 0;
        return 1 + numNos(no.getEsquerda()) + numNos(no.getDireita());
    }

    public int obterAltura() {
        return obterAltura(raiz);
    }

    private int obterAltura(No<E> no) {
        if (no == null) return -1;
        return 1 + Math.max(obterAltura(no.getEsquerda()), obterAltura(no.getDireita()));
    }

    public void adicionar(E item) {
        raiz = adicionar(raiz, item);
    }

    private No<E> adicionar(No<E> no, E item) {
        if (no == null) {
            return new No<>(item);
        }
        int comparacao = item.compareTo(no.getItem());
        if (comparacao < 0) {
            no.setEsquerda(adicionar(no.getEsquerda(), item));
        } else if (comparacao > 0) {
            no.setDireita(adicionar(no.getDireita(), item));
        }
        return no;
    }
}
