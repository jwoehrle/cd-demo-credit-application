package com.innoq.mploed.ddd.application.events;

public interface EventPublisher {
    void publishEvent(String topic, DomainEvent event);
}
