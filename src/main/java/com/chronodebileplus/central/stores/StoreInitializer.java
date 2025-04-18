package com.chronodebileplus.central.stores;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class StoreInitializer {

    private final StoreService storeService;
    private static final Logger logger = LoggerFactory.getLogger(
        StoreInitializer.class
    );

    public StoreInitializer(StoreService _storeService) {
        this.storeService = _storeService;
    }

    @Value("${myStore.name}")
    private String myStoreName;

    @Value("${myStore.url}")
    private String myStoreUrl;

    @PostConstruct
    public void initializeMyStore() {
        // Vérifier si le store existe déjà
        if (this.storeService.storeNameExists(this.myStoreName)) {
            logger.error(
                "Le nom du magasin est déjà utilisé: {}",
                this.myStoreName
            );
            return;
        }

        // Register the store
        Store store = new Store(this.myStoreName, this.myStoreUrl);
        logger.info("Registering store: {}", store);
        Store registeredStore = this.storeService.registerStore(store);
        logger.info("Store registered with ID: {}", registeredStore.getId());
    }
}
