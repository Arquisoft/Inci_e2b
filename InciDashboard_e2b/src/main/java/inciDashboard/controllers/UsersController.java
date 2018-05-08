package inciDashboard.controllers;

import inciDashboard.entities.Comentario;
import inciDashboard.entities.InciStatus;
import inciDashboard.entities.Incidencia;
import inciDashboard.entities.User;
import inciDashboard.services.CommentsService;
import inciDashboard.services.IncidenciasService;
import inciDashboard.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UsersController {

    @Autowired
    private IncidenciasService incidenciasService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private UsersService usersService;
	
	/*@Autowired
	private KafkaProducer kafka;*/

    public List<SseEmitter> emitters = Collections.synchronizedList(new ArrayList<SseEmitter>());

    @PersistenceContext
    EntityManager entityManager;

    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        return new User("Pepe", "pepe@example.com");
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "redirect:/";
    }

    @RequestMapping("/user/listIncidencias")
    public String getListadoIncidencias(Model model, Principal principal) {
        List<Incidencia> incidencias = incidenciasService.getIncidenciasByUser(usersService.getUserByEmail(principal.getName()));
        incidencias.stream().forEach(f -> System.out.println(f));
        incidencias = incidencias.stream().filter(f -> f.getEstado() != InciStatus.CERRADA && f.getCaducidad().before(new Date())).collect(Collectors.toList());
        List<Incidencia> incidenciasUn = incidenciasService.getIncidenciasByUser(null);
        model.addAttribute("incidenciasListUnsigned", incidenciasUn);
        model.addAttribute("incidenciasList", incidencias);

        return "user/listIncidencias";
    }

    @RequestMapping("/user/updateIncidencias")
    public String update(Model model, Principal principal) {
        List<Incidencia> incidenciasUn = incidenciasService.getIncidenciasByUser(null);
        model.addAttribute("incidenciasListUnsigned", incidenciasUn);
        return "user/listIncidencias  :: tableListIncidenciasUn";
    }

    @RequestMapping("/user/stats")
    public String stats(Model model, Principal principal) {
        long id = usersService.getUserByEmail(principal.getName()).getId();

        Query q = entityManager.createNativeQuery("SELECT MONTH(`caducidad`) AS MonthNumber, COUNT(1) AS Occurances FROM incidencia GROUP BY MONTH(`caducidad`)");
        Query q2 = entityManager.createNativeQuery("SELECT MONTH(`caducidad`) AS MonthNumber, COUNT(1) AS Occurances FROM incidencia  where `user_id`= " +id+
                " GROUP BY MONTH(`caducidad`)");

        List<Object[]> r = q.getResultList();
        Map<Integer, BigInteger> m = new HashMap();
        r.forEach(c -> m.put((Integer) c[0], (BigInteger) c[1]));
        List<BigInteger> p = new ArrayList<>(15);
        for (int i = 1; i <= 12; i++) {
            if (m.containsKey(i)) {
                p.add(m.get(i));
            } else {
                p.add(BigInteger.ZERO);
            }
        }

        model.addAttribute("data", p.toArray());


        List<Object[]> r2 = q2.getResultList();
        Map<Integer, BigInteger> m2 = new HashMap();
        r2.forEach(c -> m2.put((Integer) c[0], (BigInteger) c[1]));
        List<BigInteger> p2 = new ArrayList<>(15);
        for (int i = 1; i <= 12; i++) {
            if (m2.containsKey(i)) {
                p2.add(m2.get(i));
            } else {
                p2.add(BigInteger.ZERO);

            }
        }
        model.addAttribute("mydata", p2.toArray());


        return "user/stats";

    }

    @RequestMapping("/user/listComments/{idIncidencia}")
    public String getListadoComentarios(Model model, Principal principal, @PathVariable Long idIncidencia) {
        model.addAttribute("commentsList",
                commentsService.getCommentsByIncidencia(incidenciasService.getIncidencia(idIncidencia)));
        model.addAttribute("idIncidencia", idIncidencia);
        return "user/listComments";
    }

    @RequestMapping("/user/showMap/{latitud}/{longitud}")
    public String getMap(Model model, @PathVariable double latitud, @PathVariable double longitud) {
        model.addAttribute("latitud", latitud);
        model.addAttribute("longitud", longitud);
        return "coordenadas/map";
    }

    @RequestMapping(value = "/user/addComment/{idIncidencia}", method = RequestMethod.POST)
    public String addComment(Model model, @ModelAttribute Comentario comentario, @PathVariable Long idIncidencia) {
        commentsService
                .addComentario(new Comentario(comentario.getTexto(), incidenciasService.getIncidencia(idIncidencia)));
        return "redirect:/user/listComments/{idIncidencia}";
    }

    @RequestMapping(value = "/user/addComment/{idIncidencia}")
    public String getPublication(Model model, @PathVariable Long idIncidencia) {
        model.addAttribute("idIncidencia", idIncidencia);
        model.addAttribute("comentario", new Comentario());
        model.addAttribute("commentsList", commentsService.getComentarios());
        return "user/addComment";
    }

    @RequestMapping(value = "/user/changeStatus/{idIncidencia}")
    public String setStatus(Model model, @PathVariable Long idIncidencia) {
        model.addAttribute("idIncidencia", idIncidencia);

        Map<String, String> estados = new HashMap<String, String>();
        estados.put("Abierta", InciStatus.ABIERTA.name());
        estados.put("Cerrada", InciStatus.CERRADA.name());
        estados.put("Anulada", InciStatus.ANULADA.name());
        estados.put("En proceso", InciStatus.ENPROCESO.name());

        model.addAttribute("estadosList", estados.values());

        return "user/changeStatus";
    }

    @RequestMapping(value = "/user/changeStatus/{idIncidencia}", method = RequestMethod.POST)
    public String getStatus(@PathVariable Long idIncidencia, InciStatus estado) {
        Incidencia original = incidenciasService.getIncidencia(idIncidencia);
        original.setEstado(estado);
        incidenciasService.addIndicencia(original);

        //kafka.updateStatus(String.valueOf(idIncidencia), estado.toString());

        return "redirect:/user/listIncidencias";
    }

    @CrossOrigin(origins = "http://localhost:8090")
    @RequestMapping("/emitter")
    public SseEmitter getEmitter() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));

        return emitter;
    }

    public void sendData(SseEmitter.SseEventBuilder event) {
        synchronized (emitters) {
            for (SseEmitter em : emitters) {
                try {
                    em.send(event);
                } catch (IOException e) {
                    em = new SseEmitter(Long.MAX_VALUE);
                }
            }
        }
    }

}