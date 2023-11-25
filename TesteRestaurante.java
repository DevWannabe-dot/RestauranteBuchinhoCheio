package com.buchinhocheio.restaurante;

import java.util.Iterator;
import java.util.List;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class TesteRestaurante {
    /* Constantes */
    private static final int OPCAO_NEUTRA = 203;    // Impede que a inserção do "0 para cancelar" possa encerrar o programa
    private static final int TAMANHO_LINHA_MENU = 50;
    private static final int TAMANHO_LINHA_MESAS = 44 + 2; // Tamanho da string + número formatado em 2 dígitos
    /* Variáveis de teste */
    private static Cardapio cardapioRestaurante = new Cardapio();

    public static void main(String[] args) {
        System.out.println("Sucesso na compilação.");

        /* Início do funcionamento */
        Restaurante meuRestaurante = new Restaurante("Buchinho Cheio", "Av. Pres. Antônio Carlos, 6627");
        Scanner esc = new Scanner(System.in);
        String leitura;
        int opcao = 0;
        boolean continuar = false;

        do {
            restaurante_imprimirMenuAdmin(meuRestaurante);
            opcao = esc.nextInt(); esc.nextLine(); // Retirar o \n

            switch(opcao){
                case 0:
                    System.out.print("Confirmar saída? (S/n) ");
                    leitura = esc.nextLine();
                    opcao = ((leitura.charAt(0) == 'S' || leitura.charAt(0) == 's') ? 0 : OPCAO_NEUTRA);
                break;
                case 1:
                    List<Mesa> mesasCopy = meuRestaurante.getMesas();
                    mesasCopy.add(new Mesa(mesasCopy.size() + 1));
                    meuRestaurante.setMesas(mesasCopy);

                    System.out.println(String.format("<Nova mesa %d criada.>", mesasCopy.get(mesasCopy.size()-1).getNumeroMesa()));
                break;
                case 2:
                    restaurante_listarMesas(meuRestaurante);

                    System.out.print("Digite o número da mesa (0 para cancelar): ");
                    opcao = esc.nextInt();
                    if(opcao > 0) restaurante_detalharMesaN(meuRestaurante, opcao);// opcao = OPCAO_NEUTRA;
                break;
                case 3:
                    restaurante_listarMesas(meuRestaurante);
                        
                    System.out.print("Qual o número da mesa onde deseja colocá-l@(s) (0 para cancelar)? ");
                    opcao = esc.nextInt(); esc.nextLine(); // Retirar o \n

                    do{           
                        if(!(meuRestaurante.getMesas().get(opcao - 1).isReserva()) && opcao > 0 && opcao <= meuRestaurante.getMesas().size()) {
                            Cliente novoCliente = new Cliente();
                            List<Cliente> clientes = meuRestaurante.getMesas().get(opcao - 1).getClientes();
                            if(clientes == null) clientes = new ArrayList<Cliente>();
                        
                            System.out.print("Insira o nome do seu cliente: ");
                            novoCliente.setNome(esc.nextLine());
                            System.out.print("Insira o email do seu cliente: ");
                            novoCliente.setEmail(esc.nextLine());
                        
                            clientes.add(novoCliente);
                            meuRestaurante.getMesas().get(opcao - 1).setClientes(clientes);
                        } else {
                            System.out.print("<Mesa indisponível.>");
                            break;
                        }
                    
                        System.out.print("Adicionar mais clientes (S/n)? ");
                        leitura = esc.nextLine();
                        continuar = (leitura.charAt(0) == 'S' || leitura.charAt(0) == 's');
                    } while (continuar);

                    if(!(meuRestaurante.getMesas().get(opcao - 1).isReserva()) && opcao > 0 && opcao <= meuRestaurante.getMesas().size()){
                        meuRestaurante.getMesas().get(opcao - 1).reservar(true);
                    }
                    // a partir de agora, a mesa está reservada (ou não)
                    if(opcao == 0) opcao = OPCAO_NEUTRA;
                break;
                case 4:
                    cardapioRestaurante.imprimeCardapio();

                    System.out.print("(1) Adicionar\n" + "(2) Remover\n" + ">> ");
                    boolean remover = (esc.nextInt() == 2 ? true : false); esc.nextLine(); // Retirar o \n

                    if(remover){
                        System.out.print("Digite o nome do item a remover: ");
                        
                        cardapioRestaurante.removeItem(esc.nextLine());
                    } else {
                        System.out.print("Digite o nome do item: ");
                        String k = esc.nextLine();
                        System.out.print("Digite o valor do item: ");
                        Number v = esc.nextDouble();

                        cardapioRestaurante.adicionaItem(k, v);
                    }
                break;
                case 5:
                    cardapioRestaurante.imprimeCardapio();
                break;
                default:
                    System.out.println("<Erro na leitura da opção.>");
                break;
            }
        } while (opcao != 0);
        
        
        esc.close();
    }
    public static void restaurante_listarMesas(Restaurante r) {
        Iterator<Mesa> it = r.getMesas().iterator();
        
        util_escreverCaracteresln('*', TAMANHO_LINHA_MESAS);
        while(it.hasNext()) {
            Mesa mesaAtual = it.next();
                       
            System.out.printf("%s | Mesa de número %s, %s\n", mesaAtual.getData(), new DecimalFormat("00").format(mesaAtual.getNumeroMesa()), (mesaAtual.isReserva() ? "está reservada": "está vazia"));
        }
        util_escreverCaracteresln('*', TAMANHO_LINHA_MESAS);
    }
    public static void restaurante_imprimirMenuAdmin(Restaurante r) {
        System.out.println("");
        util_escreverCaracteresln('-', TAMANHO_LINHA_MENU);
        System.out.println("Bem-vind@ ao seu restaurante " + r.getNome() +
                                "\nSituado em " + r.getEndereco());
        util_escreverCaracteresln('-', TAMANHO_LINHA_MENU);
        System.out.print("\tSelecione sua opção:\n" +
                        "Mesas\n" +
                        "\t(1) Nova mesa\n" +
                        "\t(2) Ir até mesa\n" +
                        "\t(3) Atender cliente\n" +

                        "Restaurante\n" +
                        "\t(4) Editar cardápio\n" +
                        "\t(5) Ver cardápio\n" +

                        "\n\t(0) Log out\n" +
                        ">> ");
    }
    public static void restaurante_detalharMesaN(Restaurante r, int n) {
        Iterator<Mesa> it_m = r.getMesas().iterator();
        Mesa mesaEncontrada = null;

        while(it_m.hasNext()) {
            Mesa mesaAtual = it_m.next();

            mesaEncontrada = mesaAtual.getNumeroMesa() == n ? mesaAtual : null;
            if(mesaEncontrada != null) break;
        }

        if(mesaEncontrada != null) {
            util_escreverCaracteresln('*', TAMANHO_LINHA_MESAS);

            System.out.printf("%s | Mesa de número %s, %s\n", mesaEncontrada.getData(), new DecimalFormat("00").format(mesaEncontrada.getNumeroMesa()), (mesaEncontrada.isReserva() ? "está reservada": "está vazia"));
            
            System.out.println("\tClientes: ");   
            if(mesaEncontrada.getClientes() != null) mesa_listarClientes(mesaEncontrada);
            System.out.println("\tComanda: ");
            if(mesaEncontrada.getComanda() != null) mesaEncontrada.getComanda().listarConsumo();

            if(mesaEncontrada.isReserva()) {
                Scanner esc_comanda = new Scanner(System.in);
                boolean ehMesa;
                String continuar;
                int opcao;

                restaurante_imprimirMenuMesa();
                System.out.print(">> ");
                opcao = esc_comanda.nextInt(); esc_comanda.nextLine(); // Retirar o \n

                switch (opcao) {
                    case 0:
                    break;
                    case 1:
                        System.out.printf("(%d) mesa\n", mesaEncontrada.getNumeroMesa());
                        for(Cliente cliente : mesaEncontrada.getClientes()) {
                            System.out.printf("(%s) %s\n", cliente.getEmail(), cliente.getNome());
                        }
                        System.out.print("A quem pertencerá esta comanda? ");
                        String donoDaComanda = esc_comanda.nextLine();
                        ehMesa = (donoDaComanda.charAt(0) == (NumberFormat.getInstance().format(mesaEncontrada.getNumeroMesa()).charAt(0)));
                        // Busca o primeiro dígito da entrada no primeiro dígito do número da mesa, permitindo a leitura em String tanto para cliente quanto para mesa

                        do{
                            cardapioRestaurante.imprimeCardapio();

                            if(ehMesa){
                                System.out.print("Item: ");
                                String consumo = esc_comanda.nextLine();
                                System.out.print("Quantidade: ");
                                int qtd = esc_comanda.nextInt(); esc_comanda.nextLine(); // Retirar o \n

                                Comanda c = mesaEncontrada.getComanda();
                                String tmp = c.getConsumo();
                                c.setConsumo(tmp != null ? tmp.concat(consumo) : consumo);
                                c.setValor(c.getValor() + (qtd * cardapioRestaurante.cardapio_acharItem(consumo)));
                                
                                mesaEncontrada.setComanda(c);
                            } else {
                                System.out.print("Item: ");
                                String consumo = esc_comanda.nextLine();
                                System.out.print("Quantidade: ");
                                int qtd = esc_comanda.nextInt(); esc_comanda.nextLine(); // Retirar o \n

                                Comanda c = mesaEncontrada.getComanda();
                                String tmp = c.getConsumo();
                                c.setConsumo(tmp.concat(consumo));
                                c.setValor(c.getValor() + (qtd * cardapioRestaurante.cardapio_acharItem(consumo)));
                                
                                mesaEncontrada.setComanda(c);
                            }

                            System.out.print("Mais itens (S/n)? ");
                            continuar = esc_comanda.nextLine();

                        } while(continuar.startsWith("S") || continuar.startsWith("s"));
                    break;
                    case 2:
                    break;
                    case 3:
                    break;
                    case 4:
                    break;
                    default:
                        System.out.println("<Erro na leitura da opção.>");
                    break;
                }
                
                esc_comanda.close();
            }
        }
    }
    public static void restaurante_imprimirMenuMesa() {
        System.out.println("(1) Anotar comanda\n" +
                            "(2) Listar consumo\n" + 
                            "(3) Calcular 10%\n" +
                            "(4) Dividir conta\n" +
                            "(0) Cancelar");
    }
    public static void mesa_listarClientes(Mesa m) {
        Iterator<Cliente> it_c = m.getClientes().iterator();

        while(it_c.hasNext()) {
            Cliente clienteAtual = it_c.next();
            System.out.printf("\t\t%s (%s) %s\n", clienteAtual.getNome(), clienteAtual.getEmail(), (clienteAtual.getComanda().getValor() > 0 ? "fez pedido(s)." : "não fez pedidos."));
        }
    }
    public static void util_escreverCaracteresln(char caracter, int tam) {
        while (tam != 0){
            System.out.print(caracter);
            --tam;
        }
        System.out.println("");
    }
}