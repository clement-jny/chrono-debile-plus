package com.chronodebileplus.central.stores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class StoreService {
    private final RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(StoreService.class);

    @Value("${central.endpoint.stores}")
    private String centralEndpointStores;

    public StoreService(RestClient _restClient) {
        this.restClient = _restClient;
    }

    /**
     * Récupère la liste des stores depuis l'API
     * Note: Le baseUrl n'est pas retourné par l'API, on utilise donc les valeurs en cache ou la valeur par défaut
     * @return Liste des stores avec leurs informations complètes
     */
    public List<Store> retrieveStores() {
        // TODO: add try/catch
        StoreApiResponse storeApiResponse = this.restClient.get()
            .uri(this.centralEndpointStores)
            .retrieve()
            .body(StoreApiResponse.class);

        //logger.info("Retrieved stores: {}", storeApiResponse.getStores().stream().map(Store::getName).collect(Collectors.joining(", ")));
        //assert storeApiResponse != null;
        return storeApiResponse.getStores();
    }

    /**
     * Enregistre un nouveau store
     * Note: L'API renvoie seulement id et name
     * @param _store Store à enregistrer
     * @return Store enregistré avec son ID
     */
    public Store registerStore(Store _store) {
        // TODO: add try/catch
        Store store = this.restClient.post()
            .uri(this.centralEndpointStores)
            .body(_store)
            .retrieve()
            .body(Store.class);

        // TODO: Delete
        // Reconstituer un Store complet avec le baseUrl d'origine
        // Store registeredStore = storeApiResponse.getStores().stream()
        //     .filter(s -> s.getName().equalsIgnoreCase(store.getName()))
        //     .findFirst()
        //     .orElseThrow(() -> new RuntimeException("Store not found in response"));

        logger.info("Store registered successfully with ID: {} and Name: {}", store.getId(), store.getName());
        return store;
    }
    
    /**
     * Vérifie si un store avec le même nom existe déjà
     * @param _name Le nom du store à vérifier
     * @return true si le nom existe déjà, false sinon
     */
    public boolean storeNameExists(String _name) {
        List<Store> existingStores = this.retrieveStores();
        return existingStores.stream().anyMatch(store -> store.getName().equalsIgnoreCase(_name));
    }
}
