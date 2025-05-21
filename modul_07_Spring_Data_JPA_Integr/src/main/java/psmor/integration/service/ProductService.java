package psmor.integration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import psmor.integration.dto.*;
import psmor.integration.exceptions.BalanceErrorException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final RestTemplate restTemplate;

    @Value("${integration.url}")
    private String url;

    public ProductResDto selectProductById(Long id){
        return restTemplate.getForObject( url+"/"+id, ProductResDto.class);  // GET
    }

    public BalanceChangeResDto cangeBalance(BalanceChangeReqDto balanceChangeReqDto) /*throws BalanceErrorExc*/ {  // POST
        // Ищем продукт.
        ProductResDto productResDto = selectProductById(balanceChangeReqDto.id());

        // Проверяем на достаточность
        double bal = productResDto.balance()+balanceChangeReqDto.changeSumma();
        if (bal < 0) {
            bal = productResDto.balance();
            throw new BalanceErrorException("Недостаточно средств на счете "+productResDto.account());
            //throw new IllegalArgumentException("Недостаточно средств на счете "+productResDto.account());
        }

        // Меняем остаток
        String resp = restTemplate.postForObject(url+"/balanse", new ProductBalanceDto(balanceChangeReqDto.id(), bal) , String.class);

        if (resp.equals("success") ) {
            System.out.println("resp=false");
            resp  = "error";
        }
        System.out.println("resp=true");

        return new BalanceChangeResDto(resp);
    }
}
