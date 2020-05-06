package ru.nsu.fit.DataBase.Controller.Selects;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/select/orders")
    public String startPage(Map<String, Object> model){
        Iterable<Item> it = itemRepo.findByOrderIdIsNotNull();
        model.put("orders", it);

        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);
        model.put("fullPrice", countSum(it));

        return "/select/orders/selectOrder";
    }

    /*@GetMapping("/select/orders/organizationTypeAndDate")
    public String select(@RequestParam String organizationFilter,
                         @RequestParam(required = false) List<String> orgs,
                         @RequestParam(required = false) Date afterDate,
                         @RequestParam(required = false) Date beforeDate,
                         @RequestParam(required = false) List<Integer> orderTypes,
                         Map<String, Object> model){
        Date date = new Date(System.currentTimeMillis());
        model.put("currentDate", date);

        Iterable<Item> it = null;

        if(orderTypes != null) {
            if (orgs != null) {
                if ((afterDate != null) && (beforeDate != null)) {
                    it = itemRepo.findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_AddressIn(orderTypes, afterDate, beforeDate, orgs);
                } else {
                    it = itemRepo.findByOrderId_OrderIdInAndCheck_Organization_AddressIn(orderTypes, orgs);
                }
            } else {
                if ((afterDate != null) && (beforeDate != null)) {
                    if (organizationFilter.equals("all")) {
                        it = itemRepo.findByOrderId_OrderIdInAndCheck_DateBetween(orderTypes, afterDate, beforeDate);
                    }

                    if (organizationFilter.equals("filials")) {
                        it = itemRepo.findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNull(orderTypes,afterDate, beforeDate);
                    }

                    if (organizationFilter.equals("kiosks")) {
                        it = itemRepo.findByOrderId_OrderIdInAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNotNull(orderTypes, afterDate, beforeDate);
                    }
                } else {
                    if (organizationFilter.equals("all")) {
                        it = itemRepo.findByOrderId_OrderIdIn(orderTypes);
                    }

                    if (organizationFilter.equals("filials")) {
                        it = itemRepo.findByOrderId_OrderIdInAndCheck_Organization_BranchOfficeAdressIsNull(orderTypes);
                    }

                    if (organizationFilter.equals("kiosks")) {
                        it = itemRepo.findByOrderId_OrderIdInAndCheck_Organization_BranchOfficeAdressIsNotNull(orderTypes);
                    }
                }
            }
        } else {
            if (orgs != null) {
                if ((afterDate != null) && (beforeDate != null)) {
                    it = itemRepo.findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_AddressIn(afterDate, beforeDate, orgs);
                } else {
                    it = itemRepo.findByOrderIdIsNotNullAndCheck_Organization_AddressIn(orgs);
                }
            } else {
                if ((afterDate != null) && (beforeDate != null)) {
                    if (organizationFilter.equals("all")) {
                        it = itemRepo.findByOrderIdIsNotNullAndCheck_DateBetween(afterDate, beforeDate);
                    }

                    if (organizationFilter.equals("filials")) {
                        it = itemRepo.findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNull(afterDate, beforeDate);
                    }

                    if (organizationFilter.equals("kiosks")) {
                        it = itemRepo.findByOrderIdIsNotNullAndCheck_DateBetweenAndCheck_Organization_BranchOfficeAdressIsNotNull(afterDate, beforeDate);
                    }
                } else {
                    if (organizationFilter.equals("all")) {
                        it = itemRepo.findByOrderIdIsNotNull();
                    }

                    if (organizationFilter.equals("filials")) {
                        it = itemRepo.findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNull();
                    }

                    if (organizationFilter.equals("kiosks")) {
                        it = itemRepo.findByOrderIdIsNotNullAndCheck_Organization_BranchOfficeAdressIsNotNull();
                    }
                }
            }
        }*/

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

    private int countSum(Iterable<Item> it){
        int counting = 0;
        for (Item i: it) {
            counting += i.getOrderPrice();
        }
        return counting;
    }
}
