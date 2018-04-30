# language: es
Característica: Aniadir un comentario a una incidencia
  Escenario: El operario entra en la aplicación para aniadir un comentario a una incidencia
    Dado el operario "Pedro" con contraseña "1234"
    Cuando el operario hace login correctamente
    Y hace click en la opcion de aniadir comentario una incidencia, dentro de una incidencia seleccionada
    Y crea un comentario
    Entonces aparece el comentario creado en la lista