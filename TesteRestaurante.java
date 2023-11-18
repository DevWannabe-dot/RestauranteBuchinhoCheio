package com.buchinhocheio.restaurante;

import java.util.Iterator;
import java.util.List;
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
                    Iterator<Mesa> it = meuRestaurante.getMesas().iterator();

                    while(it.hasNext()) {
                        Mesa mesaAtual = it.next();
                        
                        System.out.println(String.format("%s | Mesa de número %d", mesaAtual.getData(), mesaAtual.getNumeroMesa()));
                    }

                    System.out.print("Digite o número da mesa a detalhar (0 para parar): ");
                    opcao = esc.nextInt();
                    if(opcao > 0) restaurante_detalharMesaN(meuRestaurante, opcao); opcao = OPCAO_NEUTRA;
                break;
                default:
                    System.out.println("<Erro na leitura da opção.>");
                break;
            }
        } while (opcao != 0);
        
        
        esc.close();
    }
    public static void restaurante_imprimirMenuAdmin(Restaurante r) {
        util_escreverCaracteresln('-', 50);
        System.out.println("Bem-vind@ ao seu restaurante " + r.getNome() +
                                "\nSituado em " + r.getEndereco());
        util_escreverCaracteresln('-', 50);
        System.out.print("Selecione sua opção:\n" +
                        "(1) Construir mesa\n" +
                        "(2) Listar mesas\n" +
                        "(0) Log out\n" +
                        ">> ");
    }
    public static void restaurante_detalharMesaN(Restaurante r, int n) {
        Iterator<Mesa> it_m = r.getMesas().iterator();
        Mesa mesaEncontrada = null;

        do {
            mesaEncontrada = it_m.next();
        } while(it_m.hasNext() && it_m.next().getNumeroMesa() != n);

        if(mesaEncontrada != null) {
            util_escreverCaracteresln('*', 30);
            System.out.println(String.format("%s | Mesa de número %d", mesaEncontrada.getData(), mesaEncontrada.getNumeroMesa()));
            System.out.println("Clientes: ");

            if(mesaEncontrada.getClientes() != null){
                Iterator<Cliente> it_c = mesaEncontrada.getClientes().iterator();

                
                while(it_c.hasNext()) {
                    Cliente clienteAtual = it_c.next();

                    System.out.print(String.format("%s (%s)", clienteAtual.getNome(), clienteAtual.getEmail()) + (clienteAtual.getComandasDoCliente().size() != 0 ? " fez pedido(s)." : " não fez pedido."));
                
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