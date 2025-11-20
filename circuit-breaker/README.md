# 🔂 Circuit breaker

## 🎯 Situation

Ici, nous avons un `WeatherService` qui renvoie la météo en fonction d’un pays et d’une ville.
Le `WeatherService` échoue parfois en raison d’erreurs de connexion.
Un comportement intéressant pour le proxy consiste à masquer ces erreurs en renvoyant des valeurs mises en cache.
Si elles sont disponibles, les dernières températures valides sont renvoyées par le proxy lorsque le service distant est hors service.

Cependant, nous voulons redéfinir le proxy avec une autre implémentation basée sur le système [CircuitBreaker](https://martinfowler.com/bliki/CircuitBreaker.html) introduit par Martin Fowler.
Il est utilisé pour détecter les échecs et encapsule la logique permettant d’éviter qu’un échec ne se reproduise constamment.
Cette logique peut être spécifiée à l’aide d’une machine à états.

Pour implémenter cette machine à états, vous devez suivre la conception du modèle [State](https://refactoring.guru/design-patterns/state), un autre modèle célèbre du Gang of Four.
Sa structure peut être décrite à l’aide du diagramme suivant :

![state-diagram](https://refactoring.guru/images/patterns/diagrams/state/structure-fr.png)

## 🚧 À faire

1. Modéliser tous les états possibles de votre système.
2. Créer un diagramme de séquence montrant le passage dans tous les états avec :

   ```kotlin
   const val FAILURE_THRESHOLD: Int = 1
   const val OPEN_THRESHOLD: Int = 2
   ```
3. Implémenter votre solution.