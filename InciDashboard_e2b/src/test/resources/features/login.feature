# language: es
  Característica: entrar en la aplicacion
    Escenario: el operario entra en la aplicacion con sus credenciales
      Dado un correo "pedro@example.com" y una contrasena "1234"
      Cuando el operario se logea correctamente
      Entonces visualiza la pagina principal