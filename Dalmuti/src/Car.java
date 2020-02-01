// 얕은 복사, 깊은 복사 이해를 위해 만든 임시 클래스
public class Car implements Cloneable {
	private String brand;
    private int price;
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
 
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}