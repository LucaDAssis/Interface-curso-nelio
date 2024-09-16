package Applicaiton;

import mode.entites.CarRental;
import mode.entites.Vehicle;
import model.servicers.BrazilTaxService;
import model.servicers.RentalService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.US);

        System.out.println("Entre com os dados do aluguel");
        System.out.println("Digite o nome do aluguel");
        String carModel = sc.nextLine();
        System.out.println("Retirada (dd/MM/yyyy hh:mm)");
        LocalDateTime startDate = LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.println("Retorno (dd/MM/yyyy hh:mm)");
        LocalDateTime endDate = LocalDateTime.parse(sc.nextLine(), dtf);

        CarRental cr = new CarRental(startDate, endDate, new Vehicle(carModel));

        System.out.println("Entre com o preço por hora: ");
        double pricePerHour = sc.nextDouble();
        System.out.println("Entre com o preço por dia: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

        rentalService.processInvoice(cr);

        System.out.println("FATURA: ");
        System.out.println("Pagamento basico: " + cr.getInvoice().getBasicPayment());
        System.out.println("Impost: " + cr.getInvoice().getTax());
        System.out.println("Pagamento Total: " + cr.getInvoice().getTotalPayment());

        sc.close();

    }
}
