# Currency-exchange-rate

Currency Exchange Rates is an android application developped using MVVM architecture and android architecture components, and the open source graph lib GraphView. 

this app consumes the free API https://exchangeratesapi.io/ to get current and historical exchange rates for a given period of time.

Get the latest rates against a given currency by setting the base parameter in your request.

GET https://api.exchangeratesapi.io/latest?base=USD 


Get historical rates for a time period for a given currency against another.

GET https://api.exchangeratesapi.io/history?start_at=2018-01-01&end_at=2018-09-01&base=USD&symbols=EUR 
 
