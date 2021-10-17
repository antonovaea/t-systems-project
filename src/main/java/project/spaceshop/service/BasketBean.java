package project.spaceshop.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import project.spaceshop.dto.BasketProductDto;

import java.util.ArrayList;
import java.util.List;

@Component("basketBean")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BasketBean {

    private List<BasketProductDto> basket = new ArrayList<>();

    public List<BasketProductDto> getBasket() {
        return basket;
    }

    public void setBasket(List<BasketProductDto> basket) {
        this.basket = basket;
    }

}
