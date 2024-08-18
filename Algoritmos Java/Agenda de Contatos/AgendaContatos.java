import java.util.NoSuchElementException;
import java.util.Scanner;

public class AgendaContatos {

    public static class Contato implements Comparable<Contato> {
        private String nome;
        private String telefone;
        private String email;
        private String endereco;

        public Contato(String nome, String telefone, String email, String endereco) {
            this.nome     = nome;  
            this.telefone = telefone;
            this.email    = email;
            this.endereco = endereco;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public int compareTo(Contato item) {
            return this.nome.compareTo(item.nome);
        }

        @Override
        public String toString() {
            String nomeFormatado = nome.substring(0, 1).toUpperCase() + nome.substring(1);
            return "Nome: "      + nomeFormatado + "\n" +
                   "Telefone:  " + telefone + "\n" +
                   "E-mail:  "   + email + "\n" +
                   "Endereco:  " + endereco;
        }
    }

    public static class No<T extends Comparable<T>> {
        private T item;
        private No<T> direita;
        private No<T> esquerda;
        private int altura;

        public No(T item) {
            this.item = item;
            this.altura = 1;
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

        public int getAltura() {
            return altura;
        }

        public void setAltura(int altura) {
            this.altura = altura;
        }
    }

    public static class AVL<T extends Comparable<T>> {
        private No<T> raiz;

        public AVL() {
            this.raiz = null;
        }

        public boolean vazia() {
            return this.raiz == null;
        }

        public void inserir(T item) {
            this.raiz = inserir(this.raiz, item);
        }

        private No<T> inserir(No<T> no, T item) {
            if (no == null) {
                return new No<>(item);
            }

            int comparacao = item.compareTo(no.getItem());
            if (comparacao < 0) {
                no.setEsquerda(inserir(no.getEsquerda(), item));
            } else if (comparacao > 0) {
                no.setDireita(inserir(no.getDireita(), item));
            } else {
                throw new RuntimeException("Contato já foi previamente registrado.");
            }

            no = balancear(no);
            return no;
        }

        public T pesquisar(T item) {
            No<T> no = pesquisar(this.raiz, item);
            if (no == null) {
                throw new NoSuchElementException("Contato inexistente!");
            }
            return no.getItem();
        }

        private No<T> pesquisar(No<T> no, T item) {
            if (no == null) {
                return null;
            }

            int comparacao = item.compareTo(no.getItem());
            if (comparacao == 0) {
                return no;
            } else if (comparacao < 0) {
                return pesquisar(no.getEsquerda(), item);
            } else {
                return pesquisar(no.getDireita(), item);
            }
        }

        public void exibicaoOrdenada() {
            exibicaoOrdenada(this.raiz);
        }

        private void exibicaoOrdenada(No<T> no) {
            if (no != null) {
                exibicaoOrdenada(no.getEsquerda());
                System.out.println(no.getItem());
                exibicaoOrdenada(no.getDireita());
            }
        }

        private No<T> balancear(No<T> no) {
            if (no == null) {
                return null;
            }

            no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));
            int fatorBalanceamento = fatorBalanceamento(no);

            if (fatorBalanceamento > 1) {
                if (fatorBalanceamento(no.getEsquerda()) < 0) {
                    no.setEsquerda(rotacionarEsquerda(no.getEsquerda()));
                }
                return rotacionarDireita(no);
            }

            if (fatorBalanceamento < -1) {
                if (fatorBalanceamento(no.getDireita()) > 0) {
                    no.setDireita(rotacionarDireita(no.getDireita()));
                }
                return rotacionarEsquerda(no);
            }

            return no;
        }

        private int altura(No<T> no) {
            return no == null ? 0 : no.getAltura();
        }

        private int fatorBalanceamento(No<T> no) {
            return no == null ? 0 : altura(no.getEsquerda()) - altura(no.getDireita());
        }

        private No<T> rotacionarDireita(No<T> currentNodo) {
            No<T> child = currentNodo.getEsquerda();
            No<T> T2 = child.getDireita();

            child.setDireita(currentNodo);
            currentNodo.setEsquerda(T2);

            currentNodo.setAltura(Math.max(altura(currentNodo.getEsquerda()), altura(currentNodo.getDireita())) + 1);
            child.setAltura(Math.max(altura(child.getEsquerda()), altura(child.getDireita())) + 1);

            return child;
        }

        private No<T> rotacionarEsquerda(No<T> currentNodo) {
            No<T> child = currentNodo.getDireita();
            No<T> T2 = child.getEsquerda();

            child.setEsquerda(currentNodo);
            currentNodo.setDireita(T2);

            currentNodo.setAltura(Math.max(altura(currentNodo.getEsquerda()), altura(currentNodo.getDireita())) + 1);
            child.setAltura(Math.max(altura(child.getEsquerda()), altura(child.getDireita())) + 1);

            return child;
        }
    }

    public static class Agenda {
        private AVL<Contato>[] tabela;

        @SuppressWarnings("unchecked")
        public Agenda() {
            tabela = new AVL[26];
            for (int i = 0; i < tabela.length; i++) {
                tabela[i] = new AVL<>();
            }
        }

        public void inserir(Contato contato) {
            int indice = hash(contato.getNome());
            tabela[indice].inserir(contato);
        }

        public Contato pesquisar(String nome) {
            String nomeFormatado = nome.toLowerCase();
            int indice = hash(nomeFormatado);
            return tabela[indice].pesquisar(new Contato(nomeFormatado, "", "", ""));
        }

        public void exibicaoOrdenada() {
            for (int i = 0; i < tabela.length; i++) {
                char letra = (char) ('a' + i);
                if (!tabela[i].vazia()) {
                    System.out.println(Character.toUpperCase(letra));
                    tabela[i].exibicaoOrdenada();
                }
            }
        }

        private int hash(String nome) {
            if(!isPalavra(nome)){
                throw new IllegalArgumentException("O nome informado não é válido.");
            }

            char c = Character.toLowerCase(nome.charAt(0));

            return c - 'a';
        }
    }

    public static boolean isPalavra(String string) {
        // Verifica se a string não é nula e se é composta apenas por letras
        return string != null && string.matches("[a-zA-Z]+");
    }

    public static void main(String[] args) {
        Scanner scanner   = new Scanner(System.in);
        Agenda  agenda    = new Agenda();
        String  linha     = scanner.nextLine();
        Boolean hasInputs = true;
        Boolean allContactsHasBeenRegistered = false;

        // Leitura da Entrada Padrão
         while (hasInputs){
            if ((!linha.equals("FIM")) && allContactsHasBeenRegistered == false){
                String[] dados = linha.split(", ");

                if (dados.length != 4) {
                    System.out.println("Corrija os parâmetros de entrada, input inválido.");
                    continue;
                }

                Contato contato = new Contato(dados[0].toLowerCase(), dados[1], dados[2], dados[3]);

                try {
                    agenda.inserir(contato);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }else{

                allContactsHasBeenRegistered = true;

                if((!linha.equals("FIM")) && isPalavra(linha)){
                    String nome = linha;

                    try {
                        Contato contato = agenda.pesquisar(nome);
                        if(!contato.equals(null)){
                            System.out.println(contato);
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("Contato não encontrado");
                    }
                }
            }

            if(scanner.hasNext()){
               linha = scanner.nextLine();
            }else{
                hasInputs = false;
            }
        }

        // Impressão em ordem dos contatos
        System.out.println("Contatos na agenda:");

        agenda.exibicaoOrdenada();

        scanner.close();
    }
}
