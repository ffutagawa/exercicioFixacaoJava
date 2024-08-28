package br.com.ffutagawa.application;

import entities.Employee;
import entities.OutsourcedEmployee;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        char cha = 'n';

        do {
            List<Employee> list = new ArrayList<>();

            int qtd = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.print("Qual a quantidade de funcionarios? ");
                    qtd = sc.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                    sc.next(); // Limpa a entrada incorreta
                }
            }

            for (int i = 1; i <= qtd; i++) {
                System.out.println("Dados do Funcionario: " + i);

                char ch = ' ';
                validInput = false;
                while (!validInput) {
                    try {
                        System.out.print("Tercerizado? (y/n): ");
                        ch = sc.next().toLowerCase().charAt(0);
                        if (ch == 'y' || ch == 'n') {
                            validInput = true;
                        } else {
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira 'y' ou 'n'.");
                        sc.next(); // Limpa a entrada incorreta
                    }
                }

                System.out.print("Nome: ");
                sc.nextLine(); // Consumir a nova linha pendente
                String nome = sc.nextLine();

                int hours = 0;
                validInput = false;
                while (!validInput) {
                    try {
                        System.out.print("Horas: ");
                        hours = sc.nextInt();
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                        sc.next(); // Limpa a entrada incorreta
                    }
                }

                double porHora = 0.0;
                validInput = false;
                while (!validInput) {
                    try {
                        System.out.print("Valor por hora: ");
                        porHora = sc.nextDouble();
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número decimal.");
                        sc.next(); // Limpa a entrada incorreta
                    }
                }

                if (ch == 'y') {
                    double adicional = 0.0;
                    validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("Informe o adicional: ");
                            adicional = sc.nextDouble();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, insira um número decimal.");
                            sc.next(); // Limpa a entrada incorreta
                        }
                    }

                    Employee emp = new OutsourcedEmployee(nome, hours, porHora, adicional);
                    list.add(emp);
                } else {
                    Employee emp = new Employee(nome, hours, porHora);
                    list.add(emp);
                }
            }

            System.out.println();
            System.out.println("Pagamentos");
            for (Employee emp : list) {
                System.out.println(emp.getName() + " - $ " + String.format("%.2f", emp.payment()));
            }

            validInput = false;
            while (!validInput) {
                try {
                    System.out.print("Deseja repetir? (y/n): ");
                    cha = sc.next().toLowerCase().charAt(0);
                    if (cha == 'y' || cha == 'n') {
                        validInput = true;
                    } else {
                        throw new InputMismatchException();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira 'y' ou 'n'.");
                }
            }

        } while (cha == 'y');

        sc.close();
    }
}
