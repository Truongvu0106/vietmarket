package edu.hust.truongvu.choviet.entity;

/**
 * Created by truon on 5/3/2018.
 */

public class PriceFilter {
    private long priceFrom;
    private long priceTo;
    private String txtDisPlay;

    public PriceFilter(long priceFrom, long priceTo) {
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    public long getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(long priceFrom) {
        this.priceFrom = priceFrom;
    }

    public long getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(long priceTo) {
        this.priceTo = priceTo;
    }

    public String getTxtDisPlay() {
        return txtDisPlay;
    }

    public void setTxtDisPlay(String txtDisPlay) {
        this.txtDisPlay = txtDisPlay;
    }
}
