```puml
@startuml
    class Rxbus {
        +IEventSubscriber iEventSubscriber
        +addSubscriber(IEventSubscriber)
        +sendMessageTo(Event)
    }
@enduml
```