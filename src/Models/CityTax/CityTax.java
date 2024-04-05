package Models.CityTax;

public final class CityTax {
    protected String city;
    protected String country;
    protected double tax_price_per_night;

    public CityTax(String city, String country, double tax_price_per_night){
        this.city = city;
        this.country = country;
        this.tax_price_per_night = tax_price_per_night;
    }

    public double getTax_price_per_night(){
        return tax_price_per_night;
    }

    public void setTax_price_per_night(double tax_price_per_night){
        this.tax_price_per_night = tax_price_per_night;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    @Override
    public String toString() {
        return "CityTax{" + "\n" +
                "City='" + city + '\'' + "\n" +
                ", Country='" + country + '\'' + "\n" +
                ", Tax Price per Night=" + tax_price_per_night + "\n" +
                '}';
    }
}
