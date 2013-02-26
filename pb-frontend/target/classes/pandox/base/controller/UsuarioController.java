package pandox.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pandox.base.config.AppConfig;


@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController extends BaseController {
    
    private static Logger log = Logger.getLogger(UsuarioController.class);

    // @Autowired
    // private AdministratorFacadeService adminFacadeService;
    //
    // @Autowired
    // private UserUtil userUtil;
    
    public UsuarioController() {
        log.info("INICIANDO USUARIO CONTROLLER");
    }

    @RequestMapping(value = "")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        return load();
    }

    private ModelAndView load() {
        ModelAndView mv = new ModelAndView("index");
        // mv.addObject("listAdmin", adminFacadeService.findAll());

        return mv;

    }

    // @RequestMapping(value = "/", method = RequestMethod.POST)
    // public ModelAndView save(AdministratorVO admin, HttpServletRequest
    // request, HttpServletResponse response) {
    // Map<String, Object> vars = new HashMap<String, Object>();
    //
    // admin.getAdministrator().setUsuario(userUtil.getAdministratorLogin());
    // admin.getAdministrator().setIp(getRemoteIp());
    // adminFacadeService.create(admin);
    // vars.put("sucess", getMessage("msg.operation.sucess"));
    //
    // ModelAndView mv = load();
    // mv.addAllObjects(vars);
    // return mv;
    // }
    //
    // @RequestMapping(value = "/", method = RequestMethod.PUT)
    // public ModelAndView update(AdministratorVO admin, HttpServletRequest
    // request, HttpServletResponse response) {
    // Map<String, Object> vars = new HashMap<String, Object>();
    //
    // admin.getAdministrator().setUserUpdate(userUtil.getAdministratorLogin());
    // admin.getAdministrator().setIp(getRemoteIp());
    // adminFacadeService.update(admin);
    // vars.put("sucess", getMessage("msg.operation.sucess"));
    //
    // ModelAndView mv = load();
    // mv.addAllObjects(vars);
    // return mv;
    // }

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionWebHandler(Exception exception, HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> vars = new HashMap<String, Object>();

        vars.put("error", exception.getCause().getMessage());

        ModelAndView mv = load();
        mv.addAllObjects(vars);

        return mv;
    }

}
