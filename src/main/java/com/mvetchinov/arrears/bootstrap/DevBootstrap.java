package com.mvetchinov.arrears.bootstrap;

import com.mvetchinov.arrears.model.Customer;
import com.mvetchinov.arrears.model.Deal;
import com.mvetchinov.arrears.model.PDCL;
import com.mvetchinov.arrears.repositories.CustomerRepository;
import com.mvetchinov.arrears.repositories.DealRepository;
import com.mvetchinov.arrears.repositories.PDCLRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CustomerRepository customerRepository;
    private DealRepository dealRepository;
    private PDCLRepository pdclRepository;

    public DevBootstrap(CustomerRepository customerRepository, DealRepository dealRepository, PDCLRepository pdclRepository) {
        this.customerRepository = customerRepository;
        this.dealRepository = dealRepository;
        this.pdclRepository = pdclRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Customer ivanov = new Customer("Иванов");
        Customer petrov = new Customer("Петров");
        customerRepository.save(ivanov);
        customerRepository.save(petrov);

        Deal autoLoan = new Deal("Автокредит");
        Deal mortgage = new Deal("Ипотека");
        Deal cashCredit = new Deal("Кредит наличными");
        dealRepository.save(autoLoan);
        dealRepository.save(mortgage);
        dealRepository.save(cashCredit);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        pdclRepository.save(new PDCL(LocalDate.parse("12.12.2009",formatter),ivanov,autoLoan,"RUR", 12000d));
        pdclRepository.save(new PDCL(LocalDate.parse("25.12.2009",formatter),ivanov,autoLoan,"RUR", 5000d));
        pdclRepository.save(new PDCL(LocalDate.parse("12.12.2009",formatter),ivanov,cashCredit,"RUR", 10000d));
        pdclRepository.save(new PDCL(LocalDate.parse("12.01.2010",formatter),ivanov,autoLoan,"RUR", -10100d));
        pdclRepository.save(new PDCL(LocalDate.parse("20.11.2009",formatter),petrov,mortgage,"RUR", 25000d));
        pdclRepository.save(new PDCL(LocalDate.parse("20.12.2009",formatter),petrov,mortgage,"RUR", 20000d));
        pdclRepository.save(new PDCL(LocalDate.parse("21.12.2009",formatter),petrov,mortgage,"RUR", -25000d));
        pdclRepository.save(new PDCL(LocalDate.parse("29.12.2009",formatter),ivanov,cashCredit,"RUR", -10000d));
    }
}
