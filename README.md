# API de PAYCOMET BankStore Java

Este es el API de conexión con todos los servicios de PAYCOMET BankStore mediante **IFRAME** y **XML**.

## Instrucciones de Instalación

1. Descargar el fichero `PayCOMET.jar` que es la librería principal.
2. Inserta la librería en tu proyecto de JAVA.
3. Utiliza las funciones contenidas en la librería para realizar las llamadas.


## Ejemplos de código

* Importar la clase PAYCOMET

```java
import PAYCOMET.*;
```

* Configuración del TPV

```java
PAYCOMET_BankStoreGatewayService tpv = new PAYCOMET_BankStoreGatewayService(MERCHANT_MERCHANTCODE , MERCHANT_TERMINAL , Password,  MERCHANT_JETID, 	ORIGINAL_IP);
```


### Operaciones IFRAME

#### Añadir una tarjeta (Tokenizar)

```java
ServiceResponse res = tpv.AddUserUrl(MERCHANT_ORDER, LANGUAGE);
```

#### Cobros
* Efectuar cobro

```java
ServiceResponse res = tpv.ExecutePurchaseUrl(MERCHANT_ORDER, MERCHANT_AMOUNT, MERCHANT_CURRENCY, LANGUAGE, MERCHANT_PRODUCTDESCRIPTION, 3DSECURE);
```

* Efectuar cobro con Token

Para realizar un pago con Token, primero tenemos que haber registrado una tarjeta con la función `AddUserUrl()`

```java
ServiceResponse res = tpv.ExecutePurchaseTokenUrl(MERCHANT_ORDER, MERCHANT_AMOUNT, MERCHANT_CURRENCY, USER_ID, USER_TOKEN, LANGUAGE, MERCHANT_PRODUCTDESCRIPTION, 3DSECURE);
```

#### Suscripciones

* Crear una suscripción

```java
ServiceResponse res = tpv.CreateSubscriptionUrl(MERCHANT_ORDER, MERCHANT_AMOUNT, MERCHANT_CURRENCY, SUBSCRIPTION_STARTDATE, SUBSCRIPTION_ENDTDATE, SUBSCRIPTION_PERIODICITY, LANGUAGE, MERCHANT_PRODUCTDESCRIPTION, 3DSECURE);
```

* Crear una suscripción con Token

```java 
ServiceResponse res = tpv.CreateSubscriptionTokenUrl(MERCHANT_ORDER, MERCHANT_AMOUNT, MERCHANT_CURRENCY, SUBSCRIPTION_STARTDATE, SUBSCRIPTION_ENDTDATE, SUBSCRIPTION_PERIODICITY, USER_ID, USER_TOKEN, LANGUAGE, 3DSECURE);
```

#### Preautorizaciones
* Crear una preautorización

```java 
ServiceResponse res = tpv.CreatePreauthorizationUrl(MERCHANT_ORDER, MERCHANT_AMOUNT, MERCHANT_CURRENCY, LANGUAGE, MERCHANT_PRODUCTDESCRIPTION, 3DSECURE);
```

* Crear una preautorización con Token

```java 
ServiceResponse res = tpv.CreatePreauthorizationTokenUrl(MERCHANT_ORDER, MERCHANT_AMOUNT, MERCHANT_CURRENCY, USER_ID, USER_TOKEN, LANGUAGE, MERCHANT_PRODUCTDESCRIPTION, 3DSECURE);
```

* Confirmar una preautorización

```java 
ServiceResponse res = tpv.PreauthorizationConfirmUrl(MERCHANT_ORDER, MERCHANT_AMOUNT, MERCHANT_CURRENCY, USER_ID, USER_TOKEN, LANGUAGE, MERCHANT_PRODUCTDESCRIPTION, 3DSECURE);
```

* Cancelar una preautorización

```java 
ServiceResponse res = tpv.PreauthorizationCancelUrl(MERCHANT_ORDER, MERCHANT_AMOUNT, MERCHANT_CURRENCY, USER_ID, USER_TOKEN, LANGUAGE, MERCHANT_PRODUCTDESCRIPTION, 3DSECURE);
```

#### Preautorizaciones aplazadas
* Crear una preautorización aplazada

```java 
ServiceResponse res = tpv.DeferredPreauthorizationUrl(MERCHANT_ORDER, MERCHANT_AMOUNT, MERCHANT_CURRENCY, LANGUAGE, MERCHANT_PRODUCTDESCRIPTION, 3DSECURE, MERCHANT_SCORING);
```

* Confirmar una preautorización aplazada

