# language: es
Característica: Cambiar el estado de una incidencia
  Escenario: El operario entra en la aplicación para cerrar una incidencia
    Dado el operario "Pedro" con contraseña "1234" cerrar incidencia
    Cuando el operario hace login correctamente cerrar incidencia
    Y hace click en la opción de cerrar una incidencia, dentro de una incidencia seleccionada
    Entonces el estado de la incidencia pasa a ser cerrado