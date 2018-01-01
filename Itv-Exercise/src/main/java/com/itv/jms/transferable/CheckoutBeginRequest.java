package com.itv.jms.transferable;

import lombok.*;

import java.io.Serializable;

/**
 * Created by sakibchoudhury on 29/12/17.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckoutBeginRequest implements Serializable {
    private static final long serialVersionUID = 1728507887250744119L;

    private long storeNo;
    private int tillNo;
    private String tillUserName;

    public String productReceiverDestination() {
        return storeNo + "_" + tillNo + "_" + tillUserName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckoutBeginRequest that = (CheckoutBeginRequest) o;

        if (storeNo != that.storeNo) return false;
        if (tillNo != that.tillNo) return false;
        return !(tillUserName != null ? !tillUserName.equals(that.tillUserName) : that.tillUserName != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (storeNo ^ (storeNo >>> 32));
        result = 31 * result + tillNo;
        result = 31 * result + (tillUserName != null ? tillUserName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CheckoutBeginRequest{" +
                "storeNo=" + storeNo +
                ", tillNo=" + tillNo +
                ", tillUserName='" + tillUserName + '\'' +
                '}';
    }
}
