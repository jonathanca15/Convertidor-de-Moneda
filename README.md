El proyecto está diseñado para ser interactivo, permitiendo al usuario realizar múltiples conversiones de moneda dentro de la misma ejecución del programa.
La captura y manejo de errores está integrada para manejar posibles fallos de conexión a la API o errores en el procesamiento de la respuesta JSON. 
**El flujo de la aplicación es el siguiente:

--El usuario introduce la cantidad de dinero que desea convertir desde Pesos Mexicanos (MXN) a otra moneda.
--El usuario especifica la moneda de destino a la cual quiere convertir su cantidad, por ejemplo, USD (Dólar estadounidense), EUR (Euro), GBP (Libra esterlina), JPY (Yen japonés), etc.
--La aplicación realiza una solicitud HTTP GET a la API de ExchangeRate-API utilizando la URL construida con el API Key y la moneda base MXN.
--La respuesta JSON obtenida se analiza para verificar si la solicitud fue exitosa.
    -Si la respuesta es exitosa, se extraen las tasas de conversión de la respuesta JSON para la moneda de destino especificada por el usuario.
    -Se realiza el cálculo de la cantidad convertida multiplicando la cantidad de dinero introducida por la tasa de conversión obtenida.
--El resultado de la conversión se muestra al usuario en la consola, indicando la cantidad original en MXN, la cantidad equivalente en la moneda de destino y la moneda de destino especificada.
