package model;

import entities.*;
import java.util.*;

public class ProductModel {

	public List<Product>  findAll(){
		List<Product> listProducts = new ArrayList<Product>();
		listProducts.add(new Product("p1","name1",100,2));
		listProducts.add(new Product("p2","name2",200,3));
		listProducts.add(new Product("p3","name3",300,4));
		listProducts.add(new Product("p4","name4",400,5));
		listProducts.add(new Product("p5","name5",500,6));
		return listProducts;
	}
	
	
}
