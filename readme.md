# TWITTER GEO

Di seguito verrà riportato il funzionamento del progetto d'esame denominato "TWITTER - Geo", svolto nel corso di "programmazione ad oggetti" anno 2021/2022.

L'obiettivo di tale progetto è quello di sviluppare un'applicazione Java per effettuare statistiche sui dati Geo di Twitter su diverse location.

## indice

- [Descrizione generale](#DESCRIZIONE-GENERALE)
- [Funzionamento del progetto](#FUNZIONAMENTO-DEL-PROGETTO)

 	* [Rotte](#ROTTE)
 	* [Filtri](#FILTRI)
 	* [Statistiche](#STATISTICHE)



## DESCRIZIONE GENERALE

Il nostro applicativo è un RESTfull web Service, ovvero un sistema software che comunica con il client attraverso il protocollo HTTP. Più precisamente definisce delle risorse accessibili via Web o API Testing (es. Postman), tramite URL e mappa le operazioni CRUD (Create, Delete, Update, Delete).

L'applicativo permette di:

- visualizzare i tweet più recenti impostando, volendo, città, numero, tipologia e lingua dei tweet
- eseguire filtri sul numero di post dell'utente, hashtags, menzioni e username nella città di Milano
- effettuare statistiche sulla frequenza dei post, massimo, minimo, media e deviazione standard nella città di Milano



## FUNZIONAMENTO DEL PROGETTO

Il diagramma riportato qui sotto, illustra il funzionamento del progetto, ove l'utente, usando le rotte qui descritte, può ottenere la sua risposta in file json.

![immagine_utente_rotte](https://user-images.githubusercontent.com/95341359/148639891-fd9c092a-7dfa-4a73-84f7-d470e5835e99.jpeg)


### ROTTE
url http://localhost:8080. I dati che vengono restituiti sono in formato Json.

| DESTINAZIONE                                                 | DESCRIZIONE                                                  | esempio di chiamata                                          |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| /api/get-tweets                                              | chiamata generale, a cui vanno applicati attributi visualizza tutti i tweet(di Milano) | /api/get-tweets                                              |
| /api/get-tweets/api/get-tweets?location=<value>&result_type=<value>&count=<value>&lang=<value> | chiamata specifica in cui vengono visualizzati i Tweet (Ancona/Milano/Napoli) | /api/get-tweets?location=<Ancona>&result_type=<recent>&count=<50>&lang=<it> |
| /api/get-attributes                                          | visualizza i metadati e la loro descrizione                  | /api/get-attributes                                          |
| /api/filters                                                 | chiamata generale che visualizza i tweet con i filtri impostati di default (lower, post_num, 20 ). | /api/filters                                                 |
| /api/filters?filter=<value>&field=<value>&value=<value>      | visualizza i tweet con gli attribuiti specificati dall'utente. | /api/filters?filter=<search>&field=<username>&value=<Spring> |
| /api/statistics                                              | visualizza le statistiche sulla frequenza dei post           | /api/statistics                                              |
| /api/statistics?stats_field=<value>                          | visualizza le statistiche su determinati campi               | /api/statistics?stats_field=<list_count>                     |
| /api/statistics?stats_field=<value>&filter=<value>&field=<value>&value=<value> | Visualizza le statistiche su un campo dei Tweet, applicando prima un filtro | /api/statistics?stats_field=listed_count&filt<br />er=lower&field=post_num&value=50 |


#### esempio chiamata di tweet con i valori di default </br> 
![immagine get_tweets](https://user-images.githubusercontent.com/95341359/148639969-3f26d338-076b-4a42-a12b-16183f3e5f29.jpg)

* **post_id**: id del post
* **user_id**: id dell'utente
* **user_post_num**: numero di post dell'utente
* **post_lang**: lingua del tweet
* **post_type**: tipologia del tweet
* **location**: località in cui è stato fatto il tweet
* **user_post_mensions**: menzioni fatte nel tweet
* **postDate**: data in cui è stato fatto il post
* **screen_name**: nome utente dei profili taggati
	
### FILTRI 
I filtri richiedono dei parametri, non obbligatori, in quanto, se non vengono forniti, ottengono un valore di dafeult.

 ##### descrizione campi:

* **filter**: identifica il tipo di filtro da applicare; può essere di quattro tipi:  search, lower, upper e included. Il tipo di default è lower.

* **field**: identifica il campo su cui applicare il filtro
	* Nel caso di filtro **search**: hashtags, mentions, username
	* Nel caso di filtro **lower, upper, included**: post_num
* **value**: Identifica il valore da ricercare nel campo specificato 


#### esempio filtro con valori di default </br>
![image](https://user-images.githubusercontent.com/95341359/148640526-27f7947a-ad5b-4301-aaff-b450365961e6.png)


* **post_id**: id del post

* **user_id**: id dell'utente

* **user_post_num**: numero di post dell'utente

* **post_lang**: lingua del tweet

* **post_type**: tipologia del tweet

* **location**: località in cui è stato fatto il tweet

* **user_post_mensions**: menzioni fatte nel tweet

* **postDate**: data in cui è stato fatto il post

### STATISTICHE

### esempio di chiamata alle statistiche, sul campo listed_count

![Screenshot (152)](https://user-images.githubusercontent.com/95341359/148642032-7fd63aff-3dcb-4350-aade-a32331c2b0c9.png)


- Field: campo dove vengono effettuate le statistiche in questo caso listed_count

- listed_count: numero dei post dell'utente

- sum: somma dei post degli utenti 

- max: il numero massimo di post fatti da un solo  account

- min: numero minimo di post fatti da un solo account 

- avg: media dei post di tutti gli utenti 

- dev: devianza standar 

 #### Esempio chiamata statistiche con i filtri

![Screenshot (156)](https://user-images.githubusercontent.com/95341359/148642009-d6db2140-e3fa-49ea-92f0-cab3a937a816.png)


le statistiche vengono effettuate solo sui post che vengono filtrati.
