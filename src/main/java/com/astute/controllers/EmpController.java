package com.astute.controllers;   
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.astute.beans.Emp;
import com.astute.dao.EmpDao;


@Controller  
public class EmpController {  
    @Autowired  
    EmpDao dao;
    //Emp constructor 
    public EmpController() {
		System.out.println("Empcontroller");
	}
    // display the view data and bean class data we can bind the data by using @model attribute
    //bind the data will be storing request scop or model scope or model map scope
    //by default method is get
    @RequestMapping("/")
    public ModelAndView showform(){  
        return new ModelAndView("empform","emp",new Emp());  
    }  
   //aftere getting entity class data we can store the data base by using save method
    //this controller intaract with dao call or business class or service class
    //@model attribute we can bind the data emp class object
    @RequestMapping(value="/save",method = RequestMethod.POST)    
    public String save(@ModelAttribute("emp") Emp emp){    
        dao.save(emp);    
        return "redirect:/viewemp";  
        // after storing values in a d.b this values take list and display
    } 
    //based on id we can delete the id
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)    
    public String delete(@PathVariable int id){    
        dao.delete(id);    
        return "redirect:/viewemp";    
    }     
   // we can edit the data.means form page will open 
     @RequestMapping(value="/editsave",method = RequestMethod.POST)    
    public String editsave(@ModelAttribute("emp") Emp emp){    
        dao.update(emp);    
        return "redirect:/viewemp";    
    }
    
   //update the data based on id no change the id we modify any field  
    @RequestMapping(value="/editemp/{id}")    
    public String edit(@PathVariable int id, Model m){    
        Emp emp=dao.getEmpById(id);    
        m.addAttribute("emp",emp);  
        return "empeditform";    
    }
    // after strong d.b values take list we can display
    //after taking list object data we can put the data in a any scope
    //like a m.add attribute by default request scope
    @RequestMapping("/viewemp")    
    public String viewemp(Model m){    
        List<Emp> list=dao.getEmployees();  
        @SuppressWarnings("rawtypes")
		Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
           System.out.println(iterator.next());
        }
        m.addAttribute("list",list);
        return "viewemp"; 
   
}
}
