package practica_7.controladores;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.Javalin;
import practica_7.encapsulaciones.Estudiante;
import practica_7.servicios.EstudianteServices;
import practica_7.util.BaseControlador;
import practica_7.util.NoExisteEstudianteException;

public class ApiControlador extends BaseControlador {

    private EstudianteServices estudianteServices = EstudianteServices.getInstancia();

    public ApiControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {
        app.routes(() -> {
            path("/api", () -> {
                /**
                 * Ejemplo de una API REST, implementando el CRUD
                 * ir a
                 */
                path("/estudiante", () -> {
                    after(ctx -> {
                        ctx.header("Content-Type", "application/json");
                    });

                    get("/", ctx -> {
                        
                        // Unirest.get("http://localhost:7000")
                        //     .asString();

                        ctx.json(estudianteServices.listarEstudiante());
                    });

                    get("/:matricula", ctx -> {
                        ctx.json(estudianteServices.getEstudiantePorMatricula(ctx.pathParam("matricula", Integer.class).get()));
                    });

                    post("/", ctx -> {
                        
                        //parseando la informacion del POJO debe venir en formato json.
                        Estudiante tmp = ctx.bodyAsClass(Estudiante.class);
                        //creando.
                        ctx.json(estudianteServices.crearEstudiante(tmp));

                    });

                    put("/", ctx -> {
                        //parseando la informacion del POJO.
                        Estudiante tmp = ctx.bodyAsClass(Estudiante.class);
                        //creando.
                        ctx.json(estudianteServices.actualizarEstudiante(tmp));

                    });

                    delete("/:matricula", ctx -> {
                        //creando.
                        ctx.json(estudianteServices.eliminandoEstudiante(ctx.pathParam("matricula", Integer.class).get()));
                    });
                });
            });
        });

        app.exception(NoExisteEstudianteException.class, (exception, ctx) -> {
            ctx.status(404);
            ctx.json(""+exception.getLocalizedMessage());
        });
    }
}
