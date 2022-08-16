# shop-microservices  
1) Создаем простой онлайн магазин используя микросервисы. Ядро формируют:  
	product-service(rest api + mongo db) - сервис отвечает за product config,  
	inventory-service(rest api + mysql db) - сервис который проверяет есть ли продукты  
	по заказу у нас на складе,  
	order-service(rest api + mysql db) - сервис отвечает за обработку заказов от  пользователей,  
  
	//Пока не реализован  
	notification-service(resilience 4j library - curcuit breaker pattern) - выполнение асинхронных коммуникаций используя event-driven architecture.  
	То есть когда заказ сделан, notification-service слушает это событие и посылает  order-notification к  
	пользователям  
  
2) Мы используем api-gateway с помощью которого все запросы будут перенаправляться на соответствующие сервисы.  
Вместе с api-gateway мы используем keycloak authorization server для защиты микросервисов.  
  
3) Используем eureka(service registry + discovery service) для коммуникации между нашими сервисами   
  
4) Используем централизованный config-server(на бэке git-repository) для хранения конфигураций   
и различных проперти  
  
5) Используем hashicorp vault для хранения секретных данных  
  
6)//Пока Не реализовано  
	zipkin - для отслеживания в каком микросервисе у нас возникли проблемы (implement distributed tracing).  
	Можем ходить по цепочке request и измерять время отклика для каждого сервиса  
  
7)//Пока не реализовано  
	Используем ELK для реализации централизованного логирования для всех микросервисов   
