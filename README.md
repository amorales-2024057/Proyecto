# KinalApp - Sistema de Ventas

##  Descripción del Proyecto

KinalApp es una aplicación web desarrollada con Spring Boot que simula un sistema integral de ventas de artículos. La plataforma permite gestionar clientes, productos, ventas, usuarios y generar facturas electrónicas, ofreciendo una experiencia completa para la administración de un negocio comercial.

##  Tecnologías Utilizadas

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| **Java** | 21 | Lenguaje de programación principal |
| **Spring Boot** | 4.0.2 | Framework para desarrollo de aplicaciones empresariales |
| **Spring MVC** | - | Patrón Modelo-Vista-Controlador |
| **Spring Data JPA** | - | Persistencia de datos y ORM |
| **Thymeleaf** | - | Motor de plantillas para vistas HTML |
| **MySQL** | - | Sistema Gestor de Base de Datos |
| **Maven** | - | Gestor de dependencias y construcción |
| **Bootstrap 5** | 5.1.3 | Framework CSS para diseño responsive |
| **jQuery** | 3.6.0 | Biblioteca JavaScript |
| **DataTables** | 1.11.5 | Plugin para tablas dinámicas |
| **Chart.js** | - | Gráficos interactivos |
| **Font Awesome** | 6.0.0 | Iconos vectoriales |

##  Estructura del Proyecto

```
src/
└── main/
├── java/com/andersonmorales/kinalapp/
│   ├── controller/
│   │   ├── AuthController.java
│   │   ├── ClienteController.java
│   │   ├── DetalleVentaController.java
│   │   ├── ProductosController.java
│   │   ├── UsuarioController.java
│   │   ├── VentasController.java
│   │   └── web/
│   │       └── HomeController.java
│   ├── entity/
│   │   ├── Cliente.java
│   │   ├── DetalleVenta.java
│   │   ├── Productos.java
│   │   ├── Usuario.java
│   │   └── Ventas.java
│   ├── repository/
│   │   ├── ClienteRepository.java
│   │   ├── DetalleVentasRepository.java
│   │   ├── ProductosRepository.java
│   │   ├── UsuarioRepository.java
│   │   └── VentasRepository.java
│   └── service/
│       ├── IClienteService.java
│       ├── IDetalleVentasService.java
│       ├── IProductosService.java
│       ├── IUsuarioService.java
│       ├── IVentasService.java
│       ├── ClienteService.java
│       ├── DetalleVentaService.java
│       ├── ProductosService.java
│       ├── UsuarioService.java
│       └── VentasService.java
└── resources/
├── application.properties
└── templates/
├── dashboard.html
├── login.html
└── registro.html
```
##  Requisitos Previos

Antes de ejecutar la aplicación, asegúrese de tener instalado:

-  **JDK 21** o superior
-  **Maven** (versión 3.6+)
-  **MySQL** (versión 8.0+)
-  Navegador web actualizado (Chrome, Firefox, Edge)

##  Configuración de la Base de Datos

1. **Iniciar el servicio MySQL**
```
git clone https://github.com/tu-usuario/kinalapp.git
```

2. **Ingresar a la carpeta del proyecto** 
```
cd kinalapp
```
3. **Compilar el proyecto con Mave**
```
mvn clean install
```
4. **Ejecutar la aplicación**
```
mvn spring-boot:run
```
La aplicación estará disponible en: `http://localhost:7075`

## Endpoints Disponibles
|Entidad| Método  |	Endpoint|
|---------------|---------|-----------------------------|
|Cliente| 	GET	   |/clientes|
|Cliente| 	GET	   |/clientes/{dpi}|
|Cliente| 	GET	   |/clientes/estado/{estado}|
|Cliente| 	POST   |	/clientes|
|Cliente| 	PUT    | 	/clientes/{dpi} |
|Cliente| 	DELETE |	/clientes/{dpi}
|Producto| 	GET	   |/productos
|Producto| 	GET	   |/productos/{codigo}
|Producto|	GET|	/productos/estado/{estado}
|Producto|	POST|	/productos
|Producto|	PUT|	/productos/{codigo}
|Producto|	DELETE|	/productos/{codigo}
|Venta|	GET|	/ventas
|Venta|	GET|	/ventas/{codigo}
|Venta|	GET|	/ventas/estado/{estado}
|Venta|	POST|	/ventas
|Venta|	PUT|	/ventas/{codigo}
|DetalleVenta|	GET|	/detalleVentas
|DetalleVenta|	GET|/detalleVentas/{codigo}
|DetalleVenta|	POST|	/detalleVentas
|DetalleVenta|	PUT|	/detalleVentas/{codigo}
|Usuario|	GET|	/usuarios
|Usuario|	GET|	/usuarios/{codigo}
|Usuario|	GET	|/usuarios/estado/{estado}
|Usuario|	POST|	/usuarios
|Usuario|	PUT|	/usuarios/{codigo}
|Usuario|	DELETE|	/usuarios/{codigo}

#### Autor

**Anderson Javier Morales Lobos** — `amorales-2024057@kinal.edu.gt`