package ru.itis.prytkovd.webapp.inject.scopes;

import jakarta.inject.Provider;
import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import ru.itis.prytkovd.inject.Key;
import ru.itis.prytkovd.inject.scopes.Scope;
import ru.itis.prytkovd.webapp.inject.annotations.ContextScoped;

import java.lang.annotation.Annotation;

@AllArgsConstructor
public class ContextScope implements Scope {
    private final ServletContext context;

    @Override
    @SuppressWarnings("unchecked")
    public <T> Provider<T> scope(Key<T> key, Provider<T> provider) {
        return () -> {
            String attributeName = key.toString();

            Object instance = context.getAttribute(attributeName);

            if (instance == null) {
                instance = provider.get();
                context.setAttribute(attributeName, instance);
            }

            return (T)instance;
        };
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return ContextScoped.class;
    }
}
