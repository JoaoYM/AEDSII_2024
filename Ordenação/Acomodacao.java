import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Acomodacao {
    private int roomId                    = 0;
    private int hostId                    = 0;
    private String roomType               = null;
    private String country                = null;
    private String city                   = null;
    private String neighbourhood          = null;
    private int reviews                   = 0;
    private double overallSatisfaction    = 0.0;
    private int accommodates              = 0;
    private double  bedrooms              = 0.0;
    private double price                  = 0.0;
    private String propertyType           = null;

    public static void main( String[] args){
        Scanner scan = new Scanner(System.in);
        List<Acomodacao> arrayAcomodacoes = new ArrayList<>();
        Map<String, Acomodacao> acomodacoes  = new HashMap<>();
    
        acomodacoes = readFile();
        String linha = null; 
        linha = scan.nextLine();
        int totalAcomodacoes = Integer.parseInt(linha);
        int linhasLidas      = 0;

        while(linhasLidas < totalAcomodacoes){
            linha = scan.nextLine();
            if(!linha.isEmpty()){
                arrayAcomodacoes.add(acomodacoes.get(linha));
            }
            linhasLidas++;
        }



        //arrayAcomodacoes   = bubbleSort(arrayAcomodacoes);
        //arrayAcomodacoes   = selectionSort(arrayAcomodacoes);
        //arrayAcomodacoes   = insertionSort(arrayAcomodacoes);
        //arrayAcomodacoes   = mergeSort(arrayAcomodacoes, 0, arrayAcomodacoes.size() -1 );
        //arrayAcomodacoes   = quickSort(arrayAcomodacoes, 0, arrayAcomodacoes.size() - 1);
        // arrayAcomodacoes  = heapSort(arrayAcomodacoes);




        for(Acomodacao acomodacao : arrayAcomodacoes){
            printLine(acomodacao);
        }

    }

    public Acomodacao(){

    };

    public Acomodacao(int roomId , int hostId ,  String roomType ,  String country,  String city,  String neighbourhood, int reviews,
                      double overallSatisfaction, int accommodates , double  bedrooms , double price ,  String propertyType){
    
                        setRoomId(roomId); 
                        setHostId(hostId);                     
                        setRoomType(roomType);                     
                        setCountry(country);               
                        setCity(city);
                        setNeighbourhood(neighbourhood);            
                        setReviews(reviews); 
                        setOverallSatisfaction(overallSatisfaction); 
                        setAccommodates(accommodates); 
                        setBedrooms(bedrooms); 
                        setPrice(price); 
                        setPropertyType(propertyType); 
    };

    public static Map<String, Acomodacao> readFile(){
        Scanner scan = new Scanner (System.in);
        String arquivoOrigem = "/tmp/dados_airbnb.txt";
        // String arquivoOrigem = "arquivo.txt";

        Map<String, Acomodacao> acomodacoes  = new HashMap<>();

        try {
            FileReader leitorArquivo = new FileReader(arquivoOrigem);
            BufferedReader leitor = new BufferedReader(leitorArquivo);

            String linha;

            // Desconsiderar primeira linha
            leitor.readLine();

            // Lê cada linha do arquivo até que encontre a condição de parada
            while ((linha = leitor.readLine()) != null) {
                if(!linha.isEmpty()){
                    String[] parametros =  linha.split("\t");
                    Acomodacao registro = new Acomodacao(Integer.parseInt(parametros[0].trim()), Integer.parseInt(parametros[1].trim()), parametros[2].trim(), parametros[3].trim(), parametros[4].trim(), parametros[5].trim(),
                                Integer.parseInt(parametros[6].trim()), Double.parseDouble(parametros[7].trim()), Integer.parseInt(parametros[8].trim()), Double.parseDouble(parametros[9].trim()),
                                Double.parseDouble(parametros[10].trim()), parametros[11].trim());
                    acomodacoes.put(Integer.toString(registro.getRoomId()) , registro);
                }
            }

            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 

        return acomodacoes;
    }

    public static void printLine(Acomodacao registro){
        System.out.println("[" + registro.getRoomId() +  " ## " +  registro.getHostId()  + " ## " + registro.getRoomType() + " ## " + registro.getCountry() + " ## "  + registro.getCity() + " ## " + 
                                 registro.getNeighbourhood() + " ## " + registro.getReviews() + " ## " + registro.getOverallSatisfaction() +" ## " + registro.getAccommodates() + " ## "   + 
                                 registro.getBedrooms() + " ## " + registro.getPrice() + " ## " + registro.getPropertyType() + "]");
    }


    public int getRoomId() {
        return this.roomId;
    }

    public int getHostId() {
        return this.hostId;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public String getCountry() {
        return this.country;
    }
    public String getCity() {
        return this.city;
    }

    public String getNeighbourhood() {
        return this.neighbourhood;
    }
    public int getReviews() {
        return this.reviews;
    }
    public double getOverallSatisfaction() {
        return this.overallSatisfaction;
    }
    public int getAccommodates() {
        return this.accommodates;
    }

    public double getBedrooms() {
        return this.bedrooms;
    }

    public double getPrice() {
        return this.price;
    }
    public String getPropertyType() {
        return this.propertyType;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public void setCity(String city) {
        this.city = city;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }


    public void setReviews(int reviews) {
        this.reviews = reviews;
    }


    public void setOverallSatisfaction(double overallSatisfaction) {
        this.overallSatisfaction = overallSatisfaction;
    }


    public void setAccommodates(int accommodates) {
        this.accommodates = accommodates;
    }

    public void setBedrooms(double bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public Acomodacao clone() {
        try {
            Acomodacao clone = (Acomodacao) super.clone();
       
            clone.roomId = this.roomId;
            clone.hostId = this.hostId;
            clone.roomType = this.roomType;
            clone.country = this.country;
            clone.city = this.city;
            clone.neighbourhood = this.neighbourhood;
            clone.reviews = this.reviews;
            clone.overallSatisfaction = this.overallSatisfaction;
            clone.accommodates = this.accommodates;
            clone.bedrooms = this.bedrooms;
            clone.price = this.price;
            clone.propertyType = this.propertyType;
      
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clonagem não suportada, interface 'CloneAble' não implementada ", e);
        }
    }


    // Métodos de Ordenação -----------------------------------------------------------------------------------------

    public static List <Acomodacao> selectionSort(List <Acomodacao>  acomodacaos){
        for (int i = 0; i <= acomodacaos.size() - 1; i++){
            int min_index = i;
            int min_value = 0;
            for (int j = i + 1; j < acomodacaos.size(); j++){ 
                if((min_value = acomodacaos.get(j).getCountry().compareTo(acomodacaos.get(min_index).getCountry()))                    != 0){
                    if(min_value < 0){
                        min_index = j;  
                    }
                }else if ((min_value = acomodacaos.get(j).getCity().compareTo(acomodacaos.get(min_index).getCity()))                   != 0){
                    if(min_value < 0){
                        min_index = j;  
                    }
                }else if ((min_value = acomodacaos.get(j).getNeighbourhood().compareTo(acomodacaos.get(min_index).getNeighbourhood())) != 0){
                    if(min_value < 0){
                        min_index = j;  
                    }
                }else if (acomodacaos.get(j).getRoomId() < acomodacaos.get(min_index).getRoomId()){
                    min_index = j;
                }
            }  

            if(acomodacaos.get(i).getRoomId() != acomodacaos.get(min_index).getRoomId()){
                Acomodacao temp = acomodacaos.get(i);
                acomodacaos.set(i, acomodacaos.get(min_index));
                acomodacaos.set(min_index, temp);
            }
        }
    
        return acomodacaos;
    }

    public static List <Acomodacao> bubbleSort(List <Acomodacao>  acomodacaos) {
        for (int i = 0; i < acomodacaos.size() - 1; i++) {
            for (int j = 0; j < acomodacaos.size() - i - 1; j++) { 
                if (acomodacaos.get(j).getOverallSatisfaction()  > acomodacaos.get(j + 1).getOverallSatisfaction()  || 
                    acomodacaos.get(j).getOverallSatisfaction()  == acomodacaos.get(j + 1).getOverallSatisfaction() &&
                    acomodacaos.get(j).getRoomId()  > acomodacaos.get(j + 1).getRoomId()     ){
                   
                    Acomodacao temp = acomodacaos.get(j);
                    acomodacaos.set(j, acomodacaos.get(j + 1));
                    acomodacaos.set(j + 1, temp);
                }
            }
        }
        return acomodacaos;
    }

   
    public static List<Acomodacao> insertionSort(List<Acomodacao> acomodacoes) {
        for (int i = 1; i < acomodacoes.size(); i++) {
            Acomodacao chave = acomodacoes.get(i);
            int j = i - 1;
            
            while (j >= 0 && acomodacoes.get(j).getAccommodates() > chave.getAccommodates()) {
                acomodacoes.set(j + 1, acomodacoes.get(j));
                j = j - 1;
            }
            
            while (j >= 0 && acomodacoes.get(j).getAccommodates() == chave.getAccommodates() && acomodacoes.get(j).getRoomId() > chave.getRoomId()) {
                acomodacoes.set(j + 1, acomodacoes.get(j));
                j = j - 1;
            }
            
            acomodacoes.set(j + 1, chave);
        }
        return acomodacoes;
    }
    

    public static List<Acomodacao> mergeSort(List<Acomodacao> array, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(array, inicio, meio);
            mergeSort(array, meio + 1, fim);
            intercalar(array, inicio, meio, fim);
        }

        return array;
    }
    
    private static void intercalar(List<Acomodacao> acomodacoes, int inicio, int meio, int fim) {
        List<Acomodacao> arrayTemp = new ArrayList<>(acomodacoes);
    
        int i = inicio;
        int j = meio + 1;
        int k = inicio;
    
        while (i <= meio && j <= fim) {
            if (arrayTemp.get(i).getHostId() < arrayTemp.get(j).getHostId()) {
                acomodacoes.set(k, arrayTemp.get(i));
                i++;
            } else if (arrayTemp.get(i).getHostId() > arrayTemp.get(j).getHostId()) {
                acomodacoes.set(k, arrayTemp.get(j));
                j++;
            } else {
                if (arrayTemp.get(i).getRoomId() < arrayTemp.get(j).getRoomId()) {
                    acomodacoes.set(k, arrayTemp.get(i));
                    i++;
                } else {
                    acomodacoes.set(k, arrayTemp.get(j));
                    j++;
                }
            }
            k++;
        }
    
        while (i <= meio) {
            acomodacoes.set(k, arrayTemp.get(i));
            k++;
            i++;
        }
        
        while (j <= fim) {
            acomodacoes.set(k, arrayTemp.get(j));
            k++;
            j++;
        }
    }
    

    public static List<Acomodacao> quickSort(List<Acomodacao> acomodacoes, int esquerda, int direita) {
        if (esquerda < direita) {
            int indexPivo = separar(acomodacoes, esquerda, direita);
            quickSort(acomodacoes, esquerda, indexPivo - 1);
            quickSort(acomodacoes, indexPivo + 1, direita);
        }

        return acomodacoes;
    }

    private static int separar(List<Acomodacao> acomodacoes, int esquerda, int direita) {
        Acomodacao pivo = acomodacoes.get(esquerda);
        int i = esquerda + 1;
        int j = direita;
        
        while (i <= j) {
            if (compararAcomodacoes(acomodacoes.get(i), pivo) <= 0) {
                i++;
            } else if (compararAcomodacoes(acomodacoes.get(j), pivo) > 0) {
                j--;
            } else {
                Collections.swap(acomodacoes, i, j);
                i++;
                j--;
            }
        }
        Collections.swap(acomodacoes, esquerda, j);
        return j;
    }

    private static int compararAcomodacoes(Acomodacao acomodacao1, Acomodacao acomodacao2) {
        if (acomodacao1.getPrice() != acomodacao2.getPrice()) {
            return Integer.compare((int) acomodacao1.getPrice(), (int) acomodacao2.getPrice());
        } else if (!acomodacao1.getRoomType().equals(acomodacao2.getRoomType())) {
            return acomodacao1.getRoomType().compareTo(acomodacao2.getRoomType());
        } else {
            return Integer.compare(acomodacao1.getRoomId(), acomodacao2.getRoomId());
        }
    }

    public static List<Acomodacao> heapSort(List<Acomodacao> acomodacoes) {
        int n = acomodacoes.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeap(acomodacoes, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Collections.swap(acomodacoes, 0, i);

            maxHeap(acomodacoes, i, 0);
        }

        return acomodacoes;
    }

    public static void maxHeap(List<Acomodacao> acomodacoes, int n, int i) {
        int maior = i; 
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < n && compararAcomodacoesHeap(acomodacoes.get(esquerda), acomodacoes.get(maior)) > 0) {
            maior = esquerda;
        }

        if (direita < n && compararAcomodacoesHeap(acomodacoes.get(direita), acomodacoes.get(maior)) > 0) {
            maior = direita;
        }

        if (maior != i) {
            Collections.swap(acomodacoes, i, maior);

            maxHeap(acomodacoes, n, maior);
        }
    }

    public static int compararAcomodacoesHeap(Acomodacao acomodacao1, Acomodacao acomodacao2) {
        if (acomodacao1.getReviews() != acomodacao2.getReviews()) {
            return Integer.compare(acomodacao1.getReviews(), acomodacao2.getReviews());
        } else {
            return Integer.compare(acomodacao1.getRoomId(), acomodacao2.getRoomId());
        }
    }

}
