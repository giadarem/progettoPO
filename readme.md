# TWITTER GEO
Di seguito verrà riportato il funzionamento del progetto d'esame denominato "TWITTER - Geo", svolto nel corso di "programmazione ad oggetti" anno 2021/2022.

L'obiettivo di tale progetto è quello di sviluppare un'applicazione Java per effettuare statistiche sui dati Geo di Twitter su diverse location.

## indice

- Descrizione generale
- Funzionamento del progetto
- Rotte 
- Struttura del progetto



## DESCRIZIONE GENERALE

Il nostro applicativo è un RESTfull web Service, ovvero un sistema software che comunica con il client attraverso il protocollo HTTP. Più precisamente definisce delle risorse accessibili via Web o API Testing (es. Postman), tramite URL e mappa le operazioni CRUD (Create, Delete, Update, Delete).

L'applicativo permette di:

- visualizzare i tweet più recenti impostando, volendo, città, numero, tipologia e lingua dei tweet
- eseguire filtri sul numero di post dell'utente, hashtags, menzioni e username
- effettuare statistiche sulla frequenza dei post, massimo, minimo, media e deviazione standard



## FUNZIONAMENTO DEL PROGETTO

Il diagramma riportato qui sotto, illustra il funzionamento del progetto, ove l'utente, usando le rotte qui descritte, può ottenere la sua risposta in file json.

![](C:\Users\giada\Desktop\università\secondo anno\programmazioneOggetti\rotte.png)



## ROTTE

| DESTINAZIONE                                                 | DESCRIZIONE                                                  | esempio di chiamata |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------- |
| /api/get-tweets                                              | chiamata generale, a cui vanno applicati attributi visualizza tutti i tweet(di Milano) | /api/filters        |
| /api/get-tweets?location=<value>&result_type=<value>&count=<value>&lang=<value> | chiamata specifica in cui vengono visualizzati i Tweet (Ancona/Milano/Napoli) |                     |
| /api/get-attributes                                          | visualizza i metadati e la loro descrizione                  |                     |
| /api/filters                                                 | chiamata generale che visualizza i tweet con i filtri impostati di default (lower, post_num, 20 ). |                     |
| /api/filters?filter=<value>&field=<value>&value=<value>      | visualizza i tweet con gli attribuiti specificati dall'utente. |                     |
| /api/statistics                                              | visualizza le statistiche sulla frequenza dei post           |                     |
| /api/statistics?stats_field=<value>                          | visualizza le statistiche su determinati filtri              |                     |
| /api/statistics?stats_field=<value>&filter=<value>&field=<value>&value=<value> | Visualizza le statistiche su un campo dei Tweet, applicando prima un filtro |                     |

E' possibile effettuare le chiamate sia installando un API Testing   (Postman) sia tramite richiesta all'url

 http://localhost:8080. I dati che vengono restituiti sono in formato Json.

Le chiamate per tutte le Statistics e Filter, saranno solo sulla città di Milano.

<<<<<<< HEAD
#### FUNZIONAMENTO DEL PROGETTO
=======


## FUNZIONAMENTO DEL PROGETTO
>>>>>>> 1c73b6666575ce9a60c1665c2adf408a1c88a894

| tipo di filtro                                               | descrizione                                                  | esempio di chiamata |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------- |
| /api/filters                                                 | la chiamata restituisce i tweet degli utenti che hanno<br />un numero di tweet effettuati minore di 20 | /api/filters        |
| /api/get-tweets?location=<value>&result_type=<value>&count=<value>&lang=<value> |                                                              |                     |
|                                                              |                                                              |                     |
|                                                              |                                                              |                     |

