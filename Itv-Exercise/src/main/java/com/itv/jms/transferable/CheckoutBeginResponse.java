package com.itv.jms.transferable;

import com.itv.model.Product;
import com.itv.model.Till;
import com.itv.model.TillUser;
import lombok.*;

import java.util.List;

/**
 * Created by sakibchoudhury on 24/12/17.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckoutBeginResponse {

    private TillUser tillUser;
    private Till till;
    private List<Product> productList;
    private Exception exception;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckoutBeginResponse that = (CheckoutBeginResponse) o;

        if (tillUser != null ? !tillUser.equals(that.tillUser) : that.tillUser != null) return false;
        if (till != null ? !till.equals(that.till) : that.till != null) return false;
        if (productList != null ? !productList.equals(that.productList) : that.productList != null) return false;
        return !(exception != null ? !exception.equals(that.exception) : that.exception != null);

    }

    @Override
    public int hashCode() {
        int result = tillUser != null ? tillUser.hashCode() : 0;
        result = 31 * result + (till != null ? till.hashCode() : 0);
        result = 31 * result + (productList != null ? productList.hashCode() : 0);
        result = 31 * result + (exception != null ? exception.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CheckoutBeginResponse{" +
                "tillUser=" + tillUser +
                ", till=" + till +
                ", productList=" + productList +
                ", exception=" + exception +
                '}';
    }
}
