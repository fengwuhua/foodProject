package com.food.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.food.model.Food;

@Service @Transactional
public class FoodDao {
	@Resource SessionFactory factory;
	/*添加Food信息*/
	public void addFood(Food food) throws Exception{
		Session s= factory.getCurrentSession();
		s.save(food);
	}
	/*删除Food信息*/
	 public void deleteFood (int foodid) throws Exception {
	     Session s = factory.getCurrentSession(); 
	     Object food = s.load(Food.class, foodid);
	     s.delete(food);
	    }
	 /*更新Food信息*/
	    public void updateFood(Food food) throws Exception {
	        Session s = factory.getCurrentSession();
	        s.update(food);
	    }
	    /*根据主键获取对象*/
	    public Food getFoodById(int foodid) {
	        Session s = factory.getCurrentSession();
	        Food food = (Food)s.get(Food.class, foodid);
	        return food;
	    }
		@Transactional(propagation=Propagation.NOT_SUPPORTED)
	    public ArrayList<Food> QueryAllFoods() {
	        Session s = factory.getCurrentSession();
	        String hql = "From Food";
	        Query q = s.createQuery(hql);
	        List foodList = q.list();
	        return (ArrayList<Food>) foodList;
	    }
		public ArrayList<Food> QueryFoods(String foodName) { 
	    	Session s = factory.getCurrentSession();
	    	String hql = "From Food food where 1=1";
	    	if(!foodName.equals("")) hql = hql + " and food.foodName like '%" + foodName + "%'";
	    	                                         //food在此指代Food,要注意小写，记得一定要加%
	    	Query q = s.createQuery(hql);
	    	List foodList = q.list();
	    	return (ArrayList<Food>) foodList;
	    }
}
