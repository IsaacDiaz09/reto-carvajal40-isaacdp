# Reto Carvajal 4.0 - Isaac Diaz Perez
## Realizado en Vuejs3 (TS) y Spring Boot

En este repositorio se encuentran tanto BackEnd como FrontEnd en sus respectivas carpetas "/api" y "/client".

Aplicacion de muestra de productos y gestion de un "WishList" o lista de deseos, asi mismo se implementa Paginacion
de los datos, sort y autenticacion. Para la persistencia de los datos se utiliza **PostgreSQL** con ayuda de
Hibernate y JPA. El despliege se realizó en **Firebase Hosting** y **Heroku**.

## Requisitos

- [Node.js](https://nodejs.org/) v10+ es requerido para el funcionamiento de Vue-Cli4x
- Vuejs 3.22 o superior requerido para Composition Api y <script setup>
- Java JDK 11
- Gradle para bajar las dependencias

## Demostracion:

![Login (11)](https://user-images.githubusercontent.com/84527725/155910397-18342b02-94f8-4739-8413-e9d993ab53a6.png)
![Productos (12)](https://user-images.githubusercontent.com/84527725/155910436-f534f17f-46dd-4fb9-9043-3c54debaa068.png)
![Agregar a lista deseos (13)](https://user-images.githubusercontent.com/84527725/155910440-4b839011-149b-4bd9-a210-a3bb51000d49.png)
![Limpiar lista deseos (14)](https://user-images.githubusercontent.com/84527725/155910446-79766bb9-e2ee-44c6-8619-a1b6159da5cf.png)
![Historico (15)](https://user-images.githubusercontent.com/84527725/155910453-06ac7c78-ad19-47d5-b765-acb0464c4d48.png)
  
### Setup BackEnd
```
cd api/
mvn package -Dmaven.test.skip
cd target
java -jar file.jar
```

### Setup Front
```
cd client/
npm run serve
```
#### Nota:
> En el proyecto de Vue se implementa autenticacion con Firebase Auth, si se desea ejecutar
  el proyecto en un entorno local se deben establecer esos valores en: "src/core/firebase.ts"
## Produccion

La aplicacion ya esta deplegada y es accesible en la url:

```sh
https://reto-carvajal.web.app/
```
O
  ```sh
https://reto-carvajal.firebaseapp.com/
```

## Importante!!
Usa el siguiente usuario para poder iniciar sesion: 
- jhondoe@mail.com
- super-secret

## Licencia

MIT
