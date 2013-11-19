package chapter4and5;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class PreLoader
{
   private final FutureTask<ProductInfo> future=new FutureTask<ProductInfo>(new Callable<ProductInfo>()
		   {
	            public ProductInfo call() 
	            {
	            	 return loadProductInfo();
	            }
	            private ProductInfo loadProductInfo()
	            {
	            	ProductInfo result=new ProductInfo();
	            	for (int i=1; i<=10000;i++)
	            	{
	            		String id=Integer.valueOf(i).toString();
	            		String name="苹果"+Integer.valueOf(i);
	            		String code="apple"+id;
	            		float  price=4999;
	            		Product p=new Product(id,name,code,price);
	            		result.storeProduct(p);
	            	}
	            	return result;
	            	
	            }
		   });
		   private final Thread t=new Thread(future);
		   public void start()
		   {
			  t.start();
		   }
		   public ProductInfo get() throws InterruptedException, ExecutionException
		   {
		      return this.future.get();
		   }
		   public static void main(String[] args) throws InterruptedException, ExecutionException
		   {
			   PreLoader p=new PreLoader();
			   p.start();
			   ProductInfo pInfo=p.get();
			   System.out.println(pInfo.getProduct("1").toString());
			   System.out.println(pInfo.getProduct("10000").toString());
		   }
		   
 ///////////////////////////////内部类///////////////////////////////////////////////////////	   
   static class ProductInfo
   {
	   private final Map<String,Product> products=new ConcurrentHashMap<String, Product>();
	   public Product getProduct(String id)
	   {
		   return this.products.get(id);
	   }
	   public void storeProduct(Product product)
	   {
		   this.products.put(product.getId(), product);
	   }
	   public Product removeProduct(String id)
	   {
		   return this.products.remove(id);
	   }
   }
   static class Product
   {
	   private String id=null;
	   public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	private String name=null;
	   private String code=null;
	   private float price=0;
	   public Product(String id,String name,String code,float price)
	   {
		   this.id=id;
		   this.name=name;
		   this.code=code;
		   this.price=price;
	   }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", code=" + code
				+ ", price=" + price + "]";
	}
   }
}
