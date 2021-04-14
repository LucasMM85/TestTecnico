# TestTecnico
Mantenimiento de superhéroes

Prueba técnica

El proyecto cumple con los puntos básicos requeridos:

* Consultar todos los súper héroes.
* Consultar un único súper héroe por id.
* Consultar todos los súper héroes que contienen, en su nombre, el valor de un parámetro enviado en la petición. Por ejemplo, si enviamos “man” devolverá “Spiderman”, “Superman”, “Manolito el fuerte”, etc.
* Modificar un súper héroe.
* Eliminar un súper héroe.
* Test unitarios de algún servicio.
* Los súper héroes se deben guardar en una base de datos H2 en memoria.

Además se incluyen las mejoras:

* Implementar una anotación personalizada que sirva para medir cuánto tarda en ejecutarse una petición. Se podría anotar alguno o todos los métodos de la API con esa anotación. Funcionaría de forma similar al @Timed de Spring, pero imprimiendo la duración en un log.
* Gestión centralizada de excepciones.
* Test de integración.
* Poder cachear peticiones.
* Documentación de la API.
* Seguridad del API.
