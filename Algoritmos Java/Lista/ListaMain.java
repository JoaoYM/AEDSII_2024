package Lista;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Pilha.Celula;

class Acomodacao {
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

    public int getHostId() {
        return hostId;
    }

    public String getRoomType() {
        return roomType;
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

    public int getReviews() {
        return reviews;
    }

    public double getOverallSatisfaction() {
        return overallSatisfaction;
    }

    public int getAccommodates() {
        return accommodates;
    }

    public double getBedrooms() {
        return bedrooms;
    }

    public double getPrice() {
        return price;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public static void printLine(Acomodacao registro) {
        System.out.println("[" + registro.getRoomId() + " ## " + registro.getHostId() + " ## " + registro.getRoomType() + " ## " + registro.getCountry() + " ## " + registro.getCity() + " ## " +
                registro.getNeighbourhood() + " ## " + registro.getReviews() + " ## " + registro.getOverallSatisfaction() + " ## " + registro.getAccommodates() + " ## " +
                registro.getBedrooms() + " ## " + registro.getPrice() + " ## " + registro.getPropertyType() + "]");
    }

    public static List<Acomodacao> readFile() {
        String arquivoOrigem = "/tmp/dados_airbnb.txt";
        List<Acomodacao> acomodacoes = new ArrayList<>();

        try {
            FileReader leitorArquivo = new FileReader(arquivoOrigem);
            BufferedReader leitor = new BufferedReader(leitorArquivo);

            String linha;

            // Desconsiderar primeira linha
            leitor.readLine();

            // Lê cada linha do arquivo até que encontre a condição de parada
            while ((linha = leitor.readLine()) != null) {
                if (!linha.isEmpty()) {
                    String[] parametros = linha.split("\t");
                    Acomodacao registro = new Acomodacao(Integer.parseInt(parametros[0].trim()), Integer.parseInt(parametros[1].trim()), parametros[2].trim(), parametros[3].trim(), parametros[4].trim(), parametros[5].trim(),
                            Integer.parseInt(parametros[6].trim()), Double.parseDouble(parametros[7].trim()), Integer.parseInt(parametros[8].trim()), Double.parseDouble(parametros[9].trim()),
                            Double.parseDouble(parametros[10].trim()), parametros[11].trim());
                    acomodacoes.add(registro);
                }
            }
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return acomodacoes;
    }
}

class ListaEncadeada<E> {
    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public ListaEncadeada() {
        Celula<E> sentinela = new Celula<>();
        this.primeiro = this.ultimo = sentinela;
        this.tamanho = 0;
    }

    public boolean vazia() {
        return (this.primeiro == this.ultimo);
    }

    public void inserirInicio(E item) {
        Celula<E> novaCelula = new Celula<>(item);
        novaCelula.setProximo(primeiro.getProximo());
        primeiro.setProximo(novaCelula);
        if (vazia()) {
            ultimo = novaCelula;
        }
        tamanho++;
    }

    public E removerFinal() {
        if (vazia()) {
            throw new IllegalStateException("Não foi possível remover o item da lista: a lista está vazia!");
        }
        Celula<E> anterior = primeiro;
        while (anterior.getProximo() != ultimo) {
            anterior = anterior.getProximo();
        }
        E itemRemovido = ultimo.getItem();
        ultimo = anterior;
        ultimo.setProximo(null);
        tamanho--;
        return itemRemovido;
    }

    public void imprimir() {
        Celula<E> atual = primeiro.getProximo();
        int posicao = 0;
        while (atual != null) {
            Acomodacao acomodacao = (Acomodacao) atual.getItem();
            System.out.print("[" + posicao + "] ");
            Acomodacao.printLine(acomodacao);
            atual = atual.getProximo();
            posicao++;
        }
    }
}

class Celula<T> {
    private final T item;
    private Celula<T> proximo;

    public Celula() {
        this.item = null;
        this.proximo = null;
    }

    public Celula(T item) {
        this.item = item;
        this.proximo = null;
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

public class ListaMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Acomodacao> acomodacoes = Acomodacao.readFile();
        ListaEncadeada<Acomodacao> lista = new ListaEncadeada<>();

        String linha;
        while (!(linha = scan.nextLine()).equals("FIM")) {
            int roomId = Integer.parseInt(linha);
            for (Acomodacao acomodacao : acomodacoes) {
                if (acomodacao.getRoomId() == roomId) {
                    lista.inserirInicio(acomodacao);
                    break;
                }
            }
        }

        int nOperacoes = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < nOperacoes; i++) {
            linha = scan.nextLine();
            String[] partes = linha.split(" ");
            String comando = partes[0];
            switch (comando) {
                case "II":
                    int roomId = Integer.parseInt(partes[1]);
                    for (Acomodacao acomodacao : acomodacoes) {
                        if (acomodacao.getRoomId() == roomId) {
                            lista.inserirInicio(acomodacao);
                            break;
                        }
                    }
                    break;
                case "RF":
                    Acomodacao removida = lista.removerFinal();
                    System.out.println("(R) " + removida.getRoomId());
                    break;
            }
        }

        lista.imprimir();
    }
}
