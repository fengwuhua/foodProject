package com.food.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.food.dao.FoodDao;
import com.food.model.Food;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller @Scope("prototype")
public class FoodAction extends ActionSupport{
    @Resource FoodDao foodDao;
    private Food food;
    private List<Food> foodList;
    public String addFood()throws Exception {
        foodDao.addFood(food);
        return "message";
    }
    public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public List<Food> getFoodList(){
		return foodList;
	}
	public void setFoodList(List<Food> foodList){
		this.foodList = foodList;
	}
	public String showFood(){
		foodList=foodDao.QueryAllFoods();
		return "show_view";
	}
	public String showDetail(){
		food=foodDao.getFoodById(food.getFoodid());
		return "detail_view";
	}
	/*显示food的修改项*/
	public String showEdit() throws Exception{
		food=foodDao.getFoodById(food.getFoodid());
		return "edit_view";
	}
	/*编辑food*/
	public String editFood() throws Exception{
		foodDao.updateFood(food);
		return "edit_message";
	}
	/*删除food*/
	public String deleteFood() throws Exception{
		foodDao.deleteFood(food.getFoodid());
		return "delete_message";
	}
}