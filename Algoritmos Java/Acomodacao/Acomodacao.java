package Acomodacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        readFile();
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
                        

    // gets, sets, clone, ler e imprimir.
    };

    public static void readFile(){
        Scanner scan = new Scanner (System.in);
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
                if(!linha.isEmpty()){
                    String[] parametros =  linha.split("\t");
                    Acomodacao registro = new Acomodacao(Integer.parseInt(parametros[0].trim()), Integer.parseInt(parametros[1].trim()), parametros[2].trim(), parametros[3].trim(), parametros[4].trim(), parametros[5].trim(),
                                Integer.parseInt(parametros[6].trim()), Double.parseDouble(parametros[7].trim()), Integer.parseInt(parametros[8].trim()), Double.parseDouble(parametros[9].trim()),
                                Double.parseDouble(parametros[10].trim()), parametros[11].trim());
                    acomodacoes.add(registro);
                }
            }

            while(!(linha = scan.nextLine()).equals("FIM")){
                if(!linha.isEmpty()){
                    for (Acomodacao acomodacao : acomodacoes) {
                        if (acomodacao.roomId == Integer.parseInt(linha)){
                            printLine(acomodacao);
                            break;
                        }
                    }
                }
            }



            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
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

}
