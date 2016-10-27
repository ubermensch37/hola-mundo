package mx.com.nmp.cloud.test.web.rest;

import mx.com.nmp.cloud.test.domain.Saludo;
import mx.com.nmp.cloud.test.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author osanchez
 */
@Controller
@RequestMapping("/hola")
public class HolaMundoController {

    private static final Logger log = LoggerFactory.getLogger(HolaMundoController.class);

    private static final String plantilla = "Hola, %s!";
    private final AtomicLong contador = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Saludo atenderHola(
            @RequestParam(value = "nombre", required = false, defaultValue = "Desconocido") String nombre) {
        log.info(">> atenderHola({})", nombre);
        return new Saludo(contador.incrementAndGet(), String.format(plantilla, nombre));
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Saludo> crearEntidad(@RequestBody Saludo saludo) throws URISyntaxException {
        log.info(">> crearEntidad({})", saludo);

        if (saludo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("saludo", "idexistente", "Una nueva entidad no debe tener ID asignado")).body(null);
        }

        Saludo result = new Saludo(contador.incrementAndGet(), saludo.getContenido());

        return ResponseEntity.created(new URI("/hola/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("saludo", result.getId().toString()))
                .body(result);
    }
}
