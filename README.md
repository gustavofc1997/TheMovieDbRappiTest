# TheMovieDbRappiTest


### Capas de la aplicación

#### Persistencia 
##### Usé Room para la persistencia de los datos teniendo de forma que hay 2 Clase que reprentan las entidades de la base de datos las cuales son Movie y Trailer asociados a estos encontramos los DAO que son los que nos permiten manejar las operaciones a la base de datos,

#### Negocio
##### Se cuenta con un manager llamado MovieRepository que es el encargado de retornar la lista de peliculas para mostrar, que lo que hace es consultar a la base de datos local y hacer request al api, cuando este de respuesta le notifica a los respectivas clases DAO para guardar las respuestas y poder funcionar offline

#### Red
##### Implementación de Retrofit para las peticiones web hay un interface llamada TheMovieDBApi que contiene los metodos requeridos para hacer las consultas al API, en esta parte tambien cabe mencionar que están los POJO que permiten manejar las respuestas de las peticiones para este caso MoviesResponse y TrailersResponse

#### Vistas
##### Esta capa comprende las actividades y fragmentos utilizados para la visualizacion de los datos en la cual encontramos la actividad principal que contiene fragmentos para la visualizacion de la lista de peliculas por categorias, tambien esta la activida del detalle de la pelicula que es accedido desde la pantalla principal cuando se selecciona un item , tambien se tiene un adapter que es el mecanismo intermedio para poder pintar las respectivas peliculas en una grilla 

### 1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?

##### Este principio establece que si tenemos 2 razones para cambiar para una clase, tenemos que dividir la funcionalidad en dos clases,  Cada clase manejará solo una responsabilidad y si en el futuro necesitamos hacer un cambio, lo haremos en la clase que lo maneje Cuando necesitamos hacer un cambio en una clase que tiene más responsabilidades, el cambio puede afectar la otra funcionalidad relacionada con la otra responsabilidad de la clase.
  
##### El principio de responsabilidad única representa una buena manera de identificar clases durante la fase de diseño de una aplicación y promueve a pensar en todas las formas en que una clase puede evolucionar. Una buena separación de responsabilidades se realiza solo cuando se comprende bien el panorama completo de cómo debería funcionar la aplicación.

### 2. Qué características tiene, según su opinión, un “buen” código o código limpio?

###### Al escribir código debemos tener en cuenta que no es nuestro y que no somos los unicos en usarlo por ende debe ser tan claro que cualquiera que llegue a leerlo debe estar en la capacidad de entenderlo

###### Nuestro código debe ser limpio y enfocado, para esto debemos tener en cuenta algunos principios como el SRP para mantener las clases con una sola responsabilidad de forma que no se afecte lo demas y otro principio como el DRY para evitar generar proyectos extensos y con evitar lidiar con la complejidad

###### Código minimo con esto hago referencia a que se deben mantener los metodos y clases cortas de forma que cumplan con su objetivo inicial sin convertirse en algo complejo de forma que facilite la lectura 

###### Un buen código usa convenciones significativas 
