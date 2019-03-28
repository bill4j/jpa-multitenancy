package cz.tomasdvorak.rest;

import cz.tomasdvorak.exceptions.InvalidCredentialsException;
import cz.tomasdvorak.service.TodoListService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author bill-smith liuwb
 * @version v1.0
 * @Package cz.tomasdvorak.controller
 * @data 2019/3/28 12:43
 * @Copyright @ 2019-2020用友网络科技股份有限公司
 * @email 18232480449@163.com
 * @contract https://github.com/BillCindy
 * @blog https://blog.csdn.net/t131452n?viewmode=contents
 */
@Path("/")
@RequestScoped
public class TodoListRESTService {
    @Inject
    private TodoListService todoListServiceRS;

    @GET
    @Path("/json")
    @Produces("application/json")
    public String getHelloWorldIdJSON() {
        String result = "";
        try {
            result = "{\"result\":\"" + todoListServiceRS.readItems() + "\"}";
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();
        }
        return result;
    }
}
