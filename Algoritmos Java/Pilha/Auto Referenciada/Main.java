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

    @Override
    public String toString() {
        return roomId + " ## " + hostId + " ## " + roomType + " ## " + country + " ## " + city + " ## " +
               neighbourhood + " ## " + reviews + " ## " + overallSatisfaction + " ## " + accommodates + " ## " +
               bedrooms + " ## " + price + " ## " + propertyType;
    }

    public static List<Acomodacao> readFile() {
        String arquivoOrigem = "/tmp/dados_airbnb.txt";
        List<Acomodacao> acomodacoes = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoOrigem))) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return acomodacoes;
    }
}

class Celula<T> {
    private T item;
    private Celula<T> anterior;
    private Celula<T> proximo;

    public Celula() {
        this.item = null;
        this.anterior = null;
        this.proximo = null;
    }

    public Celula(T item) {
        this.item = item;
        this.anterior = null;
        this.proximo = null;
    }

    public Celula(T item, Celula<T> anterior, Celula<T> proximo) {
        this.item = item;
        this.anterior = anterior;
        this.proximo = proximo;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Celula<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(Celula<T> anterior) {
        this.anterior = anterior;
    }

    public Celula<T> getProximo() {
        return proximo;
    }

    public void setProximo(Celula<T> proximo) {
        this.proximo = proximo;
    }
}

class ListaDuplamenteEncadeada<E> {
    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        Celula<E> sentinela = new Celula<>();
        this.primeiro = this.ultimo = sentinela;
        this.tamanho = 0;
    }

    public boolean vazia() {
        return (this.primeiro == this.ultimo);
    }

    public void inserirFinal(E novo) {
        Celula<E> novaCelula = new Celula<>(novo, this.ultimo, null);
        this.ultimo.setProximo(novaCelula);
        this.ultimo = novaCelula;
        this.tamanho++;
    }

    public E removerFinal() {
        if (vazia()) {
            throw new IllegalStateException("Não foi possível remover o último item da lista: a lista está vazia!");
        }

        Celula<E> removida = this.ultimo;
        Celula<E> penultima = this.ultimo.getAnterior();
        penultima.setProximo(null);
        removida.setAnterior(null);
        this.ultimo = penultima;
        this.tamanho--;
        return removida.getItem();
    }

    public int getTamanho() {
        return tamanho;
    }

    public Celula<E> getCelula(int index) {
        Celula<E> atual = this.primeiro.getProximo();
        for (int i = 0; i < index; i++) {
            if (atual != null) {
                atual = atual.getProximo();
            }
        }
        return atual;
    }

    public void swap(int i, int j) {
        Celula<E> celulaI = getCelula(i);
        Celula<E> celulaJ = getCelula(j);

        E temp = celulaI.getItem();
        celulaI.setItem(celulaJ.getItem());
        celulaJ.setItem(temp);
    }

    public void ordenar() {
        if (tamanho > 1) {
            quickSort(0, tamanho - 1);
        }
    }

    private void quickSort(int esquerda, int direita) {
        if (esquerda < direita) {
            int indexPivo = separar(esquerda, direita);
            quickSort(esquerda, indexPivo - 1);
            quickSort(indexPivo + 1, direita);
        }
    }

    private int separar(int esquerda, int direita) {
        E pivo = getCelula(esquerda).getItem();
        int i = esquerda + 1;
        int j = direita;

        while (i <= j) {
            if (comparar(getCelula(i).getItem(), pivo) <= 0) {
                i++;
            } else if (comparar(getCelula(j).getItem(), pivo) > 0) {
                j--;
            } else {
                swap(i, j);
                i++;
                j--;
            }
        }
        swap(esquerda, j);
        return j;
    }

    private int comparar(E item1, E item2) {
        Acomodacao acom1 = (Acomodacao) item1;
        Acomodacao acom2 = (Acomodacao) item2;

        if (acom1.getPrice() != acom2.getPrice()) {
            return Double.compare(acom1.getPrice(), acom2.getPrice());
        } else if (!acom1.getRoomType().equals(acom2.getRoomType())) {
            return acom1.getRoomType().compareTo(acom2.getRoomType());
        } else {
            return Integer.compare(acom1.getRoomId(), acom2.getRoomId());
        }
    }

    public Celula<E> getPrimeiro() {
        return primeiro;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Acomodacao> acomodacoes = Acomodacao.readFile();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        ListaDuplamenteEncadeada<Acomodacao> lista = new ListaDuplamenteEncadeada<>();

        for (int i = 0; i < n; i++) {
            int roomId = scanner.nextInt();
            scanner.nextLine();
            for (Acomodacao a : acomodacoes) {
                if (a.getRoomId() == roomId) {
                    lista.inserirFinal(a);
                    break;
                }
            }
        }

        lista.ordenar();

        Celula<Acomodacao> atual = lista.getPrimeiro().getProximo();
        int posicao = 0;
        while (atual != null) {
            System.out.println("[" + posicao + "] " + "[" + atual.getItem().toString() + "]");
            atual = atual.getProximo();
            posicao++;
        }
    }
}
