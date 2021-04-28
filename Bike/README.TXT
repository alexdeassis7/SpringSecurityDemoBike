1) agrega la dependencia siguiente antes de comenzar con el ejercicio 
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>


Resumiendo este ej :

Spring Security protege tu app independientemente de la logica de negocio de tu sistema 
no hemos modificados los rest controller , ni los objetos de negocio 
simplemente agregue la dependencia y por defecto me crea una clave y un user en memoria 

Pero si deseamos usar una base de datos crearemos una clase "SecurityConfig" totalmente independiente donde habilitamos la seguridad web 
y practicamente estoy creando el mecanismo de acceso de donde va a estar la informacion de usuario
claves y roles asi que creamos una tabla user con su correspondiente repository
para poder asi acceder a todos los datos de user mediante su nombre y esto lo utilizo en la implementacion de la interface 
UserDetailService (propia de Spring ) esto me obliga a hacer un override del metodo  "loadUserByUserName()" con eso ya 
Spring secutiry sabe de donde obtener la info de los roles y como hacer el mecanismo de login 
entonces ya no necesitamos levantar user y pass de la memoria le indicamos en el metodo "configure()" de la clase 
SecurityConfig() que tenemos nuestra implementacion de un userDetailService que hemos creado ...


