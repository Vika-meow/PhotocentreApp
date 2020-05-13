package ru.nsu.fit.DataBase.Controller.Selects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.DataBase.Domain.Item;
import ru.nsu.fit.DataBase.Domain.OrderPrice;
import ru.nsu.fit.DataBase.Domain.Organization;
import ru.nsu.fit.DataBase.Repos.ItemRepo;
import ru.nsu.fit.DataBase.Repos.OrderPriceRepo;
import ru.nsu.fit.DataBase.Repos.OrganizationRepo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OdrerSelect {
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    OrganizationRepo organizationRepo;
    @Autowired
    OrderPriceRepo orderPriceRepo;

    String currentMethodName = "findByOrderIdIsNotNull";
    String currentSortName;
    String currentDirection = "asc";
    Class[] currentParamsArray;
    Object[] currentArgsArray;
    List<Class> currentParams = new ArrayList<>();
    List<Object> currentArgs = new ArrayList<>();

    @GetMapping("/select/orders")
    public String startPage(Map<String, Object> model){
        Iterable<Item> it = itemRepo.findByOrderIdIsNotNull();
        model.put("orders", it);

        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);
        model.put("fullPrice", countSum(it));

        return "/select/orders/selectOrder";
    }

    @GetMapping("/select/orders/organizationTypeAndDate")
    public String select(@RequestParam String organizationFilter,
            @RequestParam(required = false) List<String> orgs,
            @RequestParam(required = false) Date afterDate,
            @RequestParam(required = false) Date beforeDate,
            @RequestParam(required = false) List<Integer> orderTypes,
            Map<String, Object> model){
        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);

        Iterable<Item> it = null;

        List<Object> args = new ArrayList<>();
        List<Class> params = new ArrayList<>();

        String methodName = "findBy";
        if (orderTypes!= null){
            methodName = methodName.concat("OrderId_OrderIdIn");
            args.add(orderTypes);
            params.add(List.class);
        } else {
            methodName = methodName.concat("OrderIdIsNotNull");
        }

        if((afterDate != null) && (beforeDate != null)){
            methodName = methodName.concat("AndCheck_DateBetween");
            args.add(afterDate);
            params.add(Date.class);
            args.add(beforeDate);
            params.add(Date.class);
        }

        if(orgs != null){
            methodName = methodName.concat("AndCheck_Organization_AddressIn");
            args.add(orgs);
            params.add(List.class);
        } else {
            if(organizationFilter.equals("filials")){
                methodName = methodName.concat("AndCheck_Organization_BranchOfficeAdressIsNull");
            }
            if(organizationFilter.equals("kiosks")){
                methodName = methodName.concat("AndCheck_Organization_BranchOfficeAdressIsNotNull");
            }
        }

        Class[] paramsArray = new Class[params.size()];
        int number = 0;
        for(Class i : params){
            paramsArray[number] = i;
            number++;
        }

        Class c = itemRepo.getClass();
        Method method = null;
        try {
                method = c.getDeclaredMethod(methodName, paramsArray);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Object[] argsArray = new Object[args.size()];
        number = 0;
        for (Object i : args){
            argsArray[number] = i;
            number++;
        }
        try {
                it = (List<Item>) method.invoke(itemRepo, argsArray);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        currentParams = params;
        currentArgs = args;
        currentMethodName = methodName;
        currentParamsArray = paramsArray;
        currentArgsArray = argsArray;

        model.put("orders", it);
        model.put("fullPrice", countSum(it));
        return "/select/orders/selectOrder";
    }

    @GetMapping("/select/orders/addDates")
    public String addDates(Map<String, Object> model){
        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);
        return "/select/orders/addDates";
    }
    @GetMapping("/select/orders/removeDates")
    public String removeDates(){
        return "/select/orders/removeDates";
    }

    @GetMapping("/select/orders/addOrganizations")
    public String addOrganizations(Map<String, Object> model){
        Iterable<Organization> it = organizationRepo.findAll();
        model.put("organizations", it);
        return "/select/orders/addOrganizations";
    }
    @GetMapping("/select/orders/removeOrganizations")
    public String removeOrganizations(){
        return "/select/orders/removeOrganizations";
    }

    @GetMapping("/select/orders/putOrganizations")
    public String putOrganizations(String typeOfOrganizations, Map<String, Object> model){
        if(typeOfOrganizations.equals("all")){
            Iterable<Organization> it = organizationRepo.findAll();
            model.put("organizations", it);
        }

        if(typeOfOrganizations.equals("filials")){
            Iterable<Organization> it = organizationRepo.findByBranchOfficeAdressIsNull();
            model.put("organizations", it);
        }

        if(typeOfOrganizations.equals("kiosks")){
            Iterable<Organization> it = organizationRepo.findByBranchOfficeAdressIsNotNull();
            model.put("organizations", it);
        }
        return "/select/orders/putOrganizations";
    }

    @GetMapping("/select/orders/addOrderType")
    public String addOrderType(Map<String, Object> model){
        Iterable<OrderPrice> it = orderPriceRepo.findAll();
        model.put("orderList", it);
        return "/select/orders/addOrderType";
    }
    @GetMapping("/select/orders/removeOrderType")
    public String removeOrderType(Map<String, Object> model){
        Iterable<OrderPrice> it = orderPriceRepo.findAll();
        model.put("orderList", it);
        return "/select/orders/removeOrderType";
    }

    @GetMapping("/select/orders/orderBy")
    public String orderBy(String orderBy, Map<String, Object> model){
        List<Item> it = null;

        String methodName;
        methodName = currentMethodName;

        List<Class> params = new ArrayList<>(currentParams);
        List<Object> args = new ArrayList<>(currentArgs);

        params.add(Sort.class);
        if(orderBy.equals(currentSortName) && (currentDirection.equals("asc"))) {
                args.add(Sort.by(Sort.Direction.DESC, orderBy));
                currentDirection = "desc";
        } else {
            args.add(Sort.by(Sort.Direction.ASC, orderBy));
            currentDirection = "asc";
        }

        currentSortName = orderBy;

        Class[] paramsArray = new Class[params.size()];
        int number = 0;
        for(Class i : params){
            paramsArray[number] = i;
            number++;
        }

        Object[] argsArray = new Object[args.size()];
        number = 0;
        for (Object i : args){
            argsArray[number] = i;
            number++;
        }

        Class c = itemRepo.getClass();
        Method method = null;
        try {
            method = c.getDeclaredMethod(methodName, paramsArray);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            it = (List<Item>) method.invoke(itemRepo, argsArray);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        model.put("orders", it);
        model.put("fullPrice", countSum(it));

        return "/select/orders/tableOfSelects";
    }

    private int countSum(Iterable<Item> it){
        int counting = 0;
        for (Item i: it) {
            counting += i.getOrderPrice();
        }
        return counting;
    }
}
