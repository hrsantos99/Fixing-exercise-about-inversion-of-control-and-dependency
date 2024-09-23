package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;
import services.PicPayService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Enter with the contract data:");
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), fmt);
		System.out.print("Contract value: ");
		double contractValue = sc.nextDouble();
		
		Contract contract = new Contract(number, date, contractValue);
		
		System.out.println();
		System.out.print("Enter with the number of installments: ");
		int numberInstallments = sc.nextInt();
		
		ContractService contractService = null;
		System.out.print("Enter with the contract service (1 = PicPay or 2 = PayPal): ");
		int cont = sc.nextInt();
		
		if(cont == 1) {
			contractService = new ContractService(new PicPayService());
		} else if(cont == 2) {
			contractService = new ContractService(new PaypalService());
		} else {
			System.out.println("Invalid contract service!");
		}
		
		contractService.processContract(contract, numberInstallments);
		
		System.out.println();
		System.out.println("Installments: ");
		for(Installment installment : contract.getInstallments()) {
			System.out.println(installment);
		}
		
		sc.close();
		
	}

}
