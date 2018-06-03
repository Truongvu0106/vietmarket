package edu.hust.truongvu.choviet.model.entity;

/**
 * Created by truon on 5/31/2018.
 */

public class Promotion {
    private int id;
    private String code;
    private long amount;
    private int number;
    private long start;
    private long end;

    public Promotion(int id, String code, long amount, int number, long start, long end) {
        this.id = id;
        this.code = code;
        this.amount = amount;
        this.number = number;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
