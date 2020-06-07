package ru.nsu.fit.DataBase.NewDomains;

public class GoodsAndCompany {
    String nameOfGoods;
    String company;
    int count;

    public GoodsAndCompany(String nameOfGoods, String company, int count) {
        this.nameOfGoods = nameOfGoods;
        this.company = company;
        this.count = count;
    }

    public String getNameOfGoods() {
        return nameOfGoods;
    }

    public void setNameOfGoods(String nameOfGoods) {
        this.nameOfGoods = nameOfGoods;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
