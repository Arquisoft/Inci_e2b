# language: es
Característica: Comprobar los campos peligrosos de una incidencia
  Escenario: El operario entra en la aplicación y ve que hay peligro en una incidencia por lo que quiere mirar en detalle que es lo que produce el peligro
    Dado el operario "Pedro" con contraseña "1234" comprobar campos
    Cuando el operario hace login correctamente comprobar campos
    Y hace click en el boton de peligro
    Entonces puede visualizar los campos peligrosos