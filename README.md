## Descripción del proyecto

Es la resolución del **Challenge - Conversor de Moneda** utilizando Java dentro de la especialización como desarrollador Back-End. Reto de la formación **Oracle Next Education (ONE)** de **Oracle + Alura LATAM**.

Este código en Java consulta una API de tasas de cambio, procesa la respuesta JSON y permite al usuario interactuar mediante un menú en la consola, validando la entrada del usuario para seleccionar opciones.

## Obtención de clave API

Para obtener tu clave, es necesario realizar un registro inicial ingresando tu correo electrónico en la página oficial de [Exchange Rate API](https://www.exchangerate-api.com/). Después de esto, recibirás una clave en el correo electrónico proporcionado y estará lista para su uso.

> ⚠️ Colocar la clave del API en el archivo **`config_ejemplo.properties`** en el campo **`api_key`**. Finalmente, modificar el nombre del archivo por **`config.properties`** para su correcto funcionamiento.

## Estructura del proyecto

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.