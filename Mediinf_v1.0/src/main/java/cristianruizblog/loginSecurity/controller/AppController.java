package cristianruizblog.loginSecurity.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.inezpre5.modelo.Usuario;

import cristianruizblog.loginSecurity.entity.User;
import cristianruizblog.loginSecurity.repository.UserRepository;



@Controller
public class AppController {

	@Autowired
	private UserRepository uc;
	
	@GetMapping({"/","/login"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public String admin(ModelMap mp) {
		mp.put("users", uc.findAll());
		return "admin";
	}
	
	/*@RequestMapping(value="/admin", method = RequestMethod.GET)
    public String listaUsuarios(ModelMap mp){
        mp.put("usuarios", uc.findAll());
        return "admin";
    }*/
	
	@GetMapping({"/CrearUser"})
	public String CrearUser() {
		return "CrearUser"; 
	}
	
	@RequestMapping(value="/crear", method=RequestMethod.POST)
    public String crear(@Valid User usuario,
            BindingResult bindingResult, ModelMap mp){
        if(bindingResult.hasErrors()){
            return "/CrearUser";
        }else{
            uc.save(usuario);
            mp.put("usuario", usuario);
            return "/login";
        }
    }
    /*
	Debes crear un controlador que este fuera de este metodo ya que estas rutas exigen que estes logeado
	prueba las rutas una vez ya logeado...Solucionado desde websecurity.
	*/
}
