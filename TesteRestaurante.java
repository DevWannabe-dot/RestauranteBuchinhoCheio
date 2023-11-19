package com.buchinhocheio.restaurante;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class TesteRestaurante {
    public static final int OPCAO_NEUTRA = 203;
    public static void main(String[] args) {
        System.out.println("Sucesso na compilação.");

        /* Início do funcionamento */
        Scanner esc = new Scanner(System.in);
        Restaurante meuRestaurante = new Restaurante("Buchinho Cheio", "Av. Pres. Antônio Carlos, 6627");
        int opcao = 0;

        do {
            restaurante_imprimirMenuAdmin(meuRestaurante);
            opcao = esc.nextInt();
            esc.nextLine(); // Retirar o \n

            switch(opcao){
                case 0:
                    System.out.print("Confirmar saída? (S/N)\n" +
                                        ">> ");
                    opcao = esc.nextLine().charAt(0) == 'S' ? 0 : OPCAO_NEUTRA;
                break;
                case 1:
                    List<Mesa> mesasCopy = meuRestaurante.getMesas();
                    mesasCopy.add(new Mesa(mesasCopy.size() + 1));
                    meuRestaurante.setMesas(mesasCopy);
                break;
                case 2:
                    restaurante_listarMesas(meuRestaurante);

                    System.out.print("Digite o número da mesa a detalhar (0 para parar): ");
                    opcao = esc.nextInt();
                    if(opcao > 0) restaurante_detalharMesaN(meuRestaurante, opcao); opcao = OPCAO_NEUTRA;
                break;
                case 3:
                    util_escreverCaracteresln('*', 42);
                    restaurante_listarMesas(meuRestaurante);
                    util_escreverCaracteresln('*', 42);

                    System.out.println("Qual o número da mesa onde deseja colocá-l@(s) (0 para parar)? ");
                    opcao = esc.nextInt();
                    esc.nextLine();

                    while(opcao > 0 && opcao <= meuRestaurante.getMesas().size()) {                     
                        if(!(meuRestaurante.getMesas().get(opcao - 1).isReserva())) {
                            Cliente novoCliente = new Cliente();
                            List<Cliente> clientes = meuRestaurante.getMesas().get(opcao - 1).getClientes();

                            System.out.print("Insira o nome do seu cliente: ");
                            novoCliente.setNome(esc.nextLine());
                            System.out.print("Insira o email do seu cliente: ");
                            novoCliente.setEmail(esc.nextLine());

                            if(clientes == null) clientes = new ArrayList<Cliente>();
                            clientes.add(novoCliente);

                            meuRestaurante.getMesas().get(opcao - 1).setClientes(clientes);
                        } else {
                            System.out.println("<Mesa indisponível.>");
                            break;
                        }

                        System.out.print("Adicionar mais clientes (0 para parar)? ");
                        boolean continuar = esc.nextInt() != 0;
                        esc.nextLine();
                        if(!continuar) break;
                    }

                    if(opcao > 0 && opcao <= meuRestaurante.getMesas().size()) meuRestaurante.getMesas().get(opcao - 1).reservar(true);
                    // a partir de agora, a mesa está reservada (ou não)
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

        while(it.hasNext()) {
            Mesa mesaAtual = it.next();
                       
            System.out.println(String.format("%s | Mesa de número %d, %s", mesaAtual.getData(), mesaAtual.getNumeroMesa(), (mesaAtual.isReserva() ? "está reservada": "está vazia")));
        }
    }
    public static void restaurante_imprimirMenuAdmin(Restaurante r) {
        util_escreverCaracteresln('-', 50);
        System.out.println("Bem-vind@ ao seu restaurante " + r.getNome() +
                                "\nSituado em " + r.getEndereco());
        util_escreverCaracteresln('-', 50);
        System.out.print("Selecione sua opção:\n" +
                        "(1) Construir mesa\n" +
                        "(2) Detalhar mesa\n" +
                        "(3) Atender cliente\n" +
                        "(0) Log out\n" +
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
            util_escreverCaracteresln('*', 42);
            System.out.println(String.format("%s | Mesa de número %d, %s", mesaEncontrada.getData(), mesaEncontrada.getNumeroMesa(), (mesaEncontrada.isReserva() ? "está reservada": "está vazia")));
            
            System.out.println("\tClientes: ");   
            if(mesaEncontrada.getClientes() != null) {
                Iterator<Cliente> it_c = mesaEncontrada.getClientes().iterator();

                
                while(it_c.hasNext()) {
                    Cliente clienteAtual = it_c.next();

                    System.out.println(String.format("\t\t%s (%s)", clienteAtual.getNome(), clienteAtual.getEmail()) + (clienteAtual.getComandasDoCliente().size() != 0 ? " fez pedido(s)." : " não fez pedidos."));
                }
            }
            System.out.println("\tComandas: ");
            if(mesaEncontrada.getComandasDaMesa() != null) {
                for(Map.Entry<Mesa, Comanda> entry : mesaEncontrada.getComandasDaMesa().entrySet()) {
                    System.out.println(entry);
                }
            }
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