```
java ServiceResponse res = tpv.DeferredPreauthorizationConfirmUrl(MERCHANT_ORDER, MERCHANT_AMOUNT, MERCHANT_CURRENCY, USER_ID, USER_TOKEN, LANGUAGE, MERCHANT_PRODUCTDESCRIPTION, 3DSECURE);
```

* Cancelar una preautorización aplazada

```java 
ServiceResponse res = tpv.DeferredPreauthorizationCancelUrl(MERCHANT_ORDER, MERCHANT_AMOUNT, MERCHANT_CURRENCY, USER_ID, USER_TOKEN, LANGUAGE, MERCHANT_PRODUCTDESCRIPTION, 3DSECURE);
```


### Operaciones XML

#### Añadir una tarjeta (Tokenizar)

```java 
ServiceResponse res = srv.add_user(DS_MERCHANT_PAN, DS_MERCHANT_EXPIRYDATE, DS_MERCHANT_CVV2, DS_MERCHANT_CARDHOLDERNAME);
```

#### Cobros

* Efectuar cobro

```java 
ServiceResponse res = tpv.execute_purchase(DS_IDUSER, DS_TOKEN_USER, DS_MERCHANT_AMOUNT, DS_MERCHANT_ORDER, DS_MERCHANT_CURRENCY, DS_MERCHANT_PRODUCTDESCRIPTION, DS_MERCHANT_OWNER, DS_MERCHANT_SCORING);
```

* Efectuar cobro DCC

Esta operación se realiza en **dos pasos**:
`execute_purchase_dcc`, donde se recibe la moneda nativa de la tarjeta (en caso que la tarjeta tenga la misma moneda que el producto asociado a la transacción, el resultado será de conversión 1:1) y posteriormente se confirmará con el método `confirm_purchase_dcc` con la moneda seleccionada y la sesión original de la transacción.

```java 
ServiceResponse res = tpv.execute_purchase_dcc(DS_IDUSER, DS_TOKEN_USER, DS_MERCHANT_AMOUNT, DS_MERCHANT_ORDER, DS_MERCHANT_CURRENCY, DS_MERCHANT_PRODUCTDESCRIPTION, DS_MERCHANT_OWNER);
```

```java 
ServiceResponse res = tpv.confirm_purchase_dcc(DS_MERCHANT_ORDER, DS_MERCHANT_DCC_CURRENCY, DS_MERCHANT_DCC_SESSION);
```

#### Suscripciones

* Crear una suscripción

```java 
ServiceResponse res = tpv.create_subscription(DS_MERCHANT_PAN, DS_MERCHANT_EXPIRYDATE, DS_MERCHANT_CVV2, DS_SUBSCRIPTION_STARTDATE, DS_SUBSCRIPTION_ENDDATE, DS_SUBSCRIPTION_ORDER, DS_SUBSCRIPTION_PERIODICITY, DS_SUBSCRIPTION_AMOUNT, DS_SUBSCRIPTION_CURRENCY, DS_EXECUTE, DS_MERCHANT_CARDHOLDERNAME, DS_MERCHANT_SCORING);
```

* Crea una suscripción con Token

```java 
ServiceResponse res = tpv.create_subscription_token(DS_IDUSER, DS_TOKEN_USER, DS_SUBSCRIPTION_STARTDATE, DS_SUBSCRIPTION_ENDDATE, DS_SUBSCRIPTION_ORDER, DS_SUBSCRIPTION_PERIODICITY, DS_SUBSCRIPTION_AMOUNT, DS_SUBSCRIPTION_CURRENCY, DS_MERCHANT_SCORING);
```

#### Preautorizaciones

* Crear una preautorización

```java 
ServiceResponse res = tpv.create_preauthorization(DS_IDUSER, DS_TOKEN_USER, DS_MERCHANT_AMOUNT, DS_MERCHANT_ORDER, DS_MERCHANT_CURRENCY, DS_MERCHANT_PRODUCTDESCRIPTION, DS_MERCHANT_OWNER, DS_MERCHANT_SCORING);
```

* Confirmar una preautorización

```java 
ServiceResponse res = tpv.preauthorization_confirm(DS_IDUSER, DS_TOKEN_USER, DS_MERCHANT_AMOUNT, DS_MERCHANT_ORDER);
```

* Cancelar una preautorización

```java 
ServiceResponse res = tpv.preauthorization_cancel(DS_IDUSER, DS_TOKEN_USER, DS_MERCHANT_AMOUNT, DS_MERCHANT_ORDER);
```


### Soporte

Si tienes alguna duda o pregunta no tienes más que escribirnos un email a [tecnico@paycomet.com](mailto:tecnico@paycomet.com)